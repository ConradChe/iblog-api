package com.iblog.mapper;

import com.iblog.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogTagMapper {

    int addBlogTag(BlogTag blogTag);
}
