package com.taiji.sample.rpcsample.dao;

import com.taiji.sample.rpcsample.entity.CruiseOnLineHailTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CruiseOnLineHailTimeRepo extends JpaRepository<CruiseOnLineHailTime, String> {
	
	
	@Query(nativeQuery =true,value="SELECT nvl(CRUISE_DATA,0) from taxi_test.CRUISE_ONLINE_HAIL_TIME"
			+ " where TIME_INTERVAL=?1 "
			+" and to_char(stat_time,'YYYY-MM-DD HH24:MI:SS')=?2 ")
	public Long findByTimeInterval(String timeInterval, String statTime);
	@Query(nativeQuery =true,value="SELECT nvl(ONLINE_DATA,0) from taxi_test.CRUISE_ONLINE_HAIL_TIME"
			+ " where TIME_INTERVAL=?1 "
			+" and to_char(stat_time,'YYYY-MM-DD HH24:MI:SS')=?2 ")
	public Long findByTimeIntervalWyc(String timeInterval, String statTime);
	
	
	@Query(nativeQuery =true,value="SELECT TIME_INTERVAL from taxi_test.CRUISE_ONLINE_HAIL_TIME"
			+ " where TIME_INTERVAL=?1 "
			+" and to_char(stat_time,'YYYY-MM-DD HH24:MI:SS')=?2 ")
	public String findByTime(String timeInterval, String statTime);

}
