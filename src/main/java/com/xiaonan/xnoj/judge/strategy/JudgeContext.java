package com.xiaonan.xnoj.judge.strategy;

import com.xiaonan.xnoj.judge.codesandbox.model.JudgeInfo;
import com.xiaonan.xnoj.model.entity.Question;
import lombok.Data;

import java.util.List;

/**
 * 使用不同的策略都需要传递的信息
 */
@Data
public class JudgeContext {
    //判题信息
    private JudgeInfo judgeInfo;
    //执行用户代码获得的输出
    private List<String> userOutputList;
    //题目的样例输出
    private List<String> outputList;
    //编程语言
    private String language;
    private Question question;

}
