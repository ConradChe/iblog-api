package com.iblog.entity;

import java.util.Date;

/**
 * @program: iblog-api
 * @description: 博客分类实体类
 * @author: cgq
 * @create: 2020-01-31 19:18
 **/
public class Category {
    private String categoryId;
    private String categoryName;
    private Integer userId;
    private Date createTime;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}