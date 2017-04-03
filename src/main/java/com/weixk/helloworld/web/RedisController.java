package com.weixk.helloworld.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redis数据库测试
 * Created by weixk on 16/12/7.
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping(value = "/add")
    public String add(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        stringRedisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        return "add opt end";
    }
    @RequestMapping(value = "/find")
    public String find(@RequestParam(value = "key") String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
