package com.bbs.domain;

/**
 *   `upvoteUserName` varchar(255) NOT NULL COMMENT '点赞人',
 *   `upvoteArticleId` int(11) NOT NULL COMMENT '点赞帖子',
 *   `isUpvote` int(11) NOT NULL DEFAULT '1' COMMENT '点赞状态，0代表未点赞，1代表已点赞（默认）',
 */
public class Upvote {

    private String upvoteUserName;//点赞人
    private Integer upvoteArticleId;//点赞帖子
    private Integer isUpvote;//点赞状态，0代表未点赞，1代表已点赞（默认）

    public String getUpvoteUserName() {
        return upvoteUserName;
    }

    public void setUpvoteUserName(String upvoteUserName) {
        this.upvoteUserName = upvoteUserName;
    }

    public Integer getUpvoteArticleId() {
        return upvoteArticleId;
    }

    public void setUpvoteArticleId(Integer upvoteArticleId) {
        this.upvoteArticleId = upvoteArticleId;
    }

    public Integer getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(Integer isUpvote) {
        this.isUpvote = isUpvote;
    }
}
