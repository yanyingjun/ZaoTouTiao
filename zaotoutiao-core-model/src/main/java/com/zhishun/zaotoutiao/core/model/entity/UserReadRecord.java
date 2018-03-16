package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserReadRecord {

    private Long id;

    private Long userId;

    private String infoId;

    private String createDate;

    private String infoType;

    private Integer readContinuousDay;

    private Integer isRequestGold;

    /**
     * 导航channelId
     */
    private String channelId;

    /**
     * 关键词
     */
    private String label;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 资源来源
     */
    private String source;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 新闻链接
     */
    private String url;

    /**
     * 用户行为
     */
    private Integer browsing;

    public Integer getBrowsing() {
        return browsing;
    }

    public void setBrowsing(Integer browsing) {
        this.browsing = browsing;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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