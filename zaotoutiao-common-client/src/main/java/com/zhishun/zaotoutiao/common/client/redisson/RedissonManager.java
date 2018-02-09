package com.zhishun.zaotoutiao.common.client.redisson;

import com.zhishun.zaotoutiao.common.client.constant.base.CommonConfig;
import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redisson管理类
 *
 */
public class RedissonManager {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(RedissonManager.class);

    /**
     * redisson客户端对象
     */
    private static RedissonClient redissonClient;

    /**
     * redisson配置
     */
    private static Config config = new Config();

    /**
     * 初始化redisson
     */
    public static void init(){
        try {
            //redis哨兵模式
//            config.useSentinelServers()
//                    .setMasterName("mymaster")
//                    .addSentinelAddress("192.168.0.56:6379")
//                    //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
//                    .setConnectTimeout(30000)
//                    //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。默认:3000
//                    .setReconnectionTimeout(10000)
//                    //等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
//                    .setTimeout(10000)
//                    //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
//                    .setRetryAttempts(5)
//                    //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
//                    .setRetryInterval(3000)
//            ;

            //redis单例模式
            config.useSingleServer().setAddress(CommonConfig.REDIS_HOST_NAME + ":" + CommonConfig.REDIS_PORT)
                    .setPassword(CommonConfig.REDIS_PASSWORD)
                    .setDatabase(CommonConfig.REDIS_DB);
            redissonClient = Redisson.create(config);
        }catch (Exception e){
            LoggerUtils.error(LOGGER, "redisson管理类初始化失败", e);
        }
    }
    /**
     * 获取Redisson的实例对象
     *
     * @return
     */
    public static Redisson getRedisson(){
        init();
        return (Redisson) redissonClient;
    }

    /**
     * 测试哨兵模式的Redisson是否正常
     *
     * @param args
     */
    public static void main(String[] args) {
        Redisson redisson = RedissonManager.getRedisson();
        System.out.println("我的redisson来啦 = " + redisson);

    }
}
