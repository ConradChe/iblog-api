package com.iblog.controller;

import com.iblog.common.ApiResponse;
import com.iblog.entity.Blog;
import com.iblog.entity.Category;
import com.iblog.service.BlogService;
import com.iblog.service.CategoryService;
import com.iblog.util.IdCreateUtil;
import com.iblog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findBlogs")
    public ApiResponse findBlogs(@RequestParam(value = "userId", required = false) Integer userId) {
        List<Blog> blogs = blogService.findBlogs(userId);
        return ApiResponse.buildSuccessResponse(blogs);
    }

    @GetMapping("/findCategoryBlogs")
    public ApiResponse findCategoryBlogs(@RequestParam("categoryId") String categoryId) {
        List<Blog> categoryBlogs = blogService.findCategoryBlogs(categoryId);
        return ApiResponse.buildSuccessResponse(categoryBlogs);
    }

    @PostMapping("/addBlog")
    public ApiResponse addBlog(@RequestBody Blog blog) {
        //获取参数
        String categoryId = blog.getCategoryId();
        String categoryName = blog.getCategoryName();

        String blogId = IdCreateUtil.getCode("B");
        Date date = new Date();
        Integer userId = 1;
        String userNickName = "Conrad";
        blog.setBlogId(blogId);
        blog.setUserId(userId);
        blog.setUserNickname(userNickName);
        blog.setCreateTime(date);
        blog.setUpdateTime(date);

        if (StringUtil.isBlank(categoryId)) {
            categoryId = IdCreateUtil.getCode("B");
            blog.setCategoryId(categoryId);
            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            category.setUserId(userId);
            category.setCreateTime(date);
            categoryService.addCategory(category);
        }
        int i = blogService.addBlog(blog);
        if (i > 0) {
            return ApiResponse.buildSuccessMessage("保存成功");
        } else {
            return ApiResponse.buildErrorMessage("保存失败");
        }

    }
}