package com.iblog.service.impl;

import com.iblog.entity.Category;
import com.iblog.mapper.CategoryMapper;
import com.iblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客分类Service实现类
 * @author: cgq
 * @create: 2020-01-31 19:43
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> queryCategory(Integer userId) {
        return categoryMapper.queryCategory(userId);
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }
}