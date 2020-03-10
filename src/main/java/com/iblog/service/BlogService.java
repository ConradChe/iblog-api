package com.iblog.service;

import com.iblog.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findBlogs(Integer userId,String blogId);

    List<Blog> findCategoryBlogs(String categoryId);

    int addBlog(Blog blog);
}
