package com.xiaonan.xnoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaonan.xnoj.annotation.AuthCheck;
import com.xiaonan.xnoj.common.BaseResponse;
import com.xiaonan.xnoj.common.ErrorCode;
import com.xiaonan.xnoj.common.ResultUtils;
import com.xiaonan.xnoj.constant.UserConstant;
import com.xiaonan.xnoj.exception.BusinessException;
import com.xiaonan.xnoj.exception.ThrowUtils;
import com.xiaonan.xnoj.model.dto.question.QuestionQueryRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.entity.QuestionSubmit;
import com.xiaonan.xnoj.model.entity.User;
import com.xiaonan.xnoj.model.vo.QuestionSubmitVO;
import com.xiaonan.xnoj.service.QuestionSubmitService;
import com.xiaonan.xnoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
@RestController
@RequestMapping("/question_thumb")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 题目提交
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                         HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 获取所有题目提交， 管理员能查看所有的，而用户只能查看脱敏后的(就是无答案的)
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest, request));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
