package com.iblog.service;

import com.baomidou.mybatisplus.service.IService;
import com.iblog.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    void deleteComment(String blogId);

    List<Comment> queryCommentByBlogId(String blogId);

    List<Comment> queryCommentByParentId(Long parentId);
}
