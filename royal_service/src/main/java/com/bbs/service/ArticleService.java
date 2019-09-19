package com.bbs.service;

import com.bbs.domain.*;

import java.util.List;

public interface ArticleService {

    void saveReply(Reply reply);

    void saveComment(Comment comment);

//    List<Article> findArticleListByZoneId(int id);

    List<Comment> getArticleByArticleId(int id);

    void createNewArticle(Article article);

    Article findArticleByArticleId(int id);

    List<Reply> findReplyByCommentId(Integer commentId);

    List<UserInfo> findOnlineUser();

    Integer getTotalCount();

    Integer getTodayCount();

    List<Article> findByKeyWord(String keyWord);

    boolean submitReport(Report report);
    List<Zone> findAllZone();
    List<Article> findTop1Article();
    Integer findUpvoteCount(String articleId);
    void subtractUpvoteCount(String articleId);
    void addUpvoteCount(String articleId);
    List<Article> findArticleListByZoneId(int id, int pageSize, int pageNum);
}
