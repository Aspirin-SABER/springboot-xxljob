package com.taiji.sample.rpcsample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Monitor_Ncp_Day_MID")
public class MonitorNcpDay {
    private LocalDateTime dealTime;// 统计日期
    private LocalDateTime createTime;// 创建时间
    private String vehNo;// 车牌号
    private String driverIdCode;// 司机身份证号
    private String id;
    @Column(name = "DEAL_TIME")
    public LocalDateTime getDealTime() {
        return dealTime;
    }

    public void setDealTime(LocalDateTime dealTime) {
        this.dealTime = dealTime;
    }
    @Column(name = "create_Time")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    @Column(name = "VEH_NO")
    public String getVehNo() {
        return vehNo;
    }

    public void setVehNo(String vehNo) {
        this.vehNo = vehNo;
    }
    @Column(name = "DRIVER_ID_CODE")
    public String getDriverIdCode() {
        return driverIdCode;
    }

    public void setDriverIdCode(String driverIdCode) {
        this.driverIdCode = driverIdCode;
    }
    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
