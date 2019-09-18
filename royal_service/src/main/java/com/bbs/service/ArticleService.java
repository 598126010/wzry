package com.bbs.service;

import com.bbs.domain.*;

import java.util.List;

public interface ArticleService {

    void saveReply(Reply reply);

    void saveComment(Comment comment);

    List<Article> findArticleListByZoneId(int id);

    List<Comment> getArticleByArticleId(int id);

    void createNewArticle(Article article);

    Article findArticleByArticleId(int id);

    List<Reply> findReplyByCommentId(Integer commentId);

    List<UserInfo> findOnlineUser();

    Integer getTotalCount();

    Integer getTodayCount();
    boolean submitReport(Report report);
}
