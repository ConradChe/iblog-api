package com.iblog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: iblog-api
 * @description: 系统用户Token
 * @author: cgq
 * @create: 2020-02-15 19:19
 **/
@Data
@TableName("t_user_token")
public class UserToken implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
}