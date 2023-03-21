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
    List<DiscussPost> selectDiscussPost(int userId, int offset, int limit, int orderMode); //offset:起始的行号  limit:每页显示几条数据

    /**
     * 查询帖子的总行数
     * @Param 如果方法只有一个参数并且在<if>里使用，则必须加别名
     */
    int selectDiscussPostRows(@Param("userId") int userId);

    /**
     * 插入帖子
     * @param discussPost
     * @return
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     * 根据id查询帖子
     */
    DiscussPost selectDiscussPostById(int id);

    /**
     * 增加评论数量
     */
    int updateCommentCount(int id,int commentCount);

    /**
     *
     */
    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);
}
