package com.weixk.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppTest
{
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

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
    public void testRestAPI() {
        String url = "http://localhost:8080/user/rest/all";
        RestTemplate restTemplate = new RestTemplate();
        List result = restTemplate.getForObject(url, List.class);
        System.out.println(result.toString());
    }
}
