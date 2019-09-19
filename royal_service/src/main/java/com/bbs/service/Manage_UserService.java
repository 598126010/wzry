package com.bbs.service;


import com.bbs.domain.UserInfo;

import java.util.List;

public interface Manage_UserService{
//用户、用户组管理功能
    List<UserInfo> findByPage(int page,int size,String userName, Integer role);

    void shengji(String id);

    void jingyan(int userId,int talkStatus);

    List<UserInfo> findAllUser(int page, int size);
}
