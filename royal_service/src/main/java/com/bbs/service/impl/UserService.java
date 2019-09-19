package com.bbs.service.impl;

import com.bbs.dao.Manage_UserDao;
import com.bbs.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
public class UserService  implements UserDetailsService {
    @Autowired
    Manage_UserDao manage_userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo=manage_userDao.login(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUserName(), "{noop}"+userInfo.getUserPass(), userInfo.getLoginStatus() == 0 ? true : false, true, true, true, getAuthority(userInfo));
        return user;
    }
    //    作用就是返回一个list集合 集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        if(userInfo.getRole()  == 3){
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return list;
        }
        return null;
    }
}
