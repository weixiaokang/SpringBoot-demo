package com.weixk.helloworld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by weixk on 17/8/25.
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {
}
