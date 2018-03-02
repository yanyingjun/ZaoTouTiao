/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 用户地址实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserLocation implements Serializable {

    private static final long serialVersionUID = 3424242780995453090L;
    /**
     * ID
     */
    private Long locationId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 经度
     */
    private Float lat;

    /**
     * 纬度
     */
    private Float lng;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * Getter method for property <tt>locationId</tt>.
     *
     * @return property value of locationId
     */
    public Long getLocationId() {
        return locationId;
    }

    /**
     * Setter method for property <tt>locationId</tt>.
     *
     * @param locationId value to be assigned to property locationId
     */
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
     * Getter method for property <tt>lat</tt>.
     *
     * @return property value of lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * Setter method for property <tt>lat</tt>.
     *
     * @param lat value to be assigned to property lat
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

    /**
     * Getter method for property <tt>lng</tt>.
     *
     * @return property value of lng
     */
    public Float getLng() {
        return lng;
    }

    /**
     * Setter method for property <tt>lng</tt>.
     *
     * @param lng value to be assigned to property lng
     */
    public void setLng(Float lng) {
        this.lng = lng;
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