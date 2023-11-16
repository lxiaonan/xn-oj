package com.xiaonan.xnoj.judge;

import cn.hutool.json.JSONUtil;
import com.xiaonan.xnoj.common.ErrorCode;
import com.xiaonan.xnoj.exception.BusinessException;
import com.xiaonan.xnoj.judge.codesandbox.CodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.CodeSandBoxFactory;
import com.xiaonan.xnoj.judge.codesandbox.CodeSandBoxProxy;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;
import com.xiaonan.xnoj.judge.strategy.*;
import com.xiaonan.xnoj.model.dto.question.JudgeCase;
import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.entity.QuestionSubmit;
import com.xiaonan.xnoj.model.enums.QuestionSubmitStatusEnum;
import com.xiaonan.xnoj.service.QuestionService;
import com.xiaonan.xnoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionSubmitService questionSubmitService;
    @Value("${codeSandBox.type}")
    String type;

    @Override
    public CodeExecuteResponse doJudge(QuestionSubmit questionSubmit) {
        // 1.前置判断
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交记录不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 2.修改数据库中结果
        Integer status = questionSubmit.getStatus();
        // 如果题目不是等待中，直接退出
        if (!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "判题中");
        }
        //设置状态为判题中
        questionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "状态修改失败");
        }

        //3.调用沙箱，获取执行结果
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCase, JudgeCase.class);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 选择那种沙箱
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        CodeSandBoxProxy codeSandBoxProxy = new CodeSandBoxProxy(codeSandBox);
        CodeExecuteRequest codeExecuteRequest = CodeExecuteRequest
                .builder()
                .code(code)
                .inputList(judgeCases.stream().map(JudgeCase::getInput).collect(Collectors.toList()))
                .language(language)
                .build();
        //获得沙箱执行结果
        CodeExecuteResponse codeExecuteResponse = codeSandBoxProxy.codeExecute(codeExecuteRequest);
        if (codeExecuteResponse == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "判题结果返回失败");
        }

        //4.根据沙箱的执行结果，设置题目的判题状态和信息
        //从代码执行结果中获得判题信息
        JudgeInfo judgeInfo = codeExecuteResponse.getJudgeInfo();
        List<String> outPutList = judgeCases.stream().map(JudgeCase::getOutput).collect(Collectors.toList());
        //获取用户代码执行结果
        List<String> userOutputList = codeExecuteResponse.getOutputList();
        //创建判题上下文
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(judgeInfo);
        judgeContext.setUserOutputList(userOutputList);
        judgeContext.setOutputList(outPutList);
        judgeContext.setQuestion(question);
        judgeContext.setLanguage(language);

        //选择不同的策略
        judgeInfo = JudgeManage.doJudge(judgeContext);
        codeExecuteResponse.setJudgeInfo(judgeInfo);
        //5.更新数据库信息
        questionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "状态修改失败");
        }

        return codeExecuteResponse;
    }
}
