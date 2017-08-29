package com.weixk.helloworld.web;

import com.weixk.helloworld.task.AsynTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * Created by weixk on 17/8/29.
 */
@RestController
@RequestMapping("/asyn")
public class AsynController {

    private static final Logger logger = Logger.getLogger(AsynController.class);
    @Autowired
    private AsynTask asynTask;
    @RequestMapping("/future")
    public String taskFuture() {
        logger.info("future start: " + System.currentTimeMillis());
        try {
            Future<String> task = asynTask.taskFuture();
            while (true) {
                if (task.isDone()) {
                    logger.info("future: " + System.currentTimeMillis());
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("/task")
    public String task() {
        try {
            asynTask.task();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("task start: " + System.currentTimeMillis());
        return "success";
    }
}
