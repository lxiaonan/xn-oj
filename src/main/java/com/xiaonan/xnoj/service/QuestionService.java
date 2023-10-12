package com.xiaonan.xnoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaonan.xnoj.model.dto.question.QuestionQueryRequest;
import com.xiaonan.xnoj.model.entity.Question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaonan.xnoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 罗宇楠
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2023-10-08 14:24:21
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add,HttpServletRequest request);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest,HttpServletRequest request);


    /**
     * 获取题目封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
