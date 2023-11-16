package com.xiaonan.xnoj.judge.strategy;

import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class DefaultJudgeStrategy implements JudgeStrategy{
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> userOutputList = judgeContext.getUserOutputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();

        String message = judgeInfo.getMessage();
        int acceptNum = 0;
        for (int i = 0; i < outputList.size(); i++) {
            if (outputList.get(i).equals(userOutputList.get(i)))
                acceptNum++;
        }
        judgeInfo.setMessage(message + JudgeInfoMessageEnum.SUCCESS.getMessage() + ":100%");
        //计算正确率
        if (acceptNum != outputList.size()) {
            double accuracy = acceptNum * 1.0 / outputList.size() * 100;
            message = acceptNum == 0 ? JudgeInfoMessageEnum.WRONG_ANSWER.getMessage() : JudgeInfoMessageEnum.PARTIALLY_CORRECT.getMessage();
            judgeInfo.setMessage(message + ":" + accuracy + "%");
        }

        return judgeInfo;
    }
}
