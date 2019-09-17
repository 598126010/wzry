package com.bbs.service;

import com.bbs.domain.UserInfo;

public interface UserService {
    //用户登入
    UserInfo login(UserInfo user);
    void updateLastLoginTime(UserInfo userInfo);
    void updateLoginStatus(Integer userId, Integer loginStatus);


}
