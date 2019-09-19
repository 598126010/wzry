package com.bbs.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.ArticleService;
import com.bbs.service.UpvoteService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("upvote")
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;
    @Autowired
    private ArticleService articleService;


    @RequestMapping("/changeIsUpvote")
    @ResponseBody
    public Integer changeIsUpvote(@Param("articleId")String articleId, HttpServletRequest request){
        UserInfo user=(UserInfo) request.getSession().getAttribute("user");
/*        User user = new User();
        user.setUsername("admin");*/
        Integer upvoteCount=null;
        Integer isUpvote=upvoteService.findIsUpvote(articleId,user.getUserName());
        if (isUpvote==null){
            upvoteService.addUpvote(articleId,user.getUserName(),1);
            isUpvote=0;
        }
        if(isUpvote == 0){
            articleService.addUpvoteCount(articleId);
            upvoteService.changeIsUpvote(articleId,user.getUserName(),1);
        }else {
            articleService.subtractUpvoteCount(articleId);
            upvoteService.changeIsUpvote(articleId,user.getUserName(),0);
        }

        upvoteCount=articleService.findUpvoteCount(articleId);
        return upvoteCount;
    }

    @RequestMapping("/findByUserNameAndArticleId")
    @ResponseBody
    public Integer findByUserNameAndArticleId(@Param("articleId")String articleId,HttpServletRequest request){
        UserInfo user=(UserInfo) request.getSession().getAttribute("user");
/*        User user = new User();
        user.setUsername("admin");*/
        Integer isUpvote=upvoteService.findIsUpvote(articleId,user.getUserName());
        return isUpvote;
    }
}
