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
    @NotBlank(message = "手机号不可为空")
    private String phone;
    @NotBlank(message = "密码不可为空")
    private String password;
    private String code;
    private String nickname;
    private String salt;
    private String name;
    private String headImg;
    private char gender;
    private Integer role;
    private String birthday;
    private String hobby;
    private String address;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;
}