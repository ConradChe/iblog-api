package com.iblog.service;

import com.iblog.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> queryUser(String phone);
}
