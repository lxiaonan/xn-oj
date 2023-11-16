package com.xiaonan.xnoj.judge.strategy;

import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;

/**
 * 选择不同的策略
 */
public class JudgeManage {

    public static JudgeInfo doJudge(JudgeContext judgeContext){
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(judgeContext.getLanguage().equals("java")){
            judgeStrategy = new JavaDefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
