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
        map.put("userId", userId);
        map.put("loginStatus", loginStatus);
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
    public void updateUserPicture(Integer userId, String filename) {
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setPicUrl(filename);
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

    @Override
    public boolean checkOutUsername(String username) {
      UserInfo userInfo =   userDao.findUserInfoByUsername(username);
        if (userInfo != null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean saveUser(UserInfo userInfo) {
        int i = userDao.saveUser(userInfo);
        if (i == 1){
            return true;
        }
        return false;
    }

    //查询旧密码
    @Override
    public Boolean checkUserPass(String userId, String oldPass) {
        UserInfo u = new UserInfo();
        u.setUserId(Integer.parseInt(userId));
        u.setUserPass(oldPass);
        UserInfo check = userDao.checkUserPass(u);
        if (check != null){
            //旧密码正确,查到用户
            return true;
        }else{
            //旧密码错误,查不到用户
            return false;
        }

    }

    //修改密码
    @Override
    public void changeUserPass(String userId, String newPass) {
        UserInfo user = new UserInfo();
        user.setUserId(Integer.parseInt(userId));
        user.setUserPass(newPass);
        userDao.changeUserPass(user);
    }

    //申请高级用户
    @Override
    public void apply(UserInfo user) {
        userDao.updateByPrimaryKey(user);

    }

    //统计用户发帖数
    @Override
    public Integer getCount(String userName) {
        return userDao.getCount(userName);
    }

    @Override
    public UserInfo findByUsername(String userName) {
       return userDao.findUserInfoByUsername(userName);
    }
}
