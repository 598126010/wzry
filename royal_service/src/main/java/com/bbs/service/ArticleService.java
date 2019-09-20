package com.bbs.service;

import com.bbs.domain.*;

import java.util.List;

public interface ArticleService {

    void saveReply(Reply reply);

    void saveComment(Comment comment);

    List<Article> findArticleListByZoneId(int id, int pageSize, int pageNum);

    List<Comment> getArticleByArticleId(int id);

    void createNewArticle(Article article);

    Article findArticleByArticleId(int id);

    List<Reply> findReplyByCommentId(Integer commentId);

    List<UserInfo> findOnlineUser();

    Integer getTotalCount();

    Integer getTodayCount();
    boolean submitReport(Report report);

    void addUpvoteCount(String articleId);

    void subtractUpvoteCount(String articleId);

    Integer findUpvoteCount(String articleId);

    List<Zone> findAllZone();
    List<Article> findByKeyWord(String keyWord, int pageNum, int pageSize);

    void changeIsreportByArticle(Integer articleId);
    List<Article> findTop1Article();

    void addReplyCount(Integer articleId);

    Comment findCommentByReplyId(Integer commentId);

    int getMaxId();
}
