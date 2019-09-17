package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    //注入articleService
    @Autowired
    private ArticleService articleService;
    /**
    帖子回复功能
     */
    @RequestMapping("reply")
    public String reply(Reply reply){
        articleService.saveReply(reply);
        return "getAritcle";
    }

    /**
     * 帖子评论功能
     * @param comment
     * @return
     */
    @RequestMapping("/comment")
    public String comment(Comment comment){
        articleService.saveComment(comment);
        return "getAritcle";
    }
    /**
     * 展示不同讨论区的帖子信息
     */
    @RequestMapping("findArticleListByZoneId")
    public ModelAndView findArticleListByZoneId(ModelAndView mv, @RequestParam(name = "zoneId",required = true,defaultValue = "1")int id){
        //根据获取的交流区Id查询帖子集合
       List<Article> list =  articleService.findArticleListByZoneId(id);
       mv.addObject("articleList",list);
       mv.addObject("zoneId",id);
       mv.setViewName("index");
       return mv;
    }
    /**
     * 点击帖子,浏览帖子详情
     */
    @RequestMapping("getArticle")
    public ModelAndView getArticle(ModelAndView mv,@RequestParam(name = "articleId",required = true) int id){
        //根据articleId获取comment集合
        List<Comment> commentList = articleService.getArticleByArticleId(id);
        mv.addObject("commentList",commentList);
        mv.setViewName("getArticle");
        return mv;
    }
    /**
     * 创建新的帖子
     */
    @RequestMapping("createNewArticle")
    public String createNewArticle(Article article){
        //根据获取的article对象执行保存操作
        articleService.createNewArticle(article);
        return "redirect:findArticleListByZoneId.do?zoneId="+article.getZoneId();
    }
}
