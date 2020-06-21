package com.iblog.producer;

import com.iblog.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: rabbitmq-test
 * @description:
 * @author: cgq
 * @create: 2020-06-21 16:15
 **/
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    public MsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_A,content,correlationData);
    }

    public void sendMsg(String phone,String code){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Map map = new HashMap();
        map.put("phone", phone);
        map.put("code", code);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_A,map,correlationData);
    }

    /**
     *  @author: ConradChe
     *  @Date: 2020/6/21 16:20
     *  @Description: 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("回调id："+correlationData);
        if (b){
            logger.info("消息成功消费");
        }else {
            logger.info("消息消费失败"+s);
        }
    }
}