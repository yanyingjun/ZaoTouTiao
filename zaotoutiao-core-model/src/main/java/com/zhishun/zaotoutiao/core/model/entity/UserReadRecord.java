package com.zhishun.zaotoutiao.core.model.entity;

import java.util.Date;

public class UserReadRecord {
    private Long id;

    private Long userId;

    private String infoId;

    private Date createDate;

    private String infoType;

    private Integer readContinuousDay;

    private Integer isRequestGold;

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
     * Getter method for property <tt>createDate</tt>.
     *
     * @return property value of createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     *
     * @param createDate value to be assigned to property createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter method for property <tt>infoType</tt>.
     *
     * @return property value of infoType
     */
    public String getInfoType() {
        return infoType;
    }

    /**
     * Setter method for property <tt>infoType</tt>.
     *
     * @param infoType value to be assigned to property infoType
     */
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    /**
     * Getter method for property <tt>readContinuousDay</tt>.
     *
     * @return property value of readContinuousDay
     */
    public Integer getReadContinuousDay() {
        return readContinuousDay;
    }

    /**
     * Setter method for property <tt>readContinuousDay</tt>.
     *
     * @param readContinuousDay value to be assigned to property readContinuousDay
     */
    public void setReadContinuousDay(Integer readContinuousDay) {
        this.readContinuousDay = readContinuousDay;
    }

    /**
     * Getter method for property <tt>isRequestGold</tt>.
     *
     * @return property value of isRequestGold
     */
    public Integer getIsRequestGold() {
        return isRequestGold;
    }

    /**
     * Setter method for property <tt>isRequestGold</tt>.
     *
     * @param isRequestGold value to be assigned to property isRequestGold
     */
    public void setIsRequestGold(Integer isRequestGold) {
        this.isRequestGold = isRequestGold;
    }
}