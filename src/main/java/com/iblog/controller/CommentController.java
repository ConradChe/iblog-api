package com.iblog.controller;

import com.iblog.annotation.LoginPass;
import com.iblog.common.ApiResponse;
import com.iblog.entity.Blog;
import com.iblog.entity.Comment;
import com.iblog.entity.User;
import com.iblog.service.BlogService;
import com.iblog.service.CommentService;
import com.iblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *  @author: ConradChe
 *  @Date: 2020/4/11 15:32
 *  @Description: 评论controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    /**
     *  @author: ConradChe
     *  @Date: 2020/4/11 15:41
     *  @Description: 添加评论
     */
    @PostMapping("/insertComment")
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse insertComment(@RequestBody Comment comment, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        //获取文章编号
        String blogId = comment.getBlogId();
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        comment.setCreateTime(new Date());
        comment.setUserId(user.getUserId());
        boolean insert = commentService.insert(comment);
        if (insert){
            //修改文章评论数量
            blogService.updateCommentNum(blog);
            return ApiResponse.buildSuccessMessage("评论成功");
        }else {
            return ApiResponse.buildErrorMessage("评论失败");
        }
    }

    @LoginPass
    @GetMapping("/queryComment")
    public ApiResponse queryComment(@RequestParam("blogId") String blogId){
        //查询所有父评论
        List<Comment> commentList = commentService.queryCommentByBlogId(blogId);
        commentList.forEach(comment -> {
            Long parentId = comment.getCommentId();
            getCommentInfo(comment);
            List<Comment> children = commentService.queryCommentByParentId(parentId);
            children.forEach(apply -> getCommentInfo(apply));
            comment.setChildrenList(children);
        });
        return ApiResponse.buildSuccessResponse(commentList);
    }

    public void getCommentInfo(Comment comment){
        Long userId = comment.getUserId();
        Long byId = comment.getById();
        User user = userService.queryUserNameById(userId);
        if (byId != null){
            User byUser = userService.queryUserNameById(byId);
            comment.setByUser(byUser);
        }
        comment.setUser(user);
    }

}