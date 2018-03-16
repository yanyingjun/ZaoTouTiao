/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * Media对象
 * @author 闫迎军(YanYingJun)
 * @version $Id: Media, v0.1 2018年03月13日 15:59闫迎军(YanYingJun) Exp $
 */
public class Media {

    /**
     * 媒体类别
     */
    private Integer type;

    /**
     * 当前app的信息api必须要传
     */
    private App app;

    /**
     * 当前站点信息
     */
    private Site site;

    /**
     * 浏览器信息
     */
    private Browser browser;

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
     * Getter method for property <tt>app</tt>.
     *
     * @return property value of app
     */
    public App getApp() {
        return app;
    }

    /**
     * Setter method for property <tt>app</tt>.
     *
     * @param app value to be assigned to property app
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Getter method for property <tt>site</tt>.
     *
     * @return property value of site
     */
    public Site getSite() {
        return site;
    }

    /**
     * Setter method for property <tt>site</tt>.
     *
     * @param site value to be assigned to property site
     */
    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * Getter method for property <tt>browser</tt>.
     *
     * @return property value of browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * Setter method for property <tt>browser</tt>.
     *
     * @param browser value to be assigned to property browser
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }
}
