package com.bbs.dao;

import com.bbs.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
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
    List<Comment> getArticleByArticleId(int id);

    @Insert("insert into bbs_article_table(title,content,senderName,zoneId) values(#{title},#{content},#{senderName},#{zoneId})")
    void createNewArticle(Article article);

    @Select("select * from  bbs_article_table where articleId = #{id}")
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
