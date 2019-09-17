package com.bbs.service;

import com.bbs.domain.Article;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface SearchArticleService {


    List<Article> searchArticleByKeyWord(String keyword, int page, int size) throws UnsupportedEncodingException;
}
