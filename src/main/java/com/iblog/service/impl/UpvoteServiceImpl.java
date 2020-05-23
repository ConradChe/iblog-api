package com.iblog.service.impl;

import com.iblog.entity.Upvote;
import com.iblog.mapper.UpvoteMapper;
import com.iblog.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: iblog-api
 * @description: 点赞Service
 * @author: cgq
 * @create: 2020-04-26 20:24
 **/
@Service
public class UpvoteServiceImpl implements UpvoteService {
    @Autowired
    private UpvoteMapper upvoteMapper;
    @Override
    public Long selectUpvoteById(String blogId, Long userId) {
        return upvoteMapper.selectUpvoteById(blogId, userId);
    }

    @Override
    public int insertUpvote(Upvote upvote) {
        return upvoteMapper.insertUpvote(upvote);
    }

    @Override
    public int deleteUpvote(String blogId, Long userId) {
        return upvoteMapper.deleteUpvote(blogId, userId);
    }

    @Override
    public List<Map> selectUpvoteCount() {
        return upvoteMapper.selectUpvoteCount();
    }

    @Override
    public Map selectUpvoteCountById(String blogId) {
        return upvoteMapper.selectUpvoteCountById(blogId);
    }
}