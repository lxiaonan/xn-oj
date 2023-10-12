package com.xiaonan.xnoj.model.dto.questionsubmit;


import com.baomidou.mybatisplus.annotation.TableField;
import com.xiaonan.xnoj.model.dto.question.JudgeCase;
import com.xiaonan.xnoj.model.dto.question.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;


    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}
