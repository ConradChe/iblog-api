package com.iblog.controller;

import com.iblog.common.ApiResponse;
import com.iblog.entity.User;
import com.iblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 用户Controller
 * @author: cgq
 * @create: 2020-01-28 13:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody User user) {
        String phone = user.getPhone();
        if (null == phone || "".equals(phone)) {
            return ApiResponse.buildErrorMessage("请上传手机号");
        } else {
            //判断手机号是否已注册
            List<User> users = userService.queryUser(phone);
            if (users.size() > 0) {
                return ApiResponse.buildErrorMessage("该手机号已被注册");
            }
        }
        int i = userService.addUser(user);
        if (i > 0) {
            return ApiResponse.buildSuccessMessage("添加成功");
        } else {
            return ApiResponse.buildErrorMessage("添加失败");
        }
    }
}