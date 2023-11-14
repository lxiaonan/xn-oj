package com.xiaonan.xnoj.judge.codesandbox;

import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理，能够在不改变原本代码的情况下，增加一些功能
 */
@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {
    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public CodeExecuteResponse codeExecute(CodeExecuteRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        CodeExecuteResponse executeCodeResponse = codeSandBox.codeExecute(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
