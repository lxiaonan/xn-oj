package com.xiaonan.xnoj.model.dto.postfavour;

import com.xiaonan.xnoj.common.PageRequest;
import com.xiaonan.xnoj.model.dto.post.PostQueryRequest;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 帖子收藏查询请求
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostFavourQueryRequest extends PageRequest implements Serializable {

    /**
     * 帖子查询请求
     */
    private PostQueryRequest postQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
