package com.iblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: iblog-api
 * @description: 点赞记录
 * @author: cgq
 * @create: 2020-04-26 20:02
 **/
@Data
public class Upvote {
    private Long upvoteId;
    private String blogId;
    private Long userId;
    private Date createTime;
}