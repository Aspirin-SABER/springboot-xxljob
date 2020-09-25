package com.taiji.sample.rpcsample.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CRUISE_ONLINE_HAIL_TIME")
public class CruiseOnLineHailTime {
	protected String id;

	private String timeInterval;
	private Long cruiseData;//当天巡游车出车数
	private Long lastCruiseData;//去年巡游车出车数
	private Long yestCruiseData ;//昨日巡游车出车数
	private Long onLineData ;//网约车出车数
	private Long lastWeekOnLineData;//上周出车数
	private Long yestdayOnLineData ;//昨日出车数
	private LocalDateTime statTime ;//
	@Id
	@Column(name = "id", length = 32)
	public String getId()
	{
		if (id == null) id = UUID.randomUUID().toString().replace("-", "");
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	@Column(name="TIME_INTERVAL")
	public String getTimeInterval() {
		return timeInterval;
	}
	@Column(name="CRUISE_DATA")
	public Long getCruiseData() {
		return cruiseData;
	}
	@Column(name="LAST_CRUISE_DATA")
	public Long getLastCruiseData() {
		return lastCruiseData;
	}
	@Column(name="YEST_CRUISE_DATA")
	public Long getYestCruiseData() {
		return yestCruiseData;
	}
	@Column(name="ONLINE_DATA")
	public Long getOnLineData() {
		return onLineData;
	}
	@Column(name="LASTWEEK_ONLINE_DATA")
	public Long getLastWeekOnLineData() {
		return lastWeekOnLineData;
	}
	@Column(name="YESTDAY_ONLINE_DATA")
	public Long getYestdayOnLineData() {
		return yestdayOnLineData;
	}
	@Column(name="STAT_TIME")
	public LocalDateTime getStatTime() {
		return statTime;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public void setCruiseData(Long cruiseData) {
		this.cruiseData = cruiseData;
	}
	public void setLastCruiseData(Long lastCruiseData) {
		this.lastCruiseData = lastCruiseData;
	}
	public void setYestCruiseData(Long yestCruiseData) {
		this.yestCruiseData = yestCruiseData;
	}
	public void setOnLineData(Long onLineData) {
		this.onLineData = onLineData;
	}
	public void setLastWeekOnLineData(Long lastWeekOnLineData) {
		this.lastWeekOnLineData = lastWeekOnLineData;
	}
	public void setYestdayOnLineData(Long yestdayOnLineData) {
		this.yestdayOnLineData = yestdayOnLineData;
	}
	public void setStatTime(LocalDateTime statTime) {
		this.statTime = statTime;
	}
	public CruiseOnLineHailTime(String timeInterval, Long cruiseData, Long lastCruiseData, Long yestCruiseData,
			Long onLineData, Long lastWeekOnLineData, Long yestdayOnLineData, LocalDateTime statTime) {
		super();
		this.timeInterval = timeInterval;
		this.cruiseData = cruiseData;
		this.lastCruiseData = lastCruiseData;
		this.yestCruiseData = yestCruiseData;
		this.onLineData = onLineData;
		this.lastWeekOnLineData = lastWeekOnLineData;
		this.yestdayOnLineData = yestdayOnLineData;
		this.statTime = statTime;
	}
	public CruiseOnLineHailTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
