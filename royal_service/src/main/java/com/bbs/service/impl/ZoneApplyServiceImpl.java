package com.bbs.service.impl;

import com.bbs.dao.ZoneApplyDao;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneApplyServiceImpl implements ZoneApplyService {
    @Autowired
    private ZoneApplyDao zoneApplyDao;

    //增加板块
    @Override
    public void add(ZoneApply zoneApply) {
        zoneApplyDao.insert(zoneApply);
    }
    //查询所有板块
    @Override
    public List<ZoneApply> findAll(int page,int size,String zoneName,String userName) {
        PageHelper.startPage(page,size);
         return zoneApplyDao.mohu(zoneName,userName);
    }
//修改板块状态
    @Override
    public void kaiqi(Integer applyZoneId, Integer status,String zoneName) {
        zoneApplyDao.kaiqi(applyZoneId,status);
        if(status==1){
            zoneApplyDao.tianjia(zoneName);
        }
        if(status==0){
            zoneApplyDao.guanbi(zoneName);
        }
    }
}
