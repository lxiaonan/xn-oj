package com.xiaonan.xnoj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaonan.xnoj.common.ErrorCode;
import com.xiaonan.xnoj.exception.BusinessException;
import com.xiaonan.xnoj.judge.codesandbox.CodeSandBox;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteRequest;
import com.xiaonan.xnoj.judge.codesandbox.model.CodeExecuteResponse;

public class NativeCodeSandBox implements CodeSandBox {
    @Override
    public CodeExecuteResponse codeExecute(CodeExecuteRequest codeExecuteRequest) {
        System.out.println("本地代码沙箱环境");
        String url = "localhost:8089/judge/do";
        String toJsonStr = JSONUtil.toJsonStr(codeExecuteRequest);
        String response = HttpUtil.createPost(url).body(toJsonStr).execute().body();
        if (StringUtils.isBlank(response)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return JSONUtil.toBean(response,CodeExecuteResponse.class);
    }
}
