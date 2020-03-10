package com.iblog.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客实体类
 * @author: cgq
 * @create: 2020-01-28 11:08
 **/
@Data
public class Blog {
    private String blogId;
    private Long userId;
    private String userNickname;
    private String title;
    private String content;
    private String contentHtml;
    private List<Tag> tagList;
    private String tags;
    private String categoryId;
    private String categoryName;
    private String summary;
    private Integer viewNum;
    private Integer isHide;
    private Integer likeNum;
    private Integer commentNum;
    private Integer blogStatus;
    private Date createTime;
    private Date checkTime;
    private Date updateTime;

}