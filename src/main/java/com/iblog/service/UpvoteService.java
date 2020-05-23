package com.iblog.service;

import com.iblog.entity.Upvote;

import java.util.List;
import java.util.Map;

public interface UpvoteService {
    Long selectUpvoteById(String blogId, Long userId);

    int insertUpvote(Upvote upvote);

    int deleteUpvote(String blogId,Long userId);

    List<Map> selectUpvoteCount();

    Map selectUpvoteCountById(String blogId);
}
