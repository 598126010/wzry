package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserDao {

    //用户登入
    @Select("Select * from bbs_user_table where userName = #{userName} and userPass= #{userPass}")
    UserInfo login(UserInfo userInfo);

    @Update("update bbs_user_table set lastLoginTime = #{lastLoginTime} where userName = #{userName}")
    void updateLoginTime(UserInfo userInfo);

    @Update("update bbs_user_table set loginStatus = #{loginStatus} where userId = #{userId}")
    void updateLoginStatus(Map map);
}
