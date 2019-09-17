package com.bbs.domain;

import java.util.Date;

/**
 *   `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 *   `userName` varchar(50) NOT NULL COMMENT '用户名',
 *   `userPass` varchar(255) NOT NULL COMMENT '密码',
 *   `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
 *   `picUrl` varchar(255) DEFAULT NULL COMMENT '头像',
 *   `role` int(11) NOT NULL DEFAULT '1' COMMENT '1代表普通用户；2代表高级用户，3代表超级管理员',
 *   `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
 *   `loginStatus` int(11) DEFAULT '0' COMMENT '登录状态，0代表未登录，1代表已登录',
 *   `talkStatus` int(11) NOT NULL DEFAULT '0' COMMENT '发言状态，0代表未屏蔽发言（默认），1代表已屏蔽发言',
 *   `isupdating` int(2) DEFAULT '0' COMMENT '申请升级(0-未申请,1-已申请)',
 *   `updateStatus` int(2) unsigned DEFAULT '0' COMMENT '申请升级审核状态(0-未处理,1-已处理)',
 */
public class UserInfo {

    private Integer userId;//用户ID
    private String userName;//用户名
    private String userPass;//密码

    private String email;//邮箱
    private String picUrl;//头像
    private Integer role;//1代表普通用户；2代表高级用户，3代表超级管理员

    private Date lastLoginTime;//最后登录时间
    private Integer loginStatus;//登录状态，0代表未登录，1代表已登录
    private Integer talkStatus;//发言状态，0代表未屏蔽发言（默认），1代表已屏蔽发言

    private Integer isupdating;//申请升级(0-未申请,1-已申请)
    private Integer updateStatus;//申请升级审核状态(0-未处理,1-已处理)

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getTalkStatus() {
        return talkStatus;
    }

    public void setTalkStatus(Integer talkStatus) {
        this.talkStatus = talkStatus;
    }

    public Integer getIsupdating() {
        return isupdating;
    }

    public void setIsupdating(Integer isupdating) {
        this.isupdating = isupdating;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }
}
