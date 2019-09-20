package com.bbs.service.impl;

import com.bbs.dao.manager_ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.domain.Word;
import com.bbs.service.manager_ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class manager_ArticleServiceImpl implements manager_ArticleService {

    @Autowired
    private manager_ArticleDao manager_articleDao;

    //查询全部帖子
    @Override
    public List<Article> findByPage(String title, String senderName, int page, int size) {
        PageHelper.startPage(page,size);
        return manager_articleDao.findByPage(title,senderName,page,size);
    }

    //置顶状态更改
    @Override
    public void topStatus(int id, int status) {
        manager_articleDao.topStatus(id,status);
    }

    //帖子屏蔽状态更改
    @Override
    public void articleStatus(int id, int status) {
        manager_articleDao.articleStatus(id,status);
    }

    //查询所有举报帖
    @Override
    public List<Report> findAllRport(int page, int size) {

        PageHelper.startPage(page,size);
        return manager_articleDao.findAllRport(page,size);

    }

    //    查询已处理举报贴
    @Override
    public List<Report> findAllDealedRport(int page, int size) {
        PageHelper.startPage(page,size);
        return manager_articleDao.findAllDealedRport(page,size);
    }

    //更改帖子举报信息（是否举报）
    @Override
    public void isReportStatus(int id, int status) {

        manager_articleDao.isReportStatus(id,status);
    }


    //更改帖子举报处理信息（是否处理）
    @Override
    public void dealReportStatus(int id,int status) {

        manager_articleDao.dealReportStatus(id,status);
    }

    //    查询所有敏感词汇并分页
    @Override
    public List<Word> findAllWords(int page, int size) {

        PageHelper.startPage(page,size);
        return manager_articleDao.findAllWords(page,size);
    }
    //    改变敏感词启用状态
    @Override
    public void changeWordStatus(int id,int status) {
        manager_articleDao.changeWordStatus(id,status);
    }

    //    添加敏感词汇
    @Override
    public void addWords(String word) {
        manager_articleDao.addWords(word);
    }



//    查询相关帖子
    @Override
    public Article findArticleById(int articleId) {
        return manager_articleDao.findArticleById(articleId);
    }

    @Override
    public void deleteWord(int id) {
        manager_articleDao.deleteWord(id);
    }

}
