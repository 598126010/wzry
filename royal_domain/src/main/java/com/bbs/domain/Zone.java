package com.bbs.domain;

/**
 *   `zoneId` int(11) NOT NULL AUTO_INCREMENT COMMENT '交流区编号',
 *   `zoneName` varchar(255) NOT NULL COMMENT '交流区名字',
 *   `isDef` int(11) NOT NULL COMMENT '是否默认，1代表默认，2代表非默认',
 */
public class Zone {

    private Integer zoneId;//交流区编号
    private String zoneName;//交流区名字
    private Integer isDef;//是否默认，1代表默认，2代表非默认

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getIsDef() {
        return isDef;
    }

    public void setIsDef(Integer isDef) {
        this.isDef = isDef;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                ", isDef=" + isDef +
                '}';
    }
}
