package com.bbs.service.impl;

import com.bbs.dao.UpvoteDao;
import com.bbs.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpvoteServiceImpl implements UpvoteService {
    @Autowired
    private UpvoteDao upvoteDao;

    @Override
    public Integer findIsUpvote(String articleId, String userName) {
        Integer IsUpvote= upvoteDao.findIsUpvote(articleId,userName);
        return IsUpvote;
    }

    @Override
    public void addUpvote(String articleId, String userName, int i) {
        upvoteDao.addUpvote(articleId,userName,i);
    }

    @Override
    public void changeIsUpvote(String articleId, String userName, int i) {
        upvoteDao.changeIsUpvote(articleId,userName,i);
    }

}