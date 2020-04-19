package com.iblog.service;

import com.baomidou.mybatisplus.service.IService;
import com.iblog.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    int addUser(User user);

    List<User> queryUser(User user);

    User queryUserNameById(Long userId);
}
