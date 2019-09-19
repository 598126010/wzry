package com.bbs.dao;

import com.bbs.domain.UserInfo;
import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public interface ZoneApplyDao {

    //增加板块
    @Insert("insert into bbs_zoneapply_table (applyZoneId, zoneName, userName,reason, status) values (#{applyZoneId}, #{zoneName}, #{userName},#{reason}, #{status})")
    void insert(ZoneApply zoneApply);
    //查询所有板块
    @Select("select * from bbs_zoneapply_table")
    List<ZoneApply> findAll(String zongName,String userName);
    //修改板块状态
    @Update("update bbs_zoneapply_table set status=#{status} where applyZoneId=#{applyZoneId}")
    void kaiqi(@Param("applyZoneId") int applyZoneId,@Param("status") int status);
    //模糊查询
    @Select("<script>select * from bbs_zoneapply_table where 1=1 " +"<if test=\"zoneName !=null \">and " +"zoneName like '%${zoneName}%' </if> <if test=\"userName !=null \">" + "and userName like '%${userName}%' </if></script>")
    List<ZoneApply> mohu(@Param("zoneName") String zongName, @Param("userName") String userName);
//  开启板块 往表中添加数据
    @Insert("insert into bbs_zone_table (zoneName,isDef) values (#{zoneName},2)")
    void tianjia(String zoneName);
//  关闭板块 往表中删除数据
    @Delete("delete from bbs_zone_table where zoneName = #{zoneName}")
    void guanbi(String zoneName);
}
