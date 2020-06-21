package com.iblog;

import com.iblog.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IblogApiApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
//        User user = new User();
//        user.setName("cgq");
//        user.setPassword("123456");
//        redisService.setString("4dcd3a457081d8551cfaf5f2f1c158bf", JSON.toJSONString(user));
    }

//    @Test
//    void getUser(){
//        String string = redisService.getString("307af227050df19230ed10d99a33380e");
//        User jsonObject = JSON.parseObject(string, User.class);
//        System.out.println(jsonObject.getPassword());
//    }

    @Test
    void getIpTest(){
//        String ipAddr = IpUtils.getIpAddr();
//        System.out.println(ipAddr);
    }

}
