package com.bbs.dao;

import com.bbs.domain.Word;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordDao {
    @Select("select * from bbs_word_table")
    List<Word> findAllWord();
}
