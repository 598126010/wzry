package com.bbs.service;

import com.bbs.domain.UserInfo;

public interface UserService {
    //用户登入
    UserInfo login(UserInfo user);
    void updateLastLoginTime(UserInfo userInfo);
    void updateLoginStatus(Integer userId, Integer loginStatus);

    //更新用户信息
    void updateUserEmail(Integer userId, String email);
    UserInfo findUserInfo(UserInfo user);
    void updateUserPicture(Integer userId, String saveName);

    //查找用户头像
    String findUserPicture(String userId);

    //邮箱校验
    Boolean checkUserEmail(String email);

    //展示用户信息
    UserInfo findById(Integer userId);
}
