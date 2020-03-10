package com.iblog.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @program: iblog-api
 * @description: 用户实体类
 * @author: cgq
 * @create: 2020-01-28 12:43
 **/
@Data
@TableName("t_user")
public class User {
    @TableId
    private Long userId;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    private String nickname;
    private String salt;
    private String name;
    private String headImg;
    private char gender;
    private String birthday;
    private String hobby;
    private String address;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;


}