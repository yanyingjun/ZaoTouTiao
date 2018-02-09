/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2012-2017
 */
package com.zhishun.zaotoutiao.common.client.redis;

/**
 * Redis - 客户端 - 接口
 * 
 * @author 侯俊昌（HouJunChang）
 * @version $Id: RedisCommonClient.java, v 0.1 2017年3月18日 下午2:27:48 侯俊昌（HouJunChang） Exp $
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
     * @param expire 失效时间
     */
    void set(String redisKey, Object object, int expire);

    /**
     * 通过Redis-Key删除缓存对象
     * 
     * @param redisKey Redis-Key
     * @return 删除结果
     */
    void delete(String redisKey);

    /**
     * 通过传递规则Redis-Key删除缓存对象
     * 
     * @param redisKeyStr Redis-Key
     * @return 删除结果
     */
    void delete(String... redisKeyStr);

    /**
     * 模糊删除Redis-Key
     * 
     * @param prex 模糊Key
     */
    void deleteByPrex(String prex);

}