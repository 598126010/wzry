package com.bbs.service;

public interface UpvoteService {
    Integer findIsUpvote(String articleId, String userName);

    void addUpvote(String articleId, String userName, int i);

    void changeIsUpvote(String articleId, String userName, int i);
}
