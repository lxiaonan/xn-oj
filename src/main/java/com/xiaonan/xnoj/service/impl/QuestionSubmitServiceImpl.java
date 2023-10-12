package com.xiaonan.xnoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaonan.xnoj.common.ErrorCode;
import com.xiaonan.xnoj.constant.CommonConstant;
import com.xiaonan.xnoj.exception.BusinessException;
import com.xiaonan.xnoj.model.dto.question.QuestionQueryRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaonan.xnoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaonan.xnoj.model.entity.Question;
import com.xiaonan.xnoj.model.entity.QuestionSubmit;
import com.xiaonan.xnoj.model.entity.User;
import com.xiaonan.xnoj.model.enums.QuestionSubmitLanguageEnum;
import com.xiaonan.xnoj.model.enums.QuestionSubmitStatusEnum;
import com.xiaonan.xnoj.model.vo.QuestionSubmitVO;
import com.xiaonan.xnoj.model.vo.QuestionVO;
import com.xiaonan.xnoj.model.vo.UserVO;
import com.xiaonan.xnoj.service.*;
import com.xiaonan.xnoj.service.QuestionSubmitService;
import com.xiaonan.xnoj.mapper.QuestionSubmitMapper;
import com.xiaonan.xnoj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 罗宇楠
 * @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
 * @createDate 2023-10-08 14:24:58
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 题目id
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //判断编程语言是否存在
        String language = questionSubmitAddRequest.getLanguage();
        if (QuestionSubmitLanguageEnum.getEnumByValue(language) == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"编程语言不存在");
        }
        // 是否已题目提交
        long userId = loginUser.getId();
        // 每个用户串行题目提交
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        BeanUtils.copyProperties(questionSubmitAddRequest,questionSubmit);
//        questionSubmit.setCode(questionSubmitAddRequest.getCode());
//        questionSubmit.setQuestionId(questionId);
//        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());//判题状态，需要使用枚举类比较优雅
        questionSubmit.setJudgeInfo("{}");
        questionSubmit.setCreateTime(new Date());
        questionSubmit.setUpdateTime(new Date());
        boolean save = save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"新增题目异常");
        }
        return questionSubmit.getId();
    }
    /**
     * 获取查询包装类
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long userId = questionSubmitQueryRequest.getUserId();
        Long questionId = questionSubmitQueryRequest.getQuestionId();

        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);

        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        // 1. 关联查询用户信息
        Long userId = loginUser.getId();
        // 如果该提交信息不是当前用户的，并且不是管理员
        if(questionSubmit.getUserId() != userId && !userService.isAdmin(loginUser)){
            questionSubmitVO.setCode(null);
        }

        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(questionSubmit -> {
            return getQuestionSubmitVO(questionSubmit, loginUser);
        }).collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }


}




