package com.xiaonan.xnoj.judge.codesandbox.impl;

import com.xiaonan.xnoj.judge.codesandbox.CodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;

public class NativeCodeSandBox implements CodeSandBox {
    @Override
    public CodeExecuteResponse codeExecute(CodeExecuteRequest codeExecuteRequest) {
        System.out.println("本地代码沙箱环境");
        return null;
    }
}
