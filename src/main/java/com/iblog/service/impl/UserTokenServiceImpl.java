package com.iblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.iblog.entity.User;
import com.iblog.entity.UserToken;
import com.iblog.mapper.UserTokenMapper;
import com.iblog.service.RedisService;
import com.iblog.service.UserService;
import com.iblog.service.UserTokenService;
import com.iblog.shiro.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: iblog-api
 * @description: 用户Token Service
 * @author: cgq
 * @create: 2020-02-15 19:25
 **/
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {


    /**
     * 一天后过期 单位秒
     */
    private final static int EXPIRE = 60*60*24;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Override
    public String createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        User user = userService.selectById(userId);
        //保存token到redis
        redisService.setString(token, JSON.toJSONString(user),EXPIRE);

        //查询数据库，是否已生成token
        UserToken userToken = userTokenMapper.queryUserToken(userId);
        if (userToken == null) {
            userToken = new UserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setCreateTime(now);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //保存token
            userTokenMapper.insertUserToken(userToken);
        } else {
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            //更新token
            userTokenMapper.updateUserToken(userToken);
        }
        return token;
    }
}