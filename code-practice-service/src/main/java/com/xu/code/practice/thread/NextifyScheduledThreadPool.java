package com.xu.code.practice.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class NextifyScheduledThreadPool extends ScheduledThreadPoolExecutor {

    /**
     * @Author liberty
     * @Date 2025/4/1 17:02
     */

    public NextifyScheduledThreadPool(int corePoolSize, int maxPoolSize){
        super(corePoolSize);
        setMaximumPoolSize(maxPoolSize);
    }

}
