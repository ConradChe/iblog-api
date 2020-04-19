package com.iblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iblog.annotation.LoginPass;
import com.iblog.annotation.LoginRequired;
import com.iblog.annotation.PageView;
import com.iblog.common.ApiResponse;
import com.iblog.entity.Blog;
import com.iblog.entity.Category;
import com.iblog.entity.Tag;
import com.iblog.entity.User;
import com.iblog.service.*;
import com.iblog.util.IdCreateUtil;
import com.iblog.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RedisService redisService;

    @LoginPass
    @GetMapping("/findBlogs")
    public ApiResponse findBlogs(@RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                 @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit) {

        PageHelper.startPage(page,limit);
        List<Blog> blogs = blogService.findBlogs(keyword);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        List<Blog> list = pageInfo.getList();
        list.stream().forEach(blog -> {
            Long blogUserId = blog.getUserId();
            String blogId1 = blog.getBlogId();
            //通过id查询作者
            User user = userService.selectById(blogUserId);
            List<Tag> tags = tagService.queryTagOfBlog(blogId1);
            blog.setUser(user);
            blog.setTagList(tags);
        });
        long total = pageInfo.getTotal();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.SC_OK);
        apiResponse.setData(list);
        apiResponse.setTotal(total);
        return apiResponse;
    }

    /*
     * @Description:  查询用户的文章
     * @Param: [blogStatus, request]
     * @return: com.iblog.common.ApiResponse
     * @Author: cgq
     * @Date: 2020/3/21
     */
    @GetMapping("/queryBlogOfUser")
    public ApiResponse queryBlogOfUser(@RequestParam(value = "categoryId")String categoryId,
                                       HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        Long userId = user.getUserId();
        List<Blog> blogs = blogService.queryBlogOfUser(userId,categoryId);
        blogs.stream().forEach(blog -> {
            String blogId1 = blog.getBlogId();
            List<Tag> tags = tagService.queryTagOfBlog(blogId1);
            blog.setTagList(tags);
        });
        return ApiResponse.buildSuccessResponse(blogs);
    }

    @LoginPass
    @GetMapping("/findCategoryBlogs")
    public ApiResponse findCategoryBlogs(@RequestParam("categoryId") String categoryId) {
        List<Blog> categoryBlogs = blogService.findCategoryBlogs(categoryId);
        return ApiResponse.buildSuccessResponse(categoryBlogs);
    }

    @LoginRequired
    @PostMapping("/addBlog")
    public ApiResponse addBlog(@RequestBody Blog blog, HttpServletRequest request) {
        //获取参数
        User user = (User) request.getAttribute("user");
        String categoryId = blog.getCategoryId();
        String categoryName = blog.getCategoryName();

        String blogId = IdCreateUtil.getCode("B");
        Date date = new Date();
        Long userId = user.getUserId();
        String userNickName = user.getNickname();
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
            categoryService.addCategory(category);
        }
        int i = blogService.addBlog(blog);
        if (i > 0) {
            return ApiResponse.buildSuccessMessage("保存成功");
        } else {
            return ApiResponse.buildErrorMessage("保存失败");
        }

    }

    @PostMapping("/updateBlog")
    public ApiResponse updateBlog(@RequestBody Blog blog, HttpServletRequest request){
        //获取参数
        User user = (User) request.getAttribute("user");
        String categoryId = blog.getCategoryId();
        String categoryName = blog.getCategoryName();

        Long userId = user.getUserId();
        String userNickName = user.getNickname();
        blog.setUserId(userId);
        blog.setUserNickname(userNickName);

        if (StringUtil.isBlank(categoryId)) {
            categoryId = IdCreateUtil.getCode("B");
            blog.setCategoryId(categoryId);
            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            category.setUserId(userId);
            categoryService.addCategory(category);
        }

        int i = blogService.updateBlog(blog);
        if (i > 0) {
            return ApiResponse.buildSuccessMessage("修改成功");
        } else {
            return ApiResponse.buildErrorMessage("修改失败");
        }
    }

    @PostMapping("/hideBlog")
    public ApiResponse hideBlog(@RequestBody Blog blog){
        int result = blogService.hideBlog(blog);
        if (result>0){
            return ApiResponse.buildSuccessMessage("修改成功");
        }else {
            return ApiResponse.buildErrorMessage("修改失败");
        }
    }

    @DeleteMapping("/deleteBlog/{blogId}")
    public ApiResponse deleteBlog(@PathVariable("blogId") String blogId){
        blogService.deleteBlog(blogId);
        return ApiResponse.buildSuccessMessage("删除成功");
    }

    @PageView
    @GetMapping("/setBlogView")
    @LoginPass
    public ApiResponse setBlogView(@RequestParam(value = "blogId")String blogId){
        log.info("blogId={}",blogId);
        String key = "blogId_"+blogId;
        Long view = redisService.size(key);
        log.info("redis 缓存中浏览数：{}", view);
        return ApiResponse.buildSuccessMessage("success");
    }
}