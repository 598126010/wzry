package com.bbs.controller;

import com.bbs.domain.*;
import com.bbs.service.ArticleService;
import com.bbs.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    //注入articleService
    @Autowired
    private ArticleService articleService;
    //注入wordSerivce
    @Autowired
    private WordService wordService;

    /**
     * 帖子回复功能
     */
    @RequestMapping("/reply")
    public String reply(Reply reply, int articleId) {
        articleService.saveReply(reply);
        return "redirect:getArticle.do?articleId=" + articleId;
    }

    /**
     * 帖子评论功能
     *
     * @param comment
     * @return
     */
    @RequestMapping("/comment")
    public String comment(Comment comment) {
        articleService.saveComment(comment);
        return "redirect:getArticle.do?articleId=" + comment.getArticleId();
    }

    /**
     * 展示不同讨论区的帖子信息
     */
    @RequestMapping("/findArticleListByZoneId.do")
    public ModelAndView findArticleListByZoneId(ModelAndView mv, @RequestParam(name = "zoneId", required = true, defaultValue = "1") int id) {
        //根据获取的交流区Id查询帖子集合
        List<Article> list = articleService.findArticleListByZoneId(id);
        //获取敏感词集合
        List<Word> wordList = wordService.findAllWord();
        //遍历敏感词
        for (Word word : wordList) {
            //遍历内容
            for (Article article : list) {
                if (word.getStatus() == 0) {
                    //过滤内容
                    if (article.getContent().contains(word.getWord())) {
                        String content = article.getContent();
                        System.out.println(content);
                        String keyWord = word.getWord();
                        content = content.replace(content.substring(content.indexOf(keyWord), keyWord.indexOf(content) + keyWord.length()), "***");
                        System.out.println(content);
                        article.setContent(content);
                    }
                    //过滤标题
                    if (article.getTitle().contains(word.getWord())) {
                        String title = article.getTitle();
                        String keyWord = word.getWord();
                        title = title.replace(title.substring(title.indexOf(keyWord), keyWord.indexOf(title) + keyWord.length()), "***");
                        article.setTitle(title);
                    }
                }
            }

        }

        //显示在线用户
        List<UserInfo> onlineUsers = articleService.findOnlineUser();

        mv.addObject("onlineUser", onlineUsers);

        mv.addObject("articleList", list);
        mv.addObject("zoneId", id);
        mv.setViewName("/index");
        return mv;
    }

    /**
     * 点击帖子,浏览帖子详情
     */
    @RequestMapping("/getArticle")
    public ModelAndView getArticle(ModelAndView mv, @RequestParam(name = "articleId", required = true) int id) {
        Article article = articleService.findArticleByArticleId(id);
        //根据articleId获取comment集合
        List<Comment> commentList = articleService.getArticleByArticleId(id);
        for (Comment comment : commentList) {
            Integer commentId = comment.getCommentId();
            List<Reply> replyList = articleService.findReplyByCommentId(commentId);
            comment.setReplies(replyList);
        }
        //获取敏感词集合
        List<Word> wordList = wordService.findAllWord();
        //遍历敏感词
        for (Word word : wordList) {
            //遍历内容
            if (word.getStatus() == 0) {
                for (Comment comment : commentList) {
                    //过滤评论
                    if (comment.getCommentContent().contains(word.getWord())) {
                        String content = comment.getCommentContent();
                        String keyWord = word.getWord();
                        content = content.replace(content.substring(content.indexOf(keyWord), keyWord.indexOf(content) + keyWord.length()), "***");
                        comment.setCommentContent(content);
                    }
                    List<Reply> replies = comment.getReplies();
                    //过滤回复
                    for (Reply reply : replies) {
                            if (reply.getReplyContent().contains(word.getWord())) {
                                //获取回复信息
                                String content = reply.getReplyContent();
                                String keyWord = word.getWord();
                                content = content.replace(content.substring(content.indexOf(keyWord), keyWord.indexOf(content) + keyWord.length()), "***");
                                reply.setReplyContent(content);
                            }
                    }
                    //过滤article
                    if (article.getContent().contains(word.getWord())) {
                        String content = article.getContent();
                        String KeyWord = word.getWord();
                        content = content.replace(content.substring(content.indexOf(KeyWord),content.indexOf(KeyWord)+KeyWord.length()),"***");
                        article.setContent(content);
                    }
                }
            }
        }

                    //将article对象传入request域中
                    mv.addObject("article", article);
                    mv.addObject("commentList", commentList);
                    mv.setViewName("getArticle");
                    return mv;
                }
    /**
     * 创建新的帖子
     */
    @RequestMapping("/createNewArticle")
    public String createNewArticle(Article article){
        //根据获取的article对象执行保存操作
        articleService.createNewArticle(article);
        return "redirect:findArticleListByZoneId.do?zoneId="+article.getZoneId();
    }

    //今日帖子统计
    @RequestMapping("/getTodayCount")
    @ResponseBody
    public Integer getTodayCount(){
        Integer todayCount = articleService.getTodayCount();
        return todayCount;
    }


    //全部帖子统计
    @RequestMapping("/getTotalCount")
    @ResponseBody
    public Integer getTotalCount(){
        Integer totalCount = articleService.getTotalCount();
        return totalCount;
    }
    //获取举报
    @RequestMapping("/report")
    @ResponseBody
    public ResultInfo report(@RequestBody Report report){
        System.out.println(report);
        report.setReportStatus(1);
        boolean result = articleService.submitReport(report);
//        mv.setViewName("redirect:getArticle.do?articleId="+report.getArticleId());
        return new ResultInfo("",result);
//        return "redirect:getArticle.do?articleId="+report.getArticleId();
    }


}
