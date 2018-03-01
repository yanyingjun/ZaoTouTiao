/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;


import java.io.Serializable;

/**
 * 广告表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 6640133897041889940L;
    /**
     * ID
     */
    private Long adId;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String cover;

    /**
     *
     */
    private String keywords;

    /**
     *
     */
    private String url;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 广告类型（COMMON公共类型，MY我的，广告）
     */
    private String type;

    /**
     * Getter method for property <tt>adId</tt>.
     *
     * @return property value of adId
     */
    public Long getAdId() {
        return adId;
    }

    /**
     * Setter method for property <tt>adId</tt>.
     *
     * @param adId value to be assigned to property adId
     */
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * Getter method for property <tt>title</tt>.
     *
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property <tt>title</tt>.
     *
     * @param title value to be assigned to property title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for property <tt>cover</tt>.
     *
     * @return property value of cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * Setter method for property <tt>cover</tt>.
     *
     * @param cover value to be assigned to property cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Getter method for property <tt>keywords</tt>.
     *
     * @return property value of keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Setter method for property <tt>keywords</tt>.
     *
     * @param keywords value to be assigned to property keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Getter method for property <tt>url</tt>.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     *
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>createDate</tt>.
     *
     * @return property value of createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     *
     * @param createDate value to be assigned to property createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }
}