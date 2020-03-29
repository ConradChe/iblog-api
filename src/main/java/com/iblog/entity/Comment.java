package com.iblog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

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
    private Integer commentId;
    private String blogId;
    private Integer userId;
    private Integer byId;
    private String commentText;
    private String children;
    private Integer likeNum;
}