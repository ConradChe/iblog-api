package com.iblog.service;

import com.iblog.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findBlogs(Integer userId);

    List<Blog> findCategoryBlogs(String categoryId);
}
