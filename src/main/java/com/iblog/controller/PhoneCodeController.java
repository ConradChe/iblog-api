package com.iblog.controller;

import com.iblog.annotation.LoginPass;
import com.iblog.common.ApiResponse;
import com.iblog.service.MessageService;
import com.iblog.service.RedisService;
import com.iblog.util.PhoneCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: iblog-api
 * @description:
 * @author: cgq
 * @create: 2020-06-21 18:48
 **/
@RestController
@RequestMapping("/code")
@Slf4j
public class PhoneCodeController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private MessageService messageService;

    @LoginPass
    @GetMapping("/getPhoneCode")
    public ApiResponse getPhoneCode(String phone){
        if (phone==null || phone.isEmpty()){
            return ApiResponse.buildErrorMessage("请上传手机号");
        }
        //生成验证码
        String code = PhoneCode.vcode();
        //将code存入redis，存储五分钟
        redisService.setString(phone,code,5*60);
        //将验证码放入消息队列，等待消费者消费消息
        messageService.sendMsg(phone,code);
        return ApiResponse.buildSuccessMessage("短信验证码已发送");
    }
}