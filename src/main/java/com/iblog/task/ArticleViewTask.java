package com.iblog.task;

import com.iblog.entity.Blog;
import com.iblog.mapper.BlogMapper;
import com.iblog.service.BlogService;
import com.iblog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class ArticleViewTask {
 
    @Resource
    private RedisService redisService;
    @Resource
    private BlogService blogService;
    @Resource
    private BlogMapper blogMapper;
 
    // 每天凌晨一点执行
    @Scheduled(cron = "0 0 1 * * ? ")
    @Transactional(rollbackFor=Exception.class)
    public void createHyperLog() {
        log.info("浏览量入库开始");
 
        List<String> list = blogService.getAllBlogId();
        list.forEach(blogId ->{
            // 获取每一篇文章在redis中的浏览量，存入到数据库中
            String key  = "blogId_"+blogId;
            Long view = redisService.size(key);
            if(view>0){
                Blog blog = blogService.getBlogById(blogId);
                Long views = view + blog.getViewNum();
                blog.setViewNum(views);
                int num = blogMapper.updateBlog(blog);
                if (num != 0) {
                    log.info("数据库更新后的浏览量为：{}", views);
                    redisService.del(key);
                }
            }
        });
        log.info("浏览量入库结束");
    }
 
}