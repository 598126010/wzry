package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.*;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageHelper;
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
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<Article> findArticleListByZoneId(int id, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
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

    //点击获取热度前三的帖子
    @Override
    public List<Article> findTop1Article() {
         return articleDao.findTop1Article();
    }

    //关键字搜索
    @Override
    public void addUpvoteCount(String articleId) {
        articleDao.addUpvoteCount(articleId);
    }

    @Override
    public void subtractUpvoteCount(String articleId) {
        articleDao.subtractUpvoteCount(articleId);
    }

    @Override
    public Integer findUpvoteCount(String articleId) {
        Integer upvoteCount= articleDao.findUpvoteCount(articleId);
        return upvoteCount;
    }

    /**
     * @return
     */
    @Override
    public List<Zone> findAllZone() {
        return articleDao.findAllZone();
    }

    //关键字搜索
    @Override
    public List<Article> findByKeyWord(String keyWord, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return articleDao.findByKeyWord(keyWord);
    }

    @Override
    public void changeIsreportByArticle(Integer articleId) {
        articleDao.changeIsreportByArticle(articleId);
    }

    //全部帖子统计
    @Override
    public Integer getTotalCount() {
        return articleDao.getTotalCount();
    }




}
