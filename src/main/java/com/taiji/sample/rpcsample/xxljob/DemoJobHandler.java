package com.taiji.sample.rpcsample.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@JobHandler(value = "testJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) {
        XxlJobLogger.log("XXL-JOB, testJobHandler.");
        System.out.println("XXL-JOB测试");
        return SUCCESS;
}

}