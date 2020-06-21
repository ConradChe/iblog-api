package com.iblog.receive;

import com.iblog.config.RabbitConfig;
import com.iblog.util.PhoneCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: rabbitmq-test
 * @description: 一个生产者一个消费者
 * @author: cgq
 * @create: 2020-06-21 16:48
 **/
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content){
        logger.info("接收处理队列A当中的消息："+content);
    }

    @RabbitHandler
    public void process(Map content){
        String phone = content.get("phone")+"";
        String code = content.get("code")+"";
        //发送短信
        PhoneCode.getPhoneMsg(phone,code);
        logger.info("接收处理队列A当中的消息：【手机号为："+phone+"，验证码为："+code+"】");
    }
}