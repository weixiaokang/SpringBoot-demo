package com.weixk.helloworld.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 添加拦截器
 * Created by weixk on 16/12/14.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/user/find-by-name");
    }

    private class LoginCheckInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String token = request.getHeader("token");
            if (!StringUtils.hasLength(token)) {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                String result = "{\"code\":0, \"msg\":\"用户token参数缺失\"}";
                writer.print(result);
                return false;
            }
            String uid = request.getHeader("uid");
            if (!StringUtils.hasLength(uid)) {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                String result = "{\"code\":0, \"msg\":\"用户id参数缺失\"}";
                writer.print(result);
                return false;
            }
            String redisToken = stringRedisTemplate.opsForValue().get(uid);
            if (!token.equals(redisToken)) {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                String result = "{\"code\":-1, \"msg\":\"用户未登录或登录超时，请重新登录\"}";
                writer.print(result);
                return false;
            }
            stringRedisTemplate.opsForValue().set(uid, token, 60, TimeUnit.SECONDS);
            log.info("uid=" + uid + ", token=" + token + ", reset lifetime 10s");
            return super.preHandle(request, response, handler);
        }
    }
}
