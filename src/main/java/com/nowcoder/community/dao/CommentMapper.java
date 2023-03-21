package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 查询评论
     */
    List<Comment> selectCommentByEntityType(int entityType, int entityId, int offset, int limit);

    /**
     * 查询评论的条数
     * @param entityType
     * @param entityId
     * @return
     */
    int selectCountByEntity(int entityType,int entityId);

    /**
     * 添加评论
     */
    int insertComment(Comment comment);

    /**
     * 根据id查找评论
     */
    Comment selectCommentById(int id);

}
