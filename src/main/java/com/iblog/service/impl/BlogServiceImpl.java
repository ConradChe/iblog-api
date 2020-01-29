package com.iblog.service.impl;

import com.iblog.entity.Blog;
import com.iblog.mapper.BlogMapper;
import com.iblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客Service实现类
 * @author: cgq
 * @create: 2020-01-28 11:32
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findBlogs(Integer userId) {
        List<Blog> blogs = blogMapper.findBlogs(userId);
        return blogs;
    }

    @Override
    public List<Blog> findCategoryBlogs(String categoryId) {
        return blogMapper.findCategoryBlogs(categoryId);
    }
}