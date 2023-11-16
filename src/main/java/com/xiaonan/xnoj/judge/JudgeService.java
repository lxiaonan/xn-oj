package com.xiaonan.xnoj.judge;

import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;
import com.xiaonan.xnoj.model.entity.QuestionSubmit;

public interface JudgeService {
    public CodeExecuteResponse doJudge(QuestionSubmit questionSubmit);
}
