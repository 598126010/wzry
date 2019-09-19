package com.bbs.service.impl;

import com.bbs.dao.Manage_UserDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.Manage_UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.management.relation.Role;
import java.util.ArrayList;

import java.util.List;
@Service
public class Manage_UserServiceImpl implements Manage_UserService {
//用户、用户组管理功能
    @Autowired
    Manage_UserDao manage_userDao;
    @Override
    public List<UserInfo> findByPage(int page,int size,String userName, Integer role) {
        PageHelper.startPage(page,size);
        return  manage_userDao.mohu(userName,role);
    }


    //
    @Override
    public void shengji(String id) {
        manage_userDao.shengji(id);
    }
//禁言 恢复
    @Override
    public void jingyan(int userId,int talkStatus) {
        manage_userDao.jingyan(userId,talkStatus);
    }
}
