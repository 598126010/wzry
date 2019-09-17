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

    //更新用户信息
    @Update("update bbs_user_table set email = #{email} where userId = #{userId}")
    void updateUserEmail(UserInfo userInfo);

    @Select("select * from bbs_user_table where userId= #{userId}")
    UserInfo findUserInfo(UserInfo user);

    @Update("update bbs_user_table set picUrl = #{picUrl} where userId = #{userId}")
    void updateUserPicture(UserInfo user);

    //查找用户头像
    @Select("select * from bbs_user_table where userId = #{userId}")
    UserInfo findUserPicture(UserInfo userInfo);

    //邮箱校验
    @Select("select * from bbs_user_table where email = #{email};")
    UserInfo checkUserEmail(String email);

    //展示用户信息
    @Select("select * from bbs_user_table where userId = #{userId}")
    UserInfo findById(Integer userId);

    //查询旧密码
    @Select("select * from bbs_user_table where userId = #{userId} and userPass = #{userPass}")
    UserInfo checkUserPass(UserInfo u);

    //修改密码
    @Update("update bbs_user_table set userPass = #{userPass} where userId = #{userId}")
    void changeUserPass(UserInfo user);
}
