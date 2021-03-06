package com.iblog.service;

import com.iblog.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategory(Long userId);

    int addCategory(Category category);
}
