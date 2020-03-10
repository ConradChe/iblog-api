package com.iblog.controller;

import com.iblog.annotation.LoginRequired;
import com.iblog.common.ApiResponse;
import com.iblog.entity.Category;
import com.iblog.entity.User;
import com.iblog.service.CategoryService;
import com.iblog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客分类Controller
 * @author: cgq
 * @create: 2020-01-31 19:37
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @LoginRequired
    @GetMapping("/queryCategory")
    public ApiResponse queryCategory(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        Long userId = user.getUserId();
        if (StringUtil.isBlank(""+userId)){
            return ApiResponse.buildErrorMessage("请上传用户编号");
        }
        List<Category> categories = categoryService.queryCategory(userId);
        return ApiResponse.buildSuccessResponse(categories);
    }
}