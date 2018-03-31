/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.entity.InfosImage;

import java.util.Date;
import java.util.List;

/**
 * @author BugMan
 * @version $Id: InfosVO, v0.1 2018年02月26日 13:37BugMan Exp $
 * 返回的视频新闻列表视图
 */


public class InfosVO extends Infos {

    //排序时间
    private Date orderTime;

    //自己是否点赞
    private boolean isMyLike;

    //评论数
    private int commentsNum;

    //user
    private String headPath;

    private String nickName;

    //user_comment
    private int commentsId;

    private int likes;

    /**
     * 收藏相关
     */
    private int userCollectId;

    private Date collectCreateDate;

    //channels.name as type
    private String type;

    /**
     * 连续阅读时间
     */
    private String readContinuousDay;

    /**
     * 阅读时间
     */
    private String readCreateDate;

    private Long userId;

    private Integer isRead;

    /**
     * 图片集合
     */
    private List<InfosImage> picList;

    /**
     * 视频集合
     */
    private List<InfosImage> videoList;

    /**
     * 导航名称
     */
    private String channelName;

    /**
     * 浏览量
     */
    private Integer browsingVolume;

    /**
     * 转发量
     */
    private Integer forwardingAmount;

    /**
     * 收藏量
     */
    private Integer collentAmount;

    /**
     * 评论数
     */
    private Integer commentsNumber;

    /**
     * 一级标签
     */
    private String firstLevelName;

    /**
     * 二级标签
     */
    private String twoLevelName;

    public int getUserCollectId() {
        return userCollectId;
    }

    public void setUserCollectId(int userCollectId) {
        this.userCollectId = userCollectId;
    }

    public Date getCollectCreateDate() {
        return collectCreateDate;
    }

    public void setCollectCreateDate(Date collectCreateDate) {
        this.collectCreateDate = collectCreateDate;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public boolean isMyLike() {
        return isMyLike;
    }

    public void setMyLike(boolean myLike) {
        isMyLike = myLike;
    }

    public int getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Getter method for property <tt>readContinuousDay</tt>.
     *
     * @return property value of readContinuousDay
     */
    public String getReadContinuousDay() {
        return readContinuousDay;
    }

    /**
     * Setter method for property <tt>readContinuousDay</tt>.
     *
     * @param readContinuousDay value to be assigned to property readContinuousDay
     */
    public void setReadContinuousDay(String readContinuousDay) {
        this.readContinuousDay = readContinuousDay;
    }

    /**
     * Getter method for property <tt>readCreateDate</tt>.
     *
     * @return property value of readCreateDate
     */
    public String getReadCreateDate() {
        return readCreateDate;
    }

    /**
     * Setter method for property <tt>readCreateDate</tt>.
     *
     * @param readCreateDate value to be assigned to property readCreateDate
     */
    public void setReadCreateDate(String readCreateDate) {
        this.readCreateDate = readCreateDate;
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
     * Getter method for property <tt>likes</tt>.
     *
     * @return property value of likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Setter method for property <tt>likes</tt>.
     *
     * @param likes value to be assigned to property likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Getter method for property <tt>isRead</tt>.
     *
     * @return property value of isRead
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * Setter method for property <tt>isRead</tt>.
     *
     * @param isRead value to be assigned to property isRead
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * Getter method for property <tt>picList</tt>.
     *
     * @return property value of picList
     */
    public List<InfosImage> getPicList() {
        return picList;
    }

    /**
     * Setter method for property <tt>picList</tt>.
     *
     * @param picList value to be assigned to property picList
     */
    public void setPicList(List<InfosImage> picList) {
        this.picList = picList;
    }

    /**
     * Getter method for property <tt>videoList</tt>.
     *
     * @return property value of videoList
     */
    public List<InfosImage> getVideoList() {
        return videoList;
    }

    /**
     * Setter method for property <tt>videoList</tt>.
     *
     * @param videoList value to be assigned to property videoList
     */
    public void setVideoList(List<InfosImage> videoList) {
        this.videoList = videoList;
    }

    /**
     * Getter method for property <tt>channelName</tt>.
     *
     * @return property value of channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Setter method for property <tt>channelName</tt>.
     *
     * @param channelName value to be assigned to property channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Getter method for property <tt>browsingVolume</tt>.
     *
     * @return property value of browsingVolume
     */
    public Integer getBrowsingVolume() {
        return browsingVolume;
    }

    /**
     * Setter method for property <tt>browsingVolume</tt>.
     *
     * @param browsingVolume value to be assigned to property browsingVolume
     */
    public void setBrowsingVolume(Integer browsingVolume) {
        this.browsingVolume = browsingVolume;
    }

    /**
     * Getter method for property <tt>forwardingAmount</tt>.
     *
     * @return property value of forwardingAmount
     */
    public Integer getForwardingAmount() {
        return forwardingAmount;
    }

    /**
     * Setter method for property <tt>forwardingAmount</tt>.
     *
     * @param forwardingAmount value to be assigned to property forwardingAmount
     */
    public void setForwardingAmount(Integer forwardingAmount) {
        this.forwardingAmount = forwardingAmount;
    }

    /**
     * Getter method for property <tt>collentAmount</tt>.
     *
     * @return property value of collentAmount
     */
    public Integer getCollentAmount() {
        return collentAmount;
    }

    /**
     * Setter method for property <tt>collentAmount</tt>.
     *
     * @param collentAmount value to be assigned to property collentAmount
     */
    public void setCollentAmount(Integer collentAmount) {
        this.collentAmount = collentAmount;
    }

    /**
     * Getter method for property <tt>commentsNumber</tt>.
     *
     * @return property value of commentsNumber
     */
    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    /**
     * Setter method for property <tt>commentsNumber</tt>.
     *
     * @param commentsNumber value to be assigned to property commentsNumber
     */
    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    /**
     * Getter method for property <tt>firstLevelName</tt>.
     *
     * @return property value of firstLevelName
     */
    public String getFirstLevelName() {
        return firstLevelName;
    }

    /**
     * Setter method for property <tt>firstLevelName</tt>.
     *
     * @param firstLevelName value to be assigned to property firstLevelName
     */
    public void setFirstLevelName(String firstLevelName) {
        this.firstLevelName = firstLevelName;
    }

    /**
     * Getter method for property <tt>twoLevelName</tt>.
     *
     * @return property value of twoLevelName
     */
    public String getTwoLevelName() {
        return twoLevelName;
    }

    /**
     * Setter method for property <tt>twoLevelName</tt>.
     *
     * @param twoLevelName value to be assigned to property twoLevelName
     */
    public void setTwoLevelName(String twoLevelName) {
        this.twoLevelName = twoLevelName;
    }
}
