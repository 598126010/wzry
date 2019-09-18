package com.bbs.dao;

import com.bbs.domain.Zone;
import org.apache.ibatis.annotations.Select;

public interface manager_ZoneDao {
    //查找交流区名称
    @Select("select zoneName from bbs_zone_table where zoneId=#{id}")
    Zone findById(int id);
}
