package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     *查询帖子  分页
     */
    List<DiscussPost> selectDiscussPost(int userId, int offset, int limit); //offset:起始的行号  limit:每页显示几条数据

    /**
     * 查询帖子的总行数
     * @Param 如果方法只有一个参数并且在<if>里使用，则必须加别名
     */
    int selectDiscussPostRows(@Param("userId") int userId);
}
