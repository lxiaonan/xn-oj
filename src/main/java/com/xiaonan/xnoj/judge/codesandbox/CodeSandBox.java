package com.xiaonan.xnoj.judge.codesandbox;

import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;

/**
 * 代码沙箱接口
 */
public interface CodeSandBox {
    /**
     * 代码执行
     * @param codeExecuteRequest
     * @return
     */
    CodeExecuteResponse codeExecute(CodeExecuteRequest codeExecuteRequest);
}
