/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.client.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: RedisConfig, v0.1 2018年02月23日 21:52闫迎军(YanYingJun) Exp $
 */

@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Getter method for property <tt>redisTemplate</tt>.
     *
     * @return property value of redisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate() {

        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;


    }
}
