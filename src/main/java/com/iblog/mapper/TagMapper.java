package com.iblog.mapper;

import com.iblog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    List<Tag> queryTags();
}
