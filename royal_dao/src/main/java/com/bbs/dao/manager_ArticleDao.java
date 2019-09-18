package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface manager_ArticleDao {
//    查询所有帖子
    @Select("select * from bbs_article_table")
    @Results({
            @Result(id=true,property = "articleId",column = "articleId"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "sendTime",column = "sendTime"),
            @Result(property = "senderName",column = "senderName"),
            @Result(property = "isTop",column = "isTop"),
            @Result(property = "replyCount",column = "replyCount"),
            @Result(property = "upvoteCount",column = "upvoteCount"),
            @Result(property = "browseCount",column = "browseCount"),
            @Result(property = "zone",column = "zoneId",javaType = Article.class,one = @One(select = "com.bbs.dao.manager_ZoneDao.findById")),
            @Result(property = "isReport",column = "isReport"),
    })
    List<Article> findByPage(int page, int size);

//    帖子屏蔽状态更改
    @Update("update bbs_article_table set isTop=#{status} where articleId=#{id}")
    void topStatus(@Param("id") int id, @Param("status")int status );

//    帖子置顶状态更改
    @Update("update bbs_comment_table set commentStatus=#{status} where commentId=#{id}")
    void articleStatus(@Param("id") int id, @Param("status") int status);
}

