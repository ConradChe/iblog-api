package com.iblog.mapper;

import com.iblog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: iblog-api
 * @description: 博客Mapper
 * @author: cgq
 * @create: 2020-01-28 11:35
 **/
@Mapper
public interface BlogMapper {

    /*
    * @Description:  通过用户编号查询博客
    * @Param: [userId]
    * @return: java.util.List<com.iblog.entity.Blog>
    * @Author: cgq
    * @Date: 2020/1/28
    */
    List<Blog> findBlogs(@Param("userId") Integer userId,@Param("blogId") String blogId);

    /*
    * @Description:  通过分类查询博客
    * @Param: [categoryId]
    * @return: java.util.List<com.iblog.entity.Blog>
    * @Author: cgq
    * @Date: 2020/1/28
    */
    List<Blog> findCategoryBlogs(@Param("categoryId") String categoryId);

    int addBlog(Blog blog);

    List<Blog> queryBlogOfUser(Long userId, String categoryId);

    void deleteBlog(String blogId);

    int updateBlog(Blog blog);
}