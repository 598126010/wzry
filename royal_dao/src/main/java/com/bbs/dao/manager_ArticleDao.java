package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.domain.Word;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface manager_ArticleDao {
//    查询所有帖子
    @Select("<script>select * from bbs_article_table where 1=1" +
            "<if test=\"title !=null \">" +
            "and title like '%${title}%' " +
            "</if> " +
            "<if test=\"senderName !=null \">" +
            "and senderName like '%${senderName}%' " +
            "</if></script>")
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
    List<Article> findByPage(@Param("title") String title,@Param("senderName")String senderName,@Param("page")int page,@Param("size")int size);

//    帖子置顶状态更改
    @Update("update bbs_article_table set isTop=#{status} where articleId=#{id}")
    void topStatus(@Param("id") int id, @Param("status")int status );

//    帖子屏蔽状态更改
    @Update("update bbs_comment_table set commentStatus=#{status} where commentId=#{id}")
    void articleStatus(@Param("id") int id, @Param("status") int status);

//    查询所有未处理举报贴
    @Select("select * from bbs_report_table where reportStatus=1")
    List<Report> findAllRport(int page, int size);

    //    查询已屏蔽举报贴
    @Select("select * from bbs_report_table where reportStatus=2")
    List<Report> findAllDealedRport(int page, int size);

//    更改帖子举报信息（是否举报）
    @Update("update bbs_article_table set isReport=#{status} where articleId=#{id}")
    void isReportStatus(@Param("id") int id, @Param("status")int status );

//    更改帖子举报处理信息（是否处理）
    @Update("update bbs_report_table set reportStatus=#{status} where articleId=#{id}")
    void dealReportStatus(@Param("id") int id,@Param("status") int status);

    //    查询所有敏感词汇并分页
    @Select("select * from bbs_word_table")
    List<Word> findAllWords(int page, int size);

    //    改变敏感词启用状态
    @Update("update bbs_word_table set status=#{status} where wordId=#{id}")
    void changeWordStatus(@Param("id") int id,@Param("status") int status);

    //添加敏感词汇
    @Insert("insert into bbs_word_table(word,status) values (#{word},0)")
    void addWords(@Param("word") String word);

    //查询相关帖子
    @Select("select * from bbs_article_table where articleId=#{articleId}")
    Article findArticleById(int articleId);
}

