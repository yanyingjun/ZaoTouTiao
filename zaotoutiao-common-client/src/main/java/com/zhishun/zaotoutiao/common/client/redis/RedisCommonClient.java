/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.client.redis;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: RedisConfig, v0.1 2018年02月23日 21:52闫迎军(YanYingJun) Exp $
 */

public interface RedisCommonClient {

    /**
     * 通过Redis-Key，获取缓存对象
     * 
     * @param redisKey Redis-Key
     * @return 缓存对象
     */
    Object get(String redisKey);

    /**
     * 设置Redis-Key到缓存中
     * 
     * @param redisKey Redis-Key
     * @param object 缓存对象
     */
    void set(String redisKey, Object object);

    /**
     * 通过Redis-Key删除缓存对象
     * 
     * @param redisKey Redis-Key
     * @return 删除结果
     */
    void delete(String redisKey);

    /**
     * 设置过期时间
     * @param redisKey Redis-Key
     * @param expire 设置缓存失效时间
     * @return
     */
    boolean expire(final String redisKey, final long expire);

}