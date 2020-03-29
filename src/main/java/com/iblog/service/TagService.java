package com.iblog.service;

import com.iblog.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> queryTags();

    List<Tag> queryTagOfBlog(String blogId);
}
