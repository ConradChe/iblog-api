package com.iblog.mapper;

import com.iblog.entity.Upvote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UpvoteMapper {

    Long selectUpvoteById(String blogId,Long userId);

    int insertUpvote(Upvote upvote);

    int deleteUpvote(String blogId,Long userId);

    List<Map> selectUpvoteCount();

    Map selectUpvoteCountById(String blogId);
}
