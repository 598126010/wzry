package com.bbs.service;

import com.bbs.domain.UserInfo;

import java.util.List;

public interface UserOnlineService {

    List<UserInfo> findOnlineUser(Integer userId);
}
