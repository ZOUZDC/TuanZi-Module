package com.github.zouzdc.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Redis配置
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/11/24 23:58
 */
@Configuration
public class RedisConfig {

   /* @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Key HashKey使用String序列化
        template.setDefaultSerializer(RedisSerializer.string());
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // Value HashValue使用Json序列化
        template.setDefaultSerializer(RedisSerializer.json());
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        template.setConnectionFactory(factory);
        return template;
    }*/
}