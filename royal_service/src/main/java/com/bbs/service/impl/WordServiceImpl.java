package com.bbs.service.impl;

import com.bbs.dao.WordDao;
import com.bbs.domain.Word;
import com.bbs.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordDao wordDao;
    @Override
    public List<Word> findAllWord() {
        return wordDao.findAllWord();
    }
}
