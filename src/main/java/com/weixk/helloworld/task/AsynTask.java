package com.weixk.helloworld.task;


import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by weixk on 17/8/29.
 */
@Component
public class AsynTask {

    private static final Logger logger = Logger.getLogger(AsynTask.class);

    @Async
    public Future<String> taskFuture() throws InterruptedException {
        Thread.sleep(3 * 1000);
        return new AsyncResult<>(String.valueOf(System.currentTimeMillis()));
    }

    @Async
    public void task() throws InterruptedException {
        Thread.sleep(3 * 1000);
        logger.info("task: " + System.currentTimeMillis());
    }
}
