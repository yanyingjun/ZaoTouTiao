package com.zhishun.zaotoutiao.common.client.constant.base;

import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.common.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 通用配置(除请求第三方配置外，其他配置读取都放到这)
 *
 */
public class CommonConfig {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CommonConfig.class);

    static {
        try {
            Properties properties = PropertiesUtil.getProperties("config/redis.properties");
            REDIS_HOST_NAME = properties.getProperty("redis.hostName");
            REDIS_PORT = properties.getProperty("redis.port");
            REDIS_PASSWORD = properties.getProperty("redis.password");
            REDIS_DB = Integer.parseInt(properties.getProperty("redis.database"));
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "通用配置CommonConfig初始化失败", e);
        }
    }

    /**
     * redis主机名
     */
    public static String REDIS_HOST_NAME;

    /**
     * redis端口号
     */
    public static String REDIS_PORT;

    /**
     * redis密码
     */
    public static String REDIS_PASSWORD;

    /**
     * redis DB
     */
    public static int REDIS_DB;
}
