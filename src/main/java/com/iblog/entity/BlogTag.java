package com.iblog.entity;

/**
 * @program: iblog-api
 * @description: 博客标签关系实体类
 * @author: cgq
 * @create: 2020-02-01 11:51
 **/
public class BlogTag {
    private String blogId;
    private Integer tagId;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}