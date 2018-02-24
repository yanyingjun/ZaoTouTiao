/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.client.redis.impl;

import com.zhishun.zaotoutiao.common.client.redis.RedisCommonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: RedisConfig, v0.1 2018年02月23日 21:52闫迎军(YanYingJun) Exp $
 */

@Service
public class RedisCommonClientImpl implements RedisCommonClient {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(RedisCommonClientImpl.class);

    /**
     * Redis模板
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 序列化对象
     */
    private static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    private static RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();

    @Override
    public String get(final String redisKey) {

        return redisTemplate.opsForValue().get(redisKey).toString();

    }

    @Override
    public void set(final String redisKey, final Object object) {

        redisTemplate.opsForValue().set(redisKey, object);

    }

    @Override
    public void delete(final String redisKey) {

        redisTemplate.delete(redisKey);

    }


    @Override
    public boolean expire(final String redisKey, final long expire){
        return redisTemplate.expire(redisKey, expire, TimeUnit.SECONDS);
    }

}