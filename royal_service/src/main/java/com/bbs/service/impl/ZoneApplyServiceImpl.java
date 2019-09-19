package com.bbs.service.impl;

import com.bbs.dao.ZoneApplyDao;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneApplyServiceImpl implements ZoneApplyService {
    @Autowired
    private ZoneApplyDao zoneApplyDao;

    //增加板块
    @Override
    public void add(ZoneApply zoneApply) {
        zoneApplyDao.insert(zoneApply);
    }
}
