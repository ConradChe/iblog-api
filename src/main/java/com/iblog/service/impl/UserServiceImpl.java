package com.iblog.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> queryUser(User user) {
        return userMapper.queryUser(user);
    }

    @Override
    public User queryUserNameById(Long userId) {
        return userMapper.queryUserNameById(userId);
    }
}