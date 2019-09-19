package com.bbs.domain;

import java.util.Date;

/**
 *   `replyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复编号',
 *   `replyContent` varchar(255) NOT NULL COMMENT '回复内容',
 *   `replyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
 *   `replyUserName` varchar(255) NOT NULL COMMENT '回复人',
 *   `commentId` int(11) DEFAULT NULL COMMENT '评论编号',
 */

public class Reply {

    private Integer replyId;//回复编号
    private String replyContent;//回复内容
    private Date replyTime;//回复时间

    private String replyUserName;//回复人
    private Integer commentId;//评论编号

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                ", replyUserName='" + replyUserName + '\'' +
                ", commentId=" + commentId +
                '}';
    }
}
