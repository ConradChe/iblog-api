package com.iblog.service.impl;

import com.iblog.entity.Tag;
import com.iblog.mapper.TagMapper;
import com.iblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 标签Service实现类
 * @author: cgq
 * @create: 2020-01-31 20:29
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> queryTags() {
        return tagMapper.queryTags();
    }
}