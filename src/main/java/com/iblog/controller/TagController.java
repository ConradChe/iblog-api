package com.iblog.controller;

import com.iblog.common.ApiResponse;
import com.iblog.entity.Tag;
import com.iblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客标签Controller
 * @author: cgq
 * @create: 2020-01-31 20:24
 **/
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/queryTags")
    public ApiResponse queryTags(){
        List<Tag> tags = tagService.queryTags();
        return ApiResponse.buildSuccessResponse(tags);
    }

}