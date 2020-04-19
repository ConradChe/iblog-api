package com.iblog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: iblog-api
 * @description: 评论实体类
 * @author: cgq
 * @create: 2020-03-22 16:55
 **/
@Data
@TableName("t_comment")
public class Comment {
    @TableId
    private Long commentId;
    private String blogId;
    private Long userId;
    private Long byId;
    private String commentText;
    private String children;
    private Integer likeNum;
    private Date createTime;
    private User user;
    private User byUser;
    private Long parentId;
    private List<Comment> childrenList;
}