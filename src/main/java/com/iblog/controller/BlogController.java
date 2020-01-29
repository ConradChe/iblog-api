package com.iblog.controller;

import com.iblog.common.ApiResponse;
import com.iblog.entity.Blog;
import com.iblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客Controller
 * @author: cgq
 * @create: 2020-01-28 10:59
 **/
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/findBlogs")
    public ApiResponse findBlogs(@RequestParam(value = "userId",required = false) Integer userId){
        List<Blog> blogs = blogService.findBlogs(userId);
        return ApiResponse.buildSuccessResponse(blogs);
    }

    @GetMapping("/findCategoryBlogs")
    public ApiResponse findCategoryBlogs(@RequestParam("categoryId") String categoryId){
        List<Blog> categoryBlogs = blogService.findCategoryBlogs(categoryId);
        return ApiResponse.buildSuccessResponse(categoryBlogs);
    }
}