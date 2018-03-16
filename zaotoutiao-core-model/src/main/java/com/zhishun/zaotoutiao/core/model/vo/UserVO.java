/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.User;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserVO, v0.1 2018年02月25日 9:44闫迎军(YanYingJun) Exp $
 */
public class UserVO extends User{

    /**
     * 老密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 师傅信息
     */
    private User parent;

    /**
     * 活跃天数
     */
    private String spanDays;

    /**
     * 未登录用户数量
     */
    private Integer notLoginNum;

    /**
     * 分组日期
     */
    private String times;

    /**
     * 徒弟数量
     */
    private Integer apprenticeNum;

    /**
     * 阅读篇数
     */
    private Integer readNumber;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * Getter method for property <tt>oldPassword</tt>.
     *
     * @return property value of oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Setter method for property <tt>oldPassword</tt>.
     *
     * @param oldPassword value to be assigned to property oldPassword
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Getter method for property <tt>newPassword</tt>.
     *
     * @return property value of newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Setter method for property <tt>newPassword</tt>.
     *
     * @param newPassword value to be assigned to property newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Getter method for property <tt>parent</tt>.
     *
     * @return property value of parent
     */
    public User getParent() {
        return parent;
    }

    /**
     * Setter method for property <tt>parent</tt>.
     *
     * @param parent value to be assigned to property parent
     */
    public void setParent(User parent) {
        this.parent = parent;
    }

    /**
     * Getter method for property <tt>spanDays</tt>.
     *
     * @return property value of spanDays
     */
    public String getSpanDays() {
        return spanDays;
    }

    /**
     * Setter method for property <tt>spanDays</tt>.
     *
     * @param spanDays value to be assigned to property spanDays
     */
    public void setSpanDays(String spanDays) {
        this.spanDays = spanDays;
    }

    /**
     * Getter method for property <tt>notLoginNum</tt>.
     *
     * @return property value of notLoginNum
     */
    public Integer getNotLoginNum() {
        return notLoginNum;
    }

    /**
     * Setter method for property <tt>notLoginNum</tt>.
     *
     * @param notLoginNum value to be assigned to property notLoginNum
     */
    public void setNotLoginNum(Integer notLoginNum) {
        this.notLoginNum = notLoginNum;
    }

    /**
     * Getter method for property <tt>times</tt>.
     *
     * @return property value of times
     */
    public String getTimes() {
        return times;
    }

    /**
     * Setter method for property <tt>times</tt>.
     *
     * @param times value to be assigned to property times
     */
    public void setTimes(String times) {
        this.times = times;
    }

    /**
     * Getter method for property <tt>apprenticeNum</tt>.
     *
     * @return property value of apprenticeNum
     */
    public Integer getApprenticeNum() {
        return apprenticeNum;
    }

    /**
     * Setter method for property <tt>apprenticeNum</tt>.
     *
     * @param apprenticeNum value to be assigned to property apprenticeNum
     */
    public void setApprenticeNum(Integer apprenticeNum) {
        this.apprenticeNum = apprenticeNum;
    }

    /**
     * Getter method for property <tt>readNumber</tt>.
     *
     * @return property value of readNumber
     */
    public Integer getReadNumber() {
        return readNumber;
    }

    /**
     * Setter method for property <tt>readNumber</tt>.
     *
     * @param readNumber value to be assigned to property readNumber
     */
    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
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
}
