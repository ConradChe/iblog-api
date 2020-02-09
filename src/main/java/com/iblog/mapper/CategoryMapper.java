package com.iblog.mapper;

import com.iblog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> queryCategory(@Param("userId") Integer userId);

    int addCategory(Category category);
}
