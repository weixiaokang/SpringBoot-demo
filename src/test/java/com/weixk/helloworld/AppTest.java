package com.weixk.helloworld;

import com.alibaba.fastjson.JSON;
import com.weixk.helloworld.domain.User;
import com.weixk.helloworld.domain.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private Environment environment;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testProperty() {
        System.out.println(environment.getProperty("me.test.str"));
    }
    @Test
    public void testLog() {
        log.trace("=================trace====================");
        log.debug("=================debug====================");
        log.info("==================info=====================");
        log.warn("==================warn=====================");
        log.error("=================error====================");
    }
    @Test
    public void testRedisDelete() {
        stringRedisTemplate.opsForValue().set("key", "value");
        stringRedisTemplate.delete("key");
        log.info(stringRedisTemplate.hasKey("key").toString());
        log.info(stringRedisTemplate.opsForValue().get("key"));
    }
}
