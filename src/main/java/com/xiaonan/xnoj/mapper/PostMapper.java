package com.xiaonan.xnoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaonan.xnoj.model.entity.Post;
import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author <a href="https://github.com/luo-yunan">小楠</a>
 * @author 小楠
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

}




