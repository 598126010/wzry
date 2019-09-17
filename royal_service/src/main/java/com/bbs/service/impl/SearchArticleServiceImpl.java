package com.bbs.service.impl;

import com.bbs.dao.SearchArticleDao;
import com.bbs.domain.Article;
import com.bbs.service.SearchArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class SearchArticleServiceImpl implements SearchArticleService {
    @Autowired
    private SearchArticleDao searchArticleDao;


    @Override
    public List<Article> searchArticleByKeyWord(String keyword, int page, int size) throws UnsupportedEncodingException {
        return searchArticleDao.searchArticleByKeyWord(keyword);
    }
}
