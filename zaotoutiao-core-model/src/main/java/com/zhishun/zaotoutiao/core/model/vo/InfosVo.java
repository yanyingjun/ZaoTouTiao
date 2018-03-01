/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.Infos;

import java.util.Date;

/**
 * @author BugMan
 * @version $Id: InfosVo, v0.1 2018年02月26日 13:37BugMan Exp $
 * 返回的视频新闻列表视图
 */


public class InfosVo extends Infos{

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

    private String content;

    private int likes;

    private Date createDate;

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


    private String infoId;

    private Long userId;


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
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
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
     * Getter method for property <tt>createDate</tt>.
     *
     * @return property value of createDate
     */
    @Override
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     *
     * @param createDate value to be assigned to property createDate
     */
    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
