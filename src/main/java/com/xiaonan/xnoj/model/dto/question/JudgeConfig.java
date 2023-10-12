package com.xiaonan.xnoj.model.dto.question;

import lombok.Data;

/**
 * 判题配置
 */
@Data
public class JudgeConfig {
    /***
     * {"timeLimit": 1000,"memoryLimit": 1000,"stackLimit": 1000}
     **/
    // 时间限制 ms
    private Long timeLimit;
    // 内存限制 kb
    private Long memoryLimit;
    // 栈限制
    private Long stackLimit;

}
