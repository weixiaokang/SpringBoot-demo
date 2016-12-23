package com.weixk.helloworld;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.weixk.helloworld.config.RedisObjectSerializer;
import com.weixk.helloworld.domain.User;
import com.weixk.helloworld.filter.ValidatorFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, "--server.port=8080");
        log.info("应用启动");
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, User> userResgiterRedisTemplate() {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
    /**
     * 使用fastjson将对象转json字符串
     * @return 消息转换器
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(config);
        return new HttpMessageConverters(converter);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public FilterRegistrationBean validatorFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("validator");
        ValidatorFilter validatorFilter = new ValidatorFilter();
        filterRegistrationBean.setFilter(validatorFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/valid/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
