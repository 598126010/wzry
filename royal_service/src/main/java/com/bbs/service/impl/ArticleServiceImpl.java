package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void saveReply(Reply reply) {
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

    public static void main(String[] args) {
        Date date= new Date();
        System.out.println(date);
    }
}
