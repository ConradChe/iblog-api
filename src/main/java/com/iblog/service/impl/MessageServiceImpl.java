package com.iblog.service.impl;

import com.iblog.producer.MsgProducer;
import com.iblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: iblog-api
 * @description:
 * @author: cgq
 * @create: 2020-06-21 20:12
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MsgProducer msgProducer;
    @Override
    public void sendMsg(String phone, String code) {
        //调用消息生产者
        msgProducer.sendMsg(phone,code);
    }
}