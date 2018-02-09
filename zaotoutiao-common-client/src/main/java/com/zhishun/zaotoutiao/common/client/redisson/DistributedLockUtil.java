package com.zhishun.zaotoutiao.common.client.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁 工具类
 *
 * @author 曹柏青
 * @version $Id: DistributedLockUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年11月02日 10:57 曹柏青 Exp $
 */
public class DistributedLockUtil {

    /**
     * redisson对象
     */
    private static Redisson redisson = RedissonManager.getRedisson();

    /**
     * 锁名的前缀
     */
    private static final String LOCK_NAME_PREFIX = "redissonLock_";

    /**
     * 根据lockName对进行上锁操作
     *
     * @param lockName  锁名称
     */
    public static void lock(String lockName){
        String key = LOCK_NAME_PREFIX + lockName;
        RLock lock = redisson.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁 ：30秒
        lock.lock(30, TimeUnit.SECONDS);
    }

    /**
     * 根据lockName对进行解锁操作
     *
     * @param lockName  锁名称
     */
    public static void unlock(String lockName){
        String key = LOCK_NAME_PREFIX + lockName;
        RLock lock = redisson.getLock(key);
        lock.unlock();
    }

    /**
     * @param rLock  锁
     */
    public static void unlock(RLock rLock){
        rLock.unlock();
    }

    /**
     * 获取redis分布式锁
     * @param lockName
     * @return
     */
    public static RLock getLock(String lockName) {
        String key = LOCK_NAME_PREFIX + lockName;
        return redisson.getLock(key);
    }

}
