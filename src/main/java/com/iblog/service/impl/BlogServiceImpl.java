package com.iblog.service.impl;

import com.iblog.entity.Blog;
import com.iblog.entity.BlogTag;
import com.iblog.entity.Tag;
import com.iblog.mapper.BlogMapper;
import com.iblog.mapper.BlogTagMapper;
import com.iblog.service.BlogService;
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

    @Override
    public List<Blog> findBlogs(Integer userId,String blogId) {
        List<Blog> blogs = blogMapper.findBlogs(userId,blogId);
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
        List<Tag> tagList = blog.getTagList();
        for (Tag tag :
                tagList) {
            Integer tagId = tag.getTagId();
            blogTag.setTagId(tagId);
            blogTagMapper.addBlogTag(blogTag);
        }
        return i;
    }
}