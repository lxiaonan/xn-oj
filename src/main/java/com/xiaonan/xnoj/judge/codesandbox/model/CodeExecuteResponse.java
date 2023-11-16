package com.xiaonan.xnoj.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeExecuteResponse {
    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 判题状态 0成功 1部分正确 2失败
     */
    private Integer status;
    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
