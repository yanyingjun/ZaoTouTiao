/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * client对象
 * @author 闫迎军(YanYingJun)
 * @version $Id: Client, v0.1 2018年03月13日 16:23闫迎军(YanYingJun) Exp $
 */
public class Client {

    /**
     * 广告客户端类型
     */
    private Integer type;

    /**
     * 请求aiclk的版本号，当前版本1.6.2
     */
    private String version;

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

    /**
     * Getter method for property <tt>version</tt>.
     *
     * @return property value of version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter method for property <tt>version</tt>.
     *
     * @param version value to be assigned to property version
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
