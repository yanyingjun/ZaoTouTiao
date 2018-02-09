package com.zhishun.zaotoutiao.common.client.redis.impl;

import com.zhishun.zaotoutiao.common.client.redis.RedisCommonClient;
import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.core.model.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Redis - 客户端 - 接口实现
 */
/*
@Service
public class RedisCommonClientImpl implements RedisCommonClient {

    */
/**
     * 日志
     *//*

    private static Logger LOGGER = LoggerFactory.getLogger(RedisCommonClientImpl.class);

    */
/**
     * Redis模板
     *//*

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    */
/**
     * 序列化对象
     *//*

    private static StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    private static RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();

    @Override
    public Object get(final String redisKey) {

        return redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                try {
                    // Key序列化
                    byte[] byteKey = stringRedisSerializer.serialize(redisKey);

                    // Object序列化
                    byte[] byteObj = redisConnection.get(byteKey);

                    return redisSerializer.deserialize(byteObj);
                } catch (Exception e) {

                    // 打印日志
                    LoggerUtils.error(LOGGER, e.getMessage());
                }

                return null;
            }
        });
    }

    @Override
    public void set(final String redisKey, final Object object, final int expire) {

        redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {

                try {

                    // Key序列化
                    byte[] byteKey = stringRedisSerializer.serialize(redisKey);

                    // Object序列化
                    byte[] byteObj = redisSerializer.serialize(object);

                    connection.setEx(byteKey, expire, byteObj);

                    return 1L;
                } catch (Exception e) {

                    // 打印日志
                    LoggerUtils.error(LOGGER, e.getMessage());
                }

                return 0L;
            }
        });

    }

    @Override
    public void delete(final String redisKey) {

        redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                try {

                    // Key序列化
                    byte[] byteKey = stringRedisSerializer.serialize(redisKey);

                    connection.del(byteKey);

                } catch (Exception e) {

                    // 打印日志
                    LoggerUtils.error(LOGGER, e.getMessage());
                }

                return null;
            }
        });

    }

    @Override
    public void delete(final String... redisKeyStr) {

        redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {

                try {

                    // 定义结果值
                    long result = 0;

                    // 循环处理
                    for (int i = 0; i < redisKeyStr.length; i++) {

                        // Key序列化
                        byte[] byteKey = stringRedisSerializer.serialize(redisKeyStr[i]);

                        result = connection.del(byteKey);
                    }

                    return result;
                } catch (Exception e) {

                    // 打印日志
                    LoggerUtils.error(LOGGER, e.getMessage());
                }

                return null;
            }
        });

    }

    @Override
    public void deleteByPrex(final String prex) {

        redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {

                try {

                    // Key序列化
                    byte[] prexKey = stringRedisSerializer.serialize(prex);

                    // 获取匹配Key
                    Set<byte[]> byteKeySet = connection.keys(prexKey);

                    // 校验集合
                    if (byteKeySet.isEmpty()) {
                        return 0L;
                    }

                    // 定义结果值
                    long result = 0;

                    // 循环处理
                    for (byte[] byteKey : byteKeySet) {
                        result = result + connection.del(byteKey);
                    }

                    return result;
                } catch (Exception e) {

                    // 打印日志
                    LoggerUtils.error(LOGGER, e.getMessage());
                }

                return null;
            }
        });

    }

}*/
