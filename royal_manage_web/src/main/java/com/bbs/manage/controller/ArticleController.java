package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.domain.Word;
import com.bbs.service.manager_ArticleService;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private manager_ArticleService manager_articleService;

    //    查询所有帖子并分页+模糊查询
    @RequestMapping(value="/findByPage.do")
    public ModelAndView findAll(@RequestParam(name="title",required=true,defaultValue="")String title,
                                @RequestParam(name="senderName",required=true,defaultValue="")String senderName,
                                @RequestParam(name="page",required=true,defaultValue="1")int page,
                                @RequestParam(name="size",required=true,defaultValue ="5")int size,
                                HttpServletRequest request){

//        String temp = request.getParameter("title");
//        if(temp!=null){
//            try {
//                title = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
        ModelAndView mv = new ModelAndView();
        List<Article> articles = manager_articleService.findByPage(title,senderName,page,size);
        PageInfo pageInfo = new PageInfo(articles);

        mv.addObject("articleMsgs",pageInfo);
        mv.addObject("title",title);
        mv.addObject("senderName",senderName);
        mv.setViewName("ArticlePage");

        return mv;
    }
    //更改帖子屏蔽状态
    @RequestMapping("/changeStatus.do")
    public String changeStatus(int id,int status,int page,Model model,
                               @RequestParam(name="title",required=true,defaultValue="")String title,
                               @RequestParam(name="senderName",required=true,defaultValue="")String senderName){
        manager_articleService.isReportStatus(id,status);
        manager_articleService.topStatus(id,status);
        model.addAttribute("title",title);
        model.addAttribute("senderName",senderName);
        return "redirect:/article/findByPage.do?page="+page;

    }

    //更改帖子置顶状态
    @RequestMapping("/setTop.do")
    public String setTop(int id,int status,int page){
        manager_articleService.topStatus(id,status);
        return "redirect:/article/findByPage.do?page="+page;
    }

    //    查询所有未处理举报帖子并分页
    @RequestMapping("/reportPage.do")
    public ModelAndView findAllRport(@RequestParam(name="page",required=true,defaultValue="1")int page,
                                     @RequestParam(name="size",required=true,defaultValue ="5")int size){
        ModelAndView mv = new ModelAndView();

        List<Report> Reports = manager_articleService.findAllRport(page,size);

        PageInfo pageInfo = new PageInfo(Reports);

        mv.addObject("reportMsgs",pageInfo);

        mv.setViewName("ReportPage");
        return mv;
    }
    //    查询已处理举报贴并分页
    @RequestMapping("/dealedReport.do")
    public ModelAndView findAllDealedRport(@RequestParam(name="page",required=true,defaultValue="1")int page,
                                           @RequestParam(name="size",required=true,defaultValue ="5")int size){
        ModelAndView mv = new ModelAndView();

        List<Report> Reports = manager_articleService.findAllDealedRport(page,size);

        PageInfo pageInfo = new PageInfo(Reports);

        mv.addObject("reportMsgs",pageInfo);
//        mv.addObject()

        mv.setViewName("dealedReportPage");
        return mv;
    }
    //    举报处理
    @RequestMapping("/changeReport.do")
    public String changeReport(int id,
                               int status,
                               int page){

        manager_articleService.dealReportStatus(id,status);
        manager_articleService.isReportStatus(id,status);
//        model.addAttribute("page",page);

        return "redirect:/article/dealedReport.do?&page"+page;
    }
    //    查询所有敏感词汇并分页
    @RequestMapping("/SensitiveWordsPage.do")
    public ModelAndView findAllWords(@RequestParam(name="page",required=true,defaultValue="1")int page,
                                     @RequestParam(name="size",required=true,defaultValue ="5")int size){
        ModelAndView mv = new ModelAndView();

        List<Word> Words = manager_articleService.findAllWords(page,size);

        PageInfo pageInfo = new PageInfo(Words);

        mv.addObject("wordsMsgs",pageInfo);

        mv.setViewName("SensitiveWordsPage");
        return mv;
    }

    //    改变敏感词启用状态
    @RequestMapping("/wordStatus.do")
//    @ResponseBody
    public String changeWordStatus(int id,
                                   int status,
                                   @RequestParam("page")int page){

        manager_articleService.changeWordStatus(id,status);

        return "redirect:/article/SensitiveWordsPage.do";
    }

    //    添加敏感词
    @RequestMapping("/addWords.do")
    public String addWords(@RequestParam("addword")String word){

        manager_articleService.addWords(word);

        return "redirect:/article/SensitiveWordsPage.do";

    }

    //    删除敏感词
    @RequestMapping("/deleteWord.do")
    public String deleteWord(int id,int page){

        manager_articleService.deleteWord(id);

        return "redirect:/article/SensitiveWordsPage.do?page="+page;
    }

    //    查询相关帖子
    @RequestMapping("/findArticleById.do")
    @ResponseBody
    public Article findArticleById(@RequestParam(name = "articleId",required = true) int articleId){
        Article article=manager_articleService.findArticleById(articleId);
        return article;
    }

}
