package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Reply;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImol implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public void saveComment(Reply reply) {
        articleDao.saveComment(reply);
    }
}
