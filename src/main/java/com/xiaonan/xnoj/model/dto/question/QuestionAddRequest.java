package com.xiaonan.xnoj.model.dto.question;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
@Data
public class QuestionAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;


    /**
     * 判题用例 可能有多组
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置 时间限制 空间限制
     */
    private JudgeConfig judgeConfig;



    private static final long serialVersionUID = 1L;
}
