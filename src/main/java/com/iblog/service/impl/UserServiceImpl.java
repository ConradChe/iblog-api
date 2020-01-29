package com.iblog.service.impl;

import com.iblog.entity.User;
import com.iblog.mapper.UserMapper;
import com.iblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 用户Service实现类
 * @author: cgq
 * @create: 2020-01-28 13:31
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> queryUser(String phone) {
        return userMapper.queryUser(phone);
    }
}