package com.iblog.service;

public interface RedisService {

    void setString(String key,String value);

    void setString(String key, String value,int sec);

    String getString(String key);

    void del(String... key);

    Long add(String key,Object... value);

    Long size(String key);
}
