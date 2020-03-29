package com.iblog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.iblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: iblog-api
 * @description: 评论Mapper
 * @author: cgq
 * @create: 2020-03-22 16:53
 **/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    void deleteComment(String blogId);
}