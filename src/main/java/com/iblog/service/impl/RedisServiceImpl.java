package com.iblog.service.impl;

import com.iblog.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: iblog-api
 * @description: Redis
 * @author: cgq
 * @create: 2020-02-23 19:33
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void setString(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    @Override
    public void setString(String key, String value,int sec) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value,sec, TimeUnit.SECONDS);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @Override
    public void del(String... key) {
        redisTemplate.delete(key[0]);
    }

    /**
     * 计数
     * @param key
     * @param value
     */
    @Override
    public Long add(String key, Object... value) {
        return redisTemplate.opsForHyperLogLog().add(key,value);
    }

    /**
     * 获取总数
     * @param key
     */
    @Override
    public Long size(String key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }
}