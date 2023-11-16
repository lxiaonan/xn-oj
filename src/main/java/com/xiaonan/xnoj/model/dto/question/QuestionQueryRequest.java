package com.xiaonan.xnoj.model.dto.question;

import com.xiaonan.xnoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * userid
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 题目难度 简单1 中等3 困难5
     */
    private Integer rate;
    /**
     * 标签列表
     */
    private List<String> tags;
    /**
     * 题目答案
     */
    private String answer;


    private static final long serialVersionUID = 1L;
}
