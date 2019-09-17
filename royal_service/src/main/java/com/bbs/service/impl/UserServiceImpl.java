package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    //用户登入
    @Override
    public UserInfo login(UserInfo userInfo) {
        updateLastLoginTime(userInfo);
        return userDao.login(userInfo);
    }

    @Override
    public void updateLastLoginTime(UserInfo userInfo) {
        Date lastLoginTime = new Date();
        userInfo.setLastLoginTime(lastLoginTime);
        userDao.updateLoginTime(userInfo);
    }

    @Override
    public void updateLoginStatus(Integer userId, Integer loginStatus) {

        Map map = new HashMap<>();
        map.put("userId",userId);
        map.put("loginStatus",loginStatus);
        userDao.updateLoginStatus(map);
    }
}
