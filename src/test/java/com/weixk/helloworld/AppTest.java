package com.weixk.helloworld;

import com.weixk.helloworld.domain.UserResgiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);
    @Autowired
    private Environment environment;
    @Autowired
    private RedisTemplate<String, UserResgiter> userResgiterRedisTemplate;
    @Test
    public void testProperty() {
        System.out.println(environment.getProperty("me.test.str"));
    }
    @Test
    public void testLog() {
        log.error("测试log日志打印！");
    }
    @Test
    public void testUserRegisterRedisTemplate() {
        UserResgiter userResgiter = new UserResgiter();
        userResgiter.setEmail("876267672@qq.com");
        userResgiter.setNickname("weixiaokang");
        userResgiterRedisTemplate.opsForValue().set("www", userResgiter);

        UserResgiter model = userResgiterRedisTemplate.opsForValue().get("www");
        log.info("UserResgiter: {}", model);
    }
}
