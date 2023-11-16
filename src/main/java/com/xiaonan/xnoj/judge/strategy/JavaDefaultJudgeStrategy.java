package com.xiaonan.xnoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.xiaonan.xnoj.model.dto.question.JudgeConfig;
import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class JavaDefaultJudgeStrategy implements JudgeStrategy{
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> userOutputList = judgeContext.getUserOutputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();


        int acceptNum = 0;
        for (int i = 0; i < outputList.size(); i++) {
            String result = isResult(userOutputList.get(i), outputList.get(i));
            if (result.equals("true"))
                acceptNum++;
            else if(result.equals("false")){

            }else{

            }
        }
        String message = judgeInfo.getMessage() == null ? "" : judgeInfo.getMessage();
        judgeInfo.setMessage(message + JudgeInfoMessageEnum.ACCEPTED.getMessage() + ":100%");
        //计算正确率
        if (acceptNum != outputList.size()) {
            //获取用户代码的执行时间和空间
            Long time = judgeInfo.getTime();
            Long memory = judgeInfo.getMemory();
            //获取题目配置
            String judgeConfig = question.getJudgeConfig();
            JudgeConfig config = JSONUtil.toBean(judgeConfig, JudgeConfig.class);

            if (memory != null && memory > config.getMemoryLimit()) {
                message = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getMessage();
            }
            if(time > config.getTimeLimit()){
                message = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getMessage() ;
            }
            double accuracy = acceptNum * 1.0 / outputList.size() * 100;
            message += acceptNum == 0 ? JudgeInfoMessageEnum.WRONG_ANSWER.getMessage() : JudgeInfoMessageEnum.PARTIALLY_CORRECT.getMessage();
            judgeInfo.setMessage(message + ":" + accuracy + "%");
        }
        return judgeInfo;
    }

    private static String isResult(String userOut, String out) {
        String TIME = "execTime:";
        String time = "";
        String ans = "";
        if (userOut.contains(TIME)) {
            ans = userOut.substring(0,userOut.indexOf(TIME));
            time = userOut.substring(userOut.indexOf(":") + 1);
        }
        if(ans.equals(out)){
            // todo 判断时间是否超时
            return "true";
        }
        return "false";
    }
}
