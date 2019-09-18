package com.bbs.service.impl;

import com.bbs.dao.manager_ArticleDao;
import com.bbs.domain.Article;
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
    public List<Article> findByPage(int page,int size) {

        PageHelper.startPage(page,size);
        return manager_articleDao.findByPage(page,size);
    }

//置顶状态更改
    @Override
    public void topStatus(int id, int status) {
        manager_articleDao.topStatus(id,status);
    }

//帖子置顶状态更改
    @Override
    public void articleStatus(int id, int status) {
        manager_articleDao.articleStatus(id,status);
    }

}
