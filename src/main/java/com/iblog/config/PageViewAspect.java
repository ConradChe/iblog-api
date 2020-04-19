package com.iblog.config;

import com.iblog.service.RedisService;
import com.iblog.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class PageViewAspect {
 
    @Autowired
    private RedisService redisService;
 
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.iblog.annotation.PageView)")
    public void PageViewAspect() {
 
    }
 
    /**
     * 切入处理
     * @param joinPoint
     * @return
     */
    @Around("PageViewAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        Object[] object = joinPoint.getArgs();
        Object blogId = object[0];
        log.info("blogId:{}", blogId);
        Object obj = null;
        try {
            String ipAddr = IpUtils.getIpAddr();
            log.info("ipAddr:{}", ipAddr);
            String key = "blogId_" + blogId;
            // 浏览量存入redis中
            Long num = redisService.add(key,ipAddr);
            if (num == 0) {
                log.info("该ip:{},访问的浏览量已经新增过了", ipAddr);
            }
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}