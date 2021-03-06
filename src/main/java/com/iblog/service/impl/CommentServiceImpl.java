package com.iblog.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.iblog.entity.Comment;
import com.iblog.mapper.CommentMapper;
import com.iblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 评论Service
 * @author: cgq
 * @create: 2020-03-22 17:04
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void deleteComment(String blogId) {
        commentMapper.deleteComment(blogId);
    }

    @Override
    public List<Comment> queryCommentByBlogId(String blogId) {
        return commentMapper.queryCommentByBlogId(blogId);
    }

    @Override
    public List<Comment> queryCommentByParentId(Long parentId) {
        return commentMapper.queryCommentByParentId(parentId);
    }
}