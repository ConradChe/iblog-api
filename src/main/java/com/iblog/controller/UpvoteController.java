package com.iblog.controller;

import com.iblog.annotation.LoginPass;
import com.iblog.common.ApiResponse;
import com.iblog.entity.Upvote;
import com.iblog.entity.User;
import com.iblog.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @program: iblog-api
 * @description: 点赞controller
 * @author: cgq
 * @create: 2020-04-26 20:26
 **/
@RestController
@RequestMapping("/upvote")
public class UpvoteController {

    @Autowired
    private UpvoteService upvoteService;

    /**
     *  @author: ConradChe
     *  @Date: 2020/4/26 21:15
     *  @Description: 点赞
     */
    @GetMapping("/doUpvote")
    public ApiResponse doUpvote(@RequestParam String blogId, HttpServletRequest request){
        Upvote upvote = new Upvote();
        User user = (User) request.getAttribute("user");
        Long userId = user.getUserId();
        upvote.setUserId(userId);
        upvote.setCreateTime(new Date());
        upvote.setBlogId(blogId);
        int i = upvoteService.insertUpvote(upvote);
        if (i>0){
            return ApiResponse.buildSuccessMessage("点赞成功");
        }else {
            return ApiResponse.buildErrorMessage("点赞失败");
        }
    }

    /**
     *  @author: ConradChe
     *  @Date: 2020/4/26 21:17
     *  @Description: 取消点赞
     */
    @GetMapping("/cancleUpvote")
    public ApiResponse cancleUpvote(@RequestParam String blogId,HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        Long userId = user.getUserId();
        int i = upvoteService.deleteUpvote(blogId, userId);
        if (i>0){
            return ApiResponse.buildSuccessMessage("取消点赞成功");
        }else {
            return ApiResponse.buildErrorMessage("取消点赞失败");
        }
    }

    /**
     *  @author: ConradChe
     *  @Date: 2020/4/30 17:48
     *  @Description: 查询文章点赞数
     */
    @LoginPass
    @GetMapping("/selectUpvoteCountById")
    public ApiResponse selectUpvoteCountById(@RequestParam String blogId){
        Map map = upvoteService.selectUpvoteCountById(blogId);
        return ApiResponse.buildSuccessResponse(map);
    }

    /**
     *  @author: ConradChe
     *  @Date: 2020/4/30 17:55
     *  @Description: 查询用户是否点赞
     */
    @GetMapping("/selectUpvoteById")
    public ApiResponse selectUpvoteById(@RequestParam String blogId,HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        Long userId = user.getUserId();
        Long aLong = upvoteService.selectUpvoteById(blogId, userId);
        return ApiResponse.buildSuccessResponse(aLong);
    }
}