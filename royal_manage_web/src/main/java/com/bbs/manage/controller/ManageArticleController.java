package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.service.manager_ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("manageArticle")
public class ManageArticleController {

    @Autowired
    private manager_ArticleService manager_articleService;

//    查询所有帖子并分页
    @RequestMapping("/findByPage.do")
    public ModelAndView findAll(@RequestParam(name="page",required=true,defaultValue="1")int page,
                                @RequestParam(name="size",required=true,defaultValue ="6")int size){
        ModelAndView mv = new ModelAndView();

        List<Article> articles = manager_articleService.findByPage(page,size);

        PageInfo pageInfo = new PageInfo(articles);

        mv.addObject("articleMsgs",pageInfo);

        mv.setViewName("ArticlePage");
        return mv;
    }
    //更改帖子屏蔽状态
    @RequestMapping("/changeStatus.do")
    public String changeStatus(int id,int status){
        manager_articleService.articleStatus(id,status);
        manager_articleService.topStatus(id,status);
        return "redirect:/article/findByPage.do";

    }

    //更改帖子置顶状态
    @RequestMapping("/srtTop.do")
    public String setTop(int id,int status){
        manager_articleService.topStatus(id,status);
        return "redirect:/article/findByPage.do";
    }




}
