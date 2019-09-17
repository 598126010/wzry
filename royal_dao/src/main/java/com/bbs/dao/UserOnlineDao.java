package com.bbs.dao;

import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOnlineDao {

    @Select("select username,picurl from bbs_user_table where userId = #{userId}")
    List<UserInfo> findOnlineUser(Integer userId);
}
