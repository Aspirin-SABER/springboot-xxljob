package com.taiji.sample.rpcsample.xxljob;

import com.taiji.sample.rpcsample.manage.CruiseOnLineHailTimeManager;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@JobHandler(value="cruiseOnLineHailTimeJob")
@Component
public class CruiseOnLineHailTimeJob extends IJobHandler {
    @Autowired
    private CruiseOnLineHailTimeManager manager;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, testJobHandler.");
        LocalDateTime dealTime = LocalDateTime.now();
		LocalDateTime startTime = LocalDateTime.now();
        startTime = startTime.withMinute(0);
        startTime = startTime.withSecond(0);
		LocalDateTime endTime = LocalDateTime.now();
        endTime = endTime.withMinute(0);
        endTime = endTime.withSecond(0);
        if(dealTime.getHour()==4){
            startTime = startTime.minusDays(1);
            startTime = startTime.withHour(0);
            endTime = endTime.minusDays(1);
            endTime = endTime.withHour(23);
            endTime = endTime.withMinute(59);
            endTime = endTime.withSecond(59);
            manager.execute(startTime,endTime);
        }
        else if(dealTime.getHour()==9){
            startTime = startTime.withHour(7);
            endTime = endTime.withHour(9);
            manager.execute(startTime,endTime);

        }else if(dealTime.getHour()==12){
            startTime = startTime.withHour(0);
            endTime = endTime.withHour(12);
            manager.execute(startTime,endTime);
        }else if(dealTime.getHour()==18){
            startTime = startTime.withHour(0);
            endTime = endTime.withHour(18);
            manager.execute(startTime,endTime);
        }
        System.out.println("短信发送成功");
        return SUCCESS;
    }
}
