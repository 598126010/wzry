package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;

import java.util.List;

public interface ArticleService {

    void saveReply(Reply reply);

    void saveComment(Comment comment);

    List<Article> findArticleListByZoneId(int id);

    List<Comment> getArticleByArticleId(int id);

    void createNewArticle(Article article);

    Article findArticleByArticleId(int id);

    List<Reply> findReplyByCommentId(Integer commentId);
}
