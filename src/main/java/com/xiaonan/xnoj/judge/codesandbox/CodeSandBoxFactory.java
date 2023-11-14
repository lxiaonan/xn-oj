package com.xiaonan.xnoj.judge.codesandbox;

import com.xiaonan.xnoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.impl.NativeCodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            case "native":
            default: return new NativeCodeSandBox();
        }
    }
}
