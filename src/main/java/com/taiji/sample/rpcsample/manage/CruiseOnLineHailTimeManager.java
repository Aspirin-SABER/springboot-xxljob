package com.taiji.sample.rpcsample.manage;

import java.time.LocalDateTime;

public interface CruiseOnLineHailTimeManager {
    public void execute(LocalDateTime startTime, LocalDateTime endTime) ;
}
