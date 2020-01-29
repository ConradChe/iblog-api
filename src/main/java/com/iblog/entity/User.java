package com.iblog.entity;

/**
 * @program: iblog-api
 * @description: 用户实体类
 * @author: cgq
 * @create: 2020-01-28 12:43
 **/
public class User {
    private Integer userId;
    private String phone;
    private String password;
    private String nickname;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}