package com.bbs.domain;

import java.util.Date;

/**
 *   `reportId` int(11) NOT NULL AUTO_INCREMENT,
 *   `reportContent` varchar(255) DEFAULT NULL COMMENT '举报内容',
 *   `reportTime` timestamp NULL DEFAULT NULL COMMENT '举报时间',
 *   `reportUserName` varchar(255) DEFAULT NULL COMMENT '举报人',
 *   `reportStatus` int(2) DEFAULT NULL COMMENT '处理状态',
 *   `articleId` int(11) DEFAULT NULL COMMENT '文章ID',
 */

public class Report {

    private Integer reportId;
    private String reportContent;//举报内容
    private Date reportTime;//举报时间

    private String reportUserName;//举报人
    private Integer reportStatus;//处理状态
    private Integer articleId;//文章ID

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportContent='" + reportContent + '\'' +
                ", reportTime=" + reportTime +
                ", reportUserName='" + reportUserName + '\'' +
                ", reportStatus=" + reportStatus +
                ", articleId=" + articleId +
                '}';
    }
}
