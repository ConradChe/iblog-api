package com.iblog.controller;

import com.iblog.common.ApiResponse;
import com.iblog.entity.User;
import com.iblog.service.UserService;
import com.iblog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (StringUtil.isBlank(phone)) {
            return ApiResponse.buildErrorMessage("请上传手机号");
        } else {
            //判断手机号是否已注册
            List<User> users = userService.queryUser(user);
            if (users.size() > 0) {
                return ApiResponse.buildErrorMessage("该手机号已被注册");
            }
        }
        int i = userService.addUser(user);
        if (i > 0) {
            return ApiResponse.buildSuccessMessage("注册成功");
        } else {
            return ApiResponse.buildErrorMessage("注册失败");
        }
    }

    @PostMapping("/doLogin")
    public ApiResponse doLogin(@RequestBody User user){
        String phone = user.getPhone();
        String password = user.getPassword();
        if (StringUtil.isBlank(phone)){
            return ApiResponse.buildErrorMessage("手机号不可为空");
        }
        if (StringUtil.isBlank(password)){
            return ApiResponse.buildErrorMessage("密码不可为空");
        }
        List<User> users = userService.queryUser(user);
        if (users.size() == 0){
            return ApiResponse.buildErrorMessage("密码错误");
        }
        return ApiResponse.buildSuccessMessage("登录成功");
    }

    @GetMapping("/getUser")
    public ApiResponse getUser(){
        List<User> users = userService.queryUser(null);
        return ApiResponse.buildSuccessResponse(users);
    }
}