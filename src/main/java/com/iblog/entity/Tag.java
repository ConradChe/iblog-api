package com.iblog.entity;

import java.util.Date;

/**
 * @program: iblog-api
 * @description: 标签实体类
 * @author: cgq
 * @create: 2020-01-31 20:26
 **/
public class Tag {
    private Integer tagId;
    private String tagName;
    private Date createTime;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}