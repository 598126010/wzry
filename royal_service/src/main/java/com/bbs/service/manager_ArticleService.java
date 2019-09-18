package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

public interface manager_ArticleService {

    //    查询所有帖子信息
    List<Article> findByPage(int page,int size);

    //更改置顶状态
    void topStatus(int id, int status);

    //    更改帖子状态
    void articleStatus(int id, int status);
}
