package com.zhishun.zaotoutiao.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author 朱思雷
 * @version $Id: PropertiesUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月07日 10:36
 */
public class PropertiesUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
    /**
     * 读取properties配置文件
     *
     * @param fileName 文件路径与名称：redis/redis.properties
     * @return
     * @throws IOException
     * @author WangYaL 邮箱：wang_yalong@qq.com
     * @dateTime 2016年9月23日 下午5:15:56
     */
    public static Properties getProperties(String fileName) throws IOException {
        Properties prop = new Properties();
        try {
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(is);
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "读取properties配置文件失败：【" + fileName + "】文件不存在");
        }
        return prop;
    }

    /**
     * 获取properties配置文件中的值
     *
     * @param fileName 文件路径与名称：redis/redis.properties
     * @param key      配置文件中的键
     * @return
     * @throws IOException
     * @author WangYaL 邮箱：wang_yalong@qq.com
     * @dateTime 2016年9月23日 下午5:17:34
     */
    public static String getKey(String fileName, String key) throws IOException {
        Properties prop = getProperties(fileName);
        if (prop != null) {
            return prop.getProperty(key);
        }
        return null;
    }
}
