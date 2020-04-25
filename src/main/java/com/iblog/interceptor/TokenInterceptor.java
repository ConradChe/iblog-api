package com.iblog.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.iblog.annotation.AdminOperate;
import com.iblog.annotation.LoginPass;
import com.iblog.common.ApiResponse;
import com.iblog.entity.User;
import com.iblog.service.RedisService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: iblog-api
 * @description: Token拦截
 * @author: cgq
 * @create: 2020-02-16 16:57
 **/
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        LoginPass annotation = method.getAnnotation(LoginPass.class);
        AdminOperate adminOperate = method.getAnnotation(AdminOperate.class);

        //判断是否需要拦截的方法
        if (annotation != null) {
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("loginToken");
        ApiResponse apiResponse = new ApiResponse();
        //判断token是否存在
        if (token == null) {
            apiResponse = new ApiResponse(HttpStatus.SC_UNAUTHORIZED, "Token不存在");
            String json = new Gson().toJson(apiResponse);
            response.getWriter().print(json);
            return false;
        }
        //通过token在redis中获取用户信息
        String string = redisService.getString(token);
        User user = JSON.parseObject(string, User.class);
        //判断token是否正确
        if (user == null) {
            apiResponse = new ApiResponse(HttpStatus.SC_UNAUTHORIZED, "Token不正确");
            String json = new Gson().toJson(apiResponse);
            response.getWriter().print(json);
            return false;
        }
        if (adminOperate != null && user.getRole()!=1){
            apiResponse = new ApiResponse(5001, "此操作需要管理员权限");
            String json = new Gson().toJson(apiResponse);
            response.getWriter().print(json);
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }
}