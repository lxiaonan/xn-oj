package com.xiaonan.xnoj.service;

import com.xiaonan.xnoj.model.entity.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaonan.xnoj.model.entity.User;

/**
 * 帖子点赞服务
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}
