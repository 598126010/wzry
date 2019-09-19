package com.bbs.dao;

import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.Insert;

public interface ZoneApplyDao {

    //增加板块
    @Insert("insert into bbs_zoneapply_table (applyZoneId, zoneName, userName,reason, status) values (#{applyZoneId}, #{zoneName}, #{userName},#{reason}, #{status})")
    void insert(ZoneApply zoneApply);
}
