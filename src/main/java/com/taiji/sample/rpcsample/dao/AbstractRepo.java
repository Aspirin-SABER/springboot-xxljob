package com.taiji.sample.rpcsample.dao;

import java.time.LocalDateTime;

import com.taiji.sample.rpcsample.entity.CruiseOnLineHailTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AbstractRepo extends JpaRepository<CruiseOnLineHailTime, String> {

	@Query(nativeQuery =true,value="SELECT nvl(VEH_NUM,0) from taxi.MONITOR_TRAVEL_OUT_HOUR"
			+ " where TIME_INTERVAL=?1 "
			+" and to_char(stat_time,'YYYY-MM-DD HH24:MI:SS')=?2 ")
	public Long findByTimeInterval(String timeInterval, String statTime);

	
	@Query(nativeQuery =true,value="select count(t.VEHICLE_LICENSE) from ( "
			+"select distinct travel.VEHICLE_LICENSE,travel.DRIVER_ID_CODE from taxi_test.info_single_travel travel where "
			+"travel.off_time>=?1 and travel.off_time<=?2 "
			+"and travel.part_date=?3 ) t inner join taxi.basic_wycstaff_cz staff "
			+ "on staff.ID_CARD=t.DRIVER_ID_CODE ")
public Long findWycVehicle(LocalDateTime startTime, LocalDateTime endTime, String partDate);
	
}
