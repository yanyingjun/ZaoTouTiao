/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5924341575648369158L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 上级用户ID，默认为0
     */
    private Long parentId;

    /**
     * 金币
     */
    private Integer gold;

    /**
     * 零钱
     */
    private BigDecimal money;

    /**
     * 用户头像
     */
    private String headPath;

    /**
     * 年龄
     */
    private String birthday;

    /**
     * 性别（0：女，1:男）
     */
    private Integer sex;

    /**
     * 我的邀请码
     */
    private String myInvitation;

    /**
     * 是否登录（0:未登录，1:已登录）
     */
    private Integer isOnline;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 职业
     */
    private String job;

    /**
     * 教育背景
     */
    private String educational;

    /**
     *
     */
    private String lastVisitDate;

    /**
     *
     */
    private String code;

    /**
     *
     */
    private String pushTime1;

    /**
     *
     */
    private String pushTime2;

    /**
     *
     */
    private String pushTime3;

    /**
     *
     */
    private String pushTime4;

    /**
     * 微信ID
     */
    private String wechatId;

    /**
     * 用户oppenId
     */
    private String oppenId;

    /**
     * 平台ID
     */
    private Integer platformId;

    /**
     * 平台对应的渠道ID
     */
    private Integer channelId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 地址
     */
    private String address;

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
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>nickname</tt>.
     *
     * @return property value of nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickname</tt>.
     *
     * @param nickName value to be assigned to property nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter method for property <tt>telephone</tt>.
     *
     * @return property value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter method for property <tt>telephone</tt>.
     *
     * @param telephone value to be assigned to property telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter method for property <tt>account</tt>.
     *
     * @return property value of account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Setter method for property <tt>account</tt>.
     *
     * @param account value to be assigned to property account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Getter method for property <tt>password</tt>.
     *
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     *
     * @return property value of parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     *
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>gold</tt>.
     *
     * @return property value of gold
     */
    public Integer getGold() {
        return gold;
    }

    /**
     * Setter method for property <tt>gold</tt>.
     *
     * @param gold value to be assigned to property gold
     */
    public void setGold(Integer gold) {
        this.gold = gold;
    }

    /**
     * Getter method for property <tt>money</tt>.
     *
     * @return property value of money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Setter method for property <tt>money</tt>.
     *
     * @param money value to be assigned to property money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * Getter method for property <tt>headPath</tt>.
     *
     * @return property value of headPath
     */
    public String getHeadPath() {
        return headPath;
    }

    /**
     * Setter method for property <tt>headPath</tt>.
     *
     * @param headPath value to be assigned to property headPath
     */
    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    /**
     * Getter method for property <tt>birthday</tt>.
     *
     * @return property value of birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Setter method for property <tt>birthday</tt>.
     *
     * @param birthday value to be assigned to property birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter method for property <tt>sex</tt>.
     *
     * @return property value of sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     *
     * @param sex value to be assigned to property sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property <tt>myInvitation</tt>.
     *
     * @return property value of myInvitation
     */
    public String getMyInvitation() {
        return myInvitation;
    }

    /**
     * Setter method for property <tt>myInvitation</tt>.
     *
     * @param myInvitation value to be assigned to property myInvitation
     */
    public void setMyInvitation(String myInvitation) {
        this.myInvitation = myInvitation;
    }

    /**
     * Getter method for property <tt>isOnline</tt>.
     *
     * @return property value of isOnline
     */
    public Integer getIsOnline() {
        return isOnline;
    }

    /**
     * Setter method for property <tt>isOnline</tt>.
     *
     * @param isOnline value to be assigned to property isOnline
     */
    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
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
     * Getter method for property <tt>job</tt>.
     *
     * @return property value of job
     */
    public String getJob() {
        return job;
    }

    /**
     * Setter method for property <tt>job</tt>.
     *
     * @param job value to be assigned to property job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Getter method for property <tt>educational</tt>.
     *
     * @return property value of educational
     */
    public String getEducational() {
        return educational;
    }

    /**
     * Setter method for property <tt>educational</tt>.
     *
     * @param educational value to be assigned to property educational
     */
    public void setEducational(String educational) {
        this.educational = educational;
    }

    /**
     * Getter method for property <tt>lastVisitDate</tt>.
     *
     * @return property value of lastVisitDate
     */
    public String getLastVisitDate() {
        return lastVisitDate;
    }

    /**
     * Setter method for property <tt>lastVisitDate</tt>.
     *
     * @param lastVisitDate value to be assigned to property lastVisitDate
     */
    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>pushTime1</tt>.
     *
     * @return property value of pushTime1
     */
    public String getPushTime1() {
        return pushTime1;
    }

    /**
     * Setter method for property <tt>pushTime1</tt>.
     *
     * @param pushTime1 value to be assigned to property pushTime1
     */
    public void setPushTime1(String pushTime1) {
        this.pushTime1 = pushTime1;
    }

    /**
     * Getter method for property <tt>pushTime2</tt>.
     *
     * @return property value of pushTime2
     */
    public String getPushTime2() {
        return pushTime2;
    }

    /**
     * Setter method for property <tt>pushTime2</tt>.
     *
     * @param pushTime2 value to be assigned to property pushTime2
     */
    public void setPushTime2(String pushTime2) {
        this.pushTime2 = pushTime2;
    }

    /**
     * Getter method for property <tt>pushTime3</tt>.
     *
     * @return property value of pushTime3
     */
    public String getPushTime3() {
        return pushTime3;
    }

    /**
     * Setter method for property <tt>pushTime3</tt>.
     *
     * @param pushTime3 value to be assigned to property pushTime3
     */
    public void setPushTime3(String pushTime3) {
        this.pushTime3 = pushTime3;
    }

    /**
     * Getter method for property <tt>pushTime4</tt>.
     *
     * @return property value of pushTime4
     */
    public String getPushTime4() {
        return pushTime4;
    }

    /**
     * Setter method for property <tt>pushTime4</tt>.
     *
     * @param pushTime4 value to be assigned to property pushTime4
     */
    public void setPushTime4(String pushTime4) {
        this.pushTime4 = pushTime4;
    }

    /**
     * Getter method for property <tt>wechatId</tt>.
     *
     * @return property value of wechatId
     */
    public String getWechatId() {
        return wechatId;
    }

    /**
     * Setter method for property <tt>wechatId</tt>.
     *
     * @param wechatId value to be assigned to property wechatId
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    /**
     * Getter method for property <tt>oppenId</tt>.
     *
     * @return property value of oppenId
     */
    public String getOppenId() {
        return oppenId;
    }

    /**
     * Setter method for property <tt>oppenId</tt>.
     *
     * @param oppenId value to be assigned to property oppenId
     */
    public void setOppenId(String oppenId) {
        this.oppenId = oppenId;
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
     * Getter method for property <tt>status</tt>.
     *
     * @return property value of status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     *
     * @param status value to be assigned to property status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}