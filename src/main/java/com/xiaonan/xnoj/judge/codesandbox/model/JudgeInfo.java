package com.xiaonan.xnoj.judge.codesandbox.model;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {
    /***
     * {"message": "程序执行信息","time": 1000, // 单位为 ms"memory": 1000, // 单位为 kb}
     **/
    //程序执行信息
    private String message;
    // 执行时间
    private Long time;
    // 执行空间
    private Long memory;
}
