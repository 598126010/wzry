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

    //查询旧密码
    Boolean checkUserPass(String userId, String oldPass);

    //修改密码
    void changeUserPass(String userId, String newPass);

    //申请高级用户
    void apply(UserInfo user);

    //统计用户发帖数
    Integer getCount(String userName);
}
