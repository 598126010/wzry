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

    //更新用户信息
    @Override
    public void updateUserEmail(Integer userId, String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userDao.updateUserEmail(userInfo);

    }

    @Override
    public UserInfo findUserInfo(UserInfo user) {
        UserInfo userInfo = userDao.findUserInfo(user);
        return userInfo;
    }

    @Override
    public void updateUserPicture(Integer userId, String saveName) {
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setPicUrl(saveName);
        userDao.updateUserPicture(user);
    }

    //查找用户头像
    @Override
    public String findUserPicture(String userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Integer.parseInt(userId));
        UserInfo u = userDao.findUserPicture(userInfo);
        if(u.getPicUrl() == null){
            return "images/default.png";
        }
        return u.getPicUrl();

    }

    //邮箱校验
    @Override
    public Boolean checkUserEmail(String email) {
        UserInfo userInfo = userDao.checkUserEmail(email);
        if(userInfo != null){
            //该邮箱已被注册,无法使用
            return true;
        }
        return false;

    }

    //展示用户信息
    @Override
    public UserInfo findById(Integer userId) {
        return userDao.findById(userId);
    }
}
