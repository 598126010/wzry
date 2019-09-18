package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.*;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void saveReply(Reply reply) {
        System.out.println(reply);
        articleDao.saveReply(reply);
    }

    @Override
    public void saveComment(Comment comment) {
        articleDao.saveComment(comment);
    }

    /**
     * 根据zoneId获取article集合
     * @param id
     * @return
     */
    @Override
    public List<Article> findArticleListByZoneId(int id) {
        return  articleDao.findArticleListByZoneId(id);
    }

    /**
     * 根据ArticleId获取评论信息
     * @param id
     * @return
     */
    @Override
    public List<Comment> getArticleByArticleId(int id) {
       return articleDao.getArticleByArticleId(id);
    }

    /**
     * 调用dao保存帖子
     * @param article
     */
    @Override
    public void createNewArticle(Article article) {
        articleDao.createNewArticle(article);
    }

    /**
     * 根据articleId获取
     * @param id
     * @return
     */
    @Override
    public Article findArticleByArticleId(int id) {
        return  articleDao.findArticleByArticleId(id);
    }

    /**
     * 根据commentId获取reply集合
     * @param commentId
     * @return
     */
    @Override
    public List<Reply> findReplyByCommentId(Integer commentId) {
        return articleDao.findReplyByCommentId(commentId);

    }

    //显示在线用户
    @Override
    public List<UserInfo> findOnlineUser() {
        return articleDao.findOnlineUser();
    }

    //今日帖子统计
    @Override
    public Integer getTodayCount() {
        return articleDao.getTodayCount();
    }
    //举报
    @Override
    public boolean submitReport(Report report) {
        int i = articleDao.submitReport(report);
        if(i==1){
            return true;
        }else{
            return false;
        }

    }

    //全部帖子统计
    @Override
    public Integer getTotalCount() {
        return articleDao.getTotalCount();
    }




}
