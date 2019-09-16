package com.bbs.domain;

import java.util.Date;

/**
 *   `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
 *   `commentContent` varchar(255) NOT NULL COMMENT '评论内容',
 *   `commentTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
 *   `commentUserName` varchar(255) NOT NULL COMMENT '评论人',
 *   `commentStatus` int(11) DEFAULT '0' COMMENT '评论状态，1代表屏蔽，0代表解除',
 *   `articleId` int(11) NOT NULL COMMENT '帖子编号',
 */

public class Comment {

    private Integer commentId;//评论编号
    private String commentContent;//评论内容
    private Date commentTime;//评论时间

    private String commentUserName;//评论人
    private Integer commentStatus;//评论状态，1代表屏蔽，0代表解除
    private Integer articleId;//帖子编号

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
