package com.zhishun.zaotoutiao.core.model.entity;

public class UserBehavior {

    private Integer id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 下载人数
     */
    private Integer downloadNum;

    /**
     * 激活人数
     */
    private Integer activationNum;

    /**
     * 注册人数
     */
    private Integer registerNum;

    /**
     * 操作次数
     */
    private Integer operationNum;

    /**
     * 平台ID
     */
    private Integer platformId;

    /**
     * 渠道ID
     */
    private Integer channelId;

    /**
     * 是否是首次激活
     */
    private Integer isFirstActivation;

    private String createDate;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
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
     * Getter method for property <tt>downloadNum</tt>.
     *
     * @return property value of downloadNum
     */
    public Integer getDownloadNum() {
        return downloadNum;
    }

    /**
     * Setter method for property <tt>downloadNum</tt>.
     *
     * @param downloadNum value to be assigned to property downloadNum
     */
    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    /**
     * Getter method for property <tt>activationNum</tt>.
     *
     * @return property value of activationNum
     */
    public Integer getActivationNum() {
        return activationNum;
    }

    /**
     * Setter method for property <tt>activationNum</tt>.
     *
     * @param activationNum value to be assigned to property activationNum
     */
    public void setActivationNum(Integer activationNum) {
        this.activationNum = activationNum;
    }

    /**
     * Getter method for property <tt>registerNum</tt>.
     *
     * @return property value of registerNum
     */
    public Integer getRegisterNum() {
        return registerNum;
    }

    /**
     * Setter method for property <tt>registerNum</tt>.
     *
     * @param registerNum value to be assigned to property registerNum
     */
    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }

    /**
     * Getter method for property <tt>operationNum</tt>.
     *
     * @return property value of operationNum
     */
    public Integer getOperationNum() {
        return operationNum;
    }

    /**
     * Setter method for property <tt>operationNum</tt>.
     *
     * @param operationNum value to be assigned to property operationNum
     */
    public void setOperationNum(Integer operationNum) {
        this.operationNum = operationNum;
    }

    /**
     * Getter method for property <tt>platformId</tt>.
     *
     * @return property value of platformId
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * Setter method for property <tt>platformId</tt>.
     *
     * @param platformId value to be assigned to property platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * Getter method for property <tt>channelId</tt>.
     *
     * @return property value of channelId
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * Setter method for property <tt>channelId</tt>.
     *
     * @param channelId value to be assigned to property channelId
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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
     * Getter method for property <tt>isFirstActivation</tt>.
     *
     * @return property value of isFirstActivation
     */
    public Integer getIsFirstActivation() {
        return isFirstActivation;
    }

    /**
     * Setter method for property <tt>isFirstActivation</tt>.
     *
     * @param isFirstActivation value to be assigned to property isFirstActivation
     */
    public void setIsFirstActivation(Integer isFirstActivation) {
        this.isFirstActivation = isFirstActivation;
    }
}