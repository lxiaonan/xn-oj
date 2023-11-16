package com.xiaonan.xnoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaonan.xnoj.model.dto.question.QuestionQueryRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaonan.xnoj.model.entity.User;
import com.xiaonan.xnoj.model.vo.QuestionSubmitVO;
import com.xiaonan.xnoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 罗宇楠
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-10-08 14:24:58
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 提交题目id
     */
    QuestionSubmit doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目提交封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
