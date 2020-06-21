package com.iblog.service;

/**
 * @program: iblog-api
 * @description:
 * @author: cgq
 * @create: 2020-06-21 20:11
 **/
public interface MessageService {
    void sendMsg(String phone,String code);
}