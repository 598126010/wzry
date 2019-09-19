package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Manage_UserDao {

    @Select("select * from bbs_user_table where userName=#{username}")
    UserInfo login(String username)throws Exception;
//用户、用户组管理功能
    @Select("select * from bbs_user_table where isUpdating = 1")
    List<UserInfo> findByPage();
//升级
    @Update("update bbs_user_table set role='2',updateStatus = 1 where userId=#{id} and role!='3'")
    void shengji(String id);
//禁言恢复
    @Update("update bbs_user_table set talkStatus=#{talkStatus} where userId=#{userId}")
    void jingyan(@Param("userId") int userId,@Param("talkStatus") int talkStatus);
//模糊查询
     @Select("<script>select * from bbs_user_table where 1=1 and isUpdating = 1 and updateStatus = 0" +"<if test=\"userName !=null \">and " +"userName like '%${userName}%' </if> <if test=\"role   !=null \">" + "and role like '%${role}%' </if></script>")
    List<UserInfo> mohu(@Param("userName") String userName,@Param("role") Integer role);
    @Select("select * from bbs_user_table")
    List<UserInfo> findAllUser();
}
