package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchArticleDao {

    @Select("select * from bbs_article_table where title like \"%\"#{keyword}\"%\" or content like \"%\"#{keyword}\"%\"")
    List<Article> searchArticleByKeyWord(String keyword);


}
