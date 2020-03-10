package com.iblog.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.iblog.annotation.LoginPass;
import com.iblog.annotation.LoginRequired;
import com.iblog.common.ApiResponse;
import com.iblog.entity.User;
import com.iblog.entity.UserToken;
import com.iblog.service.UserService;
import com.iblog.service.UserTokenService;
import com.iblog.shiro.TokenGenerator;
import com.iblog.util.StringUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserTokenService userTokenService;

    @LoginPass
    @PostMapping("/addUser")
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse addUser(@RequestBody User user) {
        String phone = user.getPhone();
        String password = user.getPassword();
        String nickname = user.getNickname();
        if (StringUtil.isBlank(phone)) {
            return ApiResponse.buildErrorMessage("手机号不可为空");
        }else if (StringUtil.isBlank(password)){
            return ApiResponse.buildErrorMessage("密码不可为空");
        }else if (StringUtil.isBlank(nickname)){
            return ApiResponse.buildErrorMessage("昵称不可为空");
        }else {
            //判断手机号是否已注册
            List<User> users = userService.queryUser(user);
            if (users.size() > 0) {
                return ApiResponse.buildErrorMessage("该手机号已被注册");
            }
        }
        String salt = RandomStringUtils.randomAlphanumeric(8);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setLastLoginTime(date);
        user.setSalt(salt);
        user.setPassword(new Sha256Hash(password, salt).toHex());
        boolean i = userService.insert(user);
        if (i) {
            //生成token，并保存到数据库
            String token = userTokenService.createToken(user.getUserId());
            Map paramMap = new HashMap();
            paramMap.put("loginToken",token);
            paramMap.put("user",user);
            return ApiResponse.buildSuccessResponse(paramMap);
        } else {
            return ApiResponse.buildErrorMessage("注册失败");
        }
    }

    @LoginPass
    @PostMapping("/doLogin")
    public ApiResponse doLogin(@RequestBody User user) {
        String phone = user.getPhone();
        String password = user.getPassword();
        if (StringUtil.isBlank(phone)) {
            return ApiResponse.buildErrorMessage("手机号不可为空");
        }
        if (StringUtil.isBlank(password)) {
            return ApiResponse.buildErrorMessage("密码不可为空");
        }
        List<User> users = userService.queryUser(user);
        User user1 = users.get(0);
        if (users == null || !user1.getPassword().equals(new Sha256Hash(password,user1.getSalt()).toHex())) {
            return ApiResponse.buildErrorMessage("密码错误");
        }

        user1.setSalt(null);
        user1.setPassword(null);

        //生成token，并保存到数据库
        String token = userTokenService.createToken(user1.getUserId());
        Map paramMap = new HashMap();
        paramMap.put("loginToken",token);
        paramMap.put("user",user1);
        return ApiResponse.buildSuccessResponse(paramMap);
    }

    @LoginRequired
    @RequestMapping("/logout")
    public ApiResponse logout(@RequestParam(value = "userId", required = false) Long userId){
        UserToken userToken = userTokenService.selectOne(new EntityWrapper<UserToken>().eq("user_id", userId));
        try {
            if (userToken != null) {
                String token = TokenGenerator.generateValue();
                userToken.setToken(token);
                userToken.setExpireTime(new Date());
                userToken.setUpdateTime(new Date());
                userTokenService.updateById(userToken);
            }
        }catch (Exception e){
            System.out.println("退出登录, 更新token失败！");
        }
        return ApiResponse.buildSuccessMessage("退出成功");
    }

    @GetMapping("/queryUserInfo")
    public ApiResponse queryUserInfo(HttpServletRequest request) {
        User user1 = (User) request.getAttribute("user");
        User user = new User();
        user.setUserId(user1.getUserId());
        List<User> users = userService.queryUser(user);
        return ApiResponse.buildSuccessResponse(users);
    }
    @PostMapping("/updateUser")
    public ApiResponse updateUser(@RequestBody User user, HttpServletRequest request){
        User user1 = (User) request.getAttribute("user");
        user.setUserId(user1.getUserId());
        userService.updateById(user);
        return ApiResponse.buildSuccessMessage("修改成功");
    }

}