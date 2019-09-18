package com.bbs.dao;

import com.bbs.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    @Insert("insert into bbs_reply_table(commentId,replyContent,replyUserName) values(#{commentId},#{replyContent},#{replyUserName})")
    void saveReply(Reply reply);

    @Insert("insert into bbs_comment_table(commentContent,commentUserName,articleId) values(#{commentContent},#{commentUserName},#{articleId})")
    void saveComment(Comment comment);

    @Select("select * from bbs_article_table where zoneId = #{id}")
    List<Article> findArticleListByZoneId(int id);

    @Select("select * from bbs_comment_table where articleId=#{id}")
    @Results({
            @Result(id=true,property = "commentId",column = "commentId"),
            @Result(property = "commentContent",column = "commentContent"),
            @Result(property = "commentTime",column = "commentTime"),
            @Result(property = "commentUserName",column = "commentUserName"),
            @Result(property = "commentStatus",column = "commentStatus"),
            @Result(property = "articleId",column = "articleId"),
            @Result(property = "userInfo",column = "commentUserName",javaType = UserInfo.class,
            one = @One(select = "com.bbs.dao.UserDao.findUserInfoByUsername")),
    })
    List<Comment> getArticleByArticleId(int id);

    @Insert("insert into bbs_article_table(title,content,senderName,zoneId) values(#{title},#{content},#{senderName},#{zoneId})")
    void createNewArticle(Article article);

    @Select("select * from  bbs_article_table where articleId = #{id}")
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
            @Result(property = "userInfo",column = "senderName",javaType = UserInfo.class,
                    one = @One(select = "com.bbs.dao.UserDao.findUserInfoByUsername"))
    })
    Article findArticleByArticleId(int id);

    @Select("select * from bbs_reply_table where commentId = #{commentId}")
    List<Reply> findReplyByCommentId(Integer commentId);

    //显示在线用户
    @Select("select * from bbs_user_table where loginStatus = 1")
    List<UserInfo> findOnlineUser();

    //今日帖子统计
    @Select("select count(1) from bbs_article_table where to_days(sendTime) = to_days(now());")
    Integer getTodayCount();

    //全部帖子统计
    @Select("select count(1) from bbs_article_table;")
    Integer getTotalCount();
    @Insert("insert into bbs_report_table(reportContent,reportUserName,reportStatus,articleId) values(#{reportContent},#{reportUserName},#{reportStatus},#{articleId})")
    int submitReport(Report report);
}
