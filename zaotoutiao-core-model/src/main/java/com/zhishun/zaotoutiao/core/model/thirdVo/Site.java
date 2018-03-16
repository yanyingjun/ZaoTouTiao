/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: Site, v0.1 2018年03月13日 19:47闫迎军(YanYingJun) Exp $
 */
public class Site {

    /**
     * 站点主域
     */
    private String domain;

    /**
     * 当前页面
     */
    private String urls;

    /**
     * Getter method for property <tt>domain</tt>.
     *
     * @return property value of domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Setter method for property <tt>domain</tt>.
     *
     * @param domain value to be assigned to property domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Getter method for property <tt>urls</tt>.
     *
     * @return property value of urls
     */
    public String getUrls() {
        return urls;
    }

    /**
     * Setter method for property <tt>urls</tt>.
     *
     * @param urls value to be assigned to property urls
     */
    public void setUrls(String urls) {
        this.urls = urls;
    }
}
