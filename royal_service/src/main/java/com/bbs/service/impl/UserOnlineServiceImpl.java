package com.bbs.service.impl;

import com.bbs.dao.UserOnlineDao;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOnlineServiceImpl implements UserOnlineService {
    @Autowired
    private UserOnlineDao userOnlineDao;
    private UserInfo userInfo;


    @Override
    public List<UserInfo> findOnlineUser(Integer userId) {
        try {
            if(userInfo.getLoginStatus()==1){
                return userOnlineDao.findOnlineUser(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
