package com.iblog.service;

import com.baomidou.mybatisplus.service.IService;
import com.iblog.entity.Comment;

public interface CommentService extends IService<Comment> {
    void deleteComment(String blogId);
}
