package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.domain.Word;

import java.util.List;

public interface manager_ArticleService {

    //查询所有帖子信息
    List<Article> findByPage(String title,String senderName,int page,int size);

    //更改置顶状态
    void topStatus(int id, int status);

    //更改帖子屏蔽状态
    void articleStatus(int id, int status);

    //查询所有举报帖子信息
    List<Report> findAllRport(int page, int size);

    //更改帖子举报信息（是否举报）
    void isReportStatus(int id, int status);

    //更改帖子举报处理信息（是否处理）
    void dealReportStatus(int id,int status);

    //    查询所有敏感词汇并分页
    List<Word> findAllWords(int page, int size);

    //    改变敏感词启用状态
    void changeWordStatus(int id,int status);

    //    添加敏感词
    void addWords(String word);

    //    查询已处理举报贴
    List<Report> findAllDealedRport(int page, int size);

    //查询相关帖子
    Article findArticleById(int articleId);

    //删除敏感词
    void deleteWord(int id);
}
