/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 我的频道表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserChannels implements Serializable {

    private static final long serialVersionUID = -3016577005820170949L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 频道ID
     */
    private String channels;

    /**
     * 构造方法
     */
    public UserChannels(Long id, Long userId, String channels) {
        this.id = id;
        this.userId = userId;
        this.channels = channels;
    }

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
     * Getter method for property <tt>channels</tt>.
     *
     * @return property value of channels
     */
    public String getChannels() {
        return channels;
    }

    /**
     * Setter method for property <tt>channels</tt>.
     *
     * @param channels value to be assigned to property channels
     */
    public void setChannels(String channels) {
        this.channels = channels;
    }
}