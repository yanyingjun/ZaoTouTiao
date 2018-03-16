/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * network对象
 * @author 闫迎军(YanYingJun)
 * @version $Id: Network, v0.1 2018年03月13日 16:20闫迎军(YanYingJun) Exp $
 */
public class Network {

    /**
     * 设备当前ip,
     */
    private String ip;

    /**
     * 设备网络环境（1.WIFI;2.UNKNOWN;3.2G;4.3G;5.4G）
     */
    private Integer type;

    /**
     * Getter method for property <tt>ip</tt>.
     *
     * @return property value of ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Setter method for property <tt>ip</tt>.
     *
     * @param ip value to be assigned to property ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
