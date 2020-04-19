package com.iblog.service.impl;

import com.iblog.entity.Blog;
import com.iblog.entity.BlogTag;
import com.iblog.mapper.BlogMapper;
import com.iblog.mapper.BlogTagMapper;
import com.iblog.service.BlogService;
import com.iblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private CommentService commentService;

    @Override
    public List<Blog> findBlogs(String keyword) {
        List<Blog> blogs = blogMapper.findBlogs(keyword);
        return blogs;
    }

    @Override
    public List<Blog> findCategoryBlogs(String categoryId) {
        return blogMapper.findCategoryBlogs(categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBlog(Blog blog) {
        String blogId = blog.getBlogId();
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        int i = blogMapper.addBlog(blog);
        List<Integer> tagList = blog.getTagIds();
        for (Integer tag :
                tagList) {
            blogTag.setTagId(tag);
            blogTagMapper.addBlogTag(blogTag);
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBlog(Blog blog) {
        String blogId = blog.getBlogId();
        //删除博客标签关联
        blogTagMapper.deleteTagOfBlog(blogId);
        //新增博客关联
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        int i = blogMapper.updateBlog(blog);
        List<Integer> tagList = blog.getTagIds();
        for (Integer tag :
                tagList) {
            blogTag.setTagId(tag);
            blogTagMapper.addBlogTag(blogTag);
        }
        return i;
    }

    @Override
    public List<Blog> queryBlogOfUser(Long userId, String categoryId) {
        List<Blog> blogs = blogMapper.queryBlogOfUser(userId,categoryId);
        return blogs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(String blogId) {
        //删除博客
        blogMapper.deleteBlog(blogId);
        //删除博客标签关联
        blogTagMapper.deleteTagOfBlog(blogId);
        //删除博客评论关联
        commentService.deleteComment(blogId);
    }

    @Override
    public int hideBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public List<String> getAllBlogId() {
        return blogMapper.getAllBlogId();
    }

    @Override
    public Blog getBlogById(String blogId) {
        return blogMapper.getBlogById(blogId);
    }

    @Override
    public int updateCommentNum(Blog blog) {
        return blogMapper.updateCommentNum(blog);
    }
}