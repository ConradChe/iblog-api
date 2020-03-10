package com.iblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: iblog-api
 * @description: 博客分类实体类
 * @author: cgq
 * @create: 2020-01-31 19:18
 **/
@Data
public class Category {
    private String categoryId;
    private String categoryName;
    private Long userId;
    private Date createTime;

}