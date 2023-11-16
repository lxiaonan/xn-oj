package com.xiaonan.xnoj.judge.strategy;

import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略，我们有多种，比如java、cpp
 * 减少if-else的使用
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
