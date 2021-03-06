package com.iblog.service;

import com.iblog.entity.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findBlogs(String keyword);

    List<Blog> findCategoryBlogs(String categoryId);

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    List<Blog> queryBlogOfUser(Long userId, String categoryId);

    void deleteBlog(String blogId);

    int hideBlog(Blog blog);

    List<String> getAllBlogId();

    Blog getBlogById(String blogId);

    int updateCommentNum(Blog blog);

    List<Blog> queryBlogByStatus(Integer blogStatus);

    Blog queryBlogInfo(String blogId);

    int updateBlogStatus(Blog blog);
}
