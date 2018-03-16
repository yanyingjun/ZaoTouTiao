/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 用户分享实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserShare implements Serializable {

    private static final long serialVersionUID = 5702702371304967319L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分享类型（收徒RECRUIT，文章ARTICLE，视频VIDEO ,唤醒AWAKEN，晒收入INCOME）
     */
    private String type;

    /**
     *
     */
    private String infoId;

    /**
     * 分享来源（WECHAT 微信, QQ, QR_CODE 二维码  ，FRIENDSTER 朋友圈  ，SINA 新浪微博，SMS 短信'）
     */
    private String source;

    /**
     * 徒弟id,逗号分隔
     */
    private String apprenticeId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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

    /**
     * Getter method for property <tt>infoId</tt>.
     *
     * @return property value of infoId
     */
    public String getInfoId() {
        return infoId;
    }

    /**
     * Setter method for property <tt>infoId</tt>.
     *
     * @param infoId value to be assigned to property infoId
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    /**
     * Getter method for property <tt>source</tt>.
     *
     * @return property value of source
     */
    public String getSource() {
        return source;
    }

    /**
     * Setter method for property <tt>source</tt>.
     *
     * @param source value to be assigned to property source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Getter method for property <tt>apprenticeId</tt>.
     *
     * @return property value of apprenticeId
     */
    public String getApprenticeId() {
        return apprenticeId;
    }

    /**
     * Setter method for property <tt>apprenticeId</tt>.
     *
     * @param apprenticeId value to be assigned to property apprenticeId
     */
    public void setApprenticeId(String apprenticeId) {
        this.apprenticeId = apprenticeId;
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
}