package com.bbs.service;

import com.bbs.domain.ZoneApply;

import java.util.List;

public interface ZoneApplyService {

    //增加板块
    void add(ZoneApply zoneApply);


//    查询所有板块
    List<ZoneApply> findAll(int page,int size,String zoneName,String userName);
//      修改板块状态
    void kaiqi(Integer applyZoneId, Integer status,String zoneName);
}
