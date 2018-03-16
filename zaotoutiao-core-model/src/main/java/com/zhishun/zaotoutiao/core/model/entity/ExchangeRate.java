/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 汇率表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class ExchangeRate implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 普通收徒奖励金币
     */
    private Integer recruitGold;

    /**
     * 普通收徒奖励零钱
     */
    private BigDecimal recruitMoney;

    /**
     * 首次收徒奖励金币
     */
    private Integer newbieRecruitGold;

    /**
     * 首次收徒奖励零钱
     */
    private BigDecimal newbieRecruitMoney;

    /**
     * 注册并登陆奖励金币
     */
    private Integer registLoginGold;

    /**
     * 注册并登陆奖励零钱
     */
    private Integer registLoginMoney;

    /**
     * 金币转零钱汇率
     */
    private BigDecimal goldToMoney;

    /**
     * 签到奖金币活动天数，单位：天，-1表示永不过期
     */
    private Integer signActivityDays;

    /**
     * 阅读奖金币活动时间,单位：天，-1表示永不过期
     */
    private Integer readActivityDays;

    /**
     * 新手阅读时限
     */
    private Integer newbieReadTime;

    /**
     * 唤醒徒弟奖励(师傅)
     */
    private Integer awakenParentGold;

    /**
     * 唤醒徒弟奖励(徒弟)
     */
    private Integer awakenUserGold;

    /**
     * 阅读掉落金币数量
     */
    private Integer readDownGold;

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
     * Getter method for property <tt>recruitGold</tt>.
     *
     * @return property value of recruitGold
     */
    public Integer getRecruitGold() {
        return recruitGold;
    }

    /**
     * Setter method for property <tt>recruitGold</tt>.
     *
     * @param recruitGold value to be assigned to property recruitGold
     */
    public void setRecruitGold(Integer recruitGold) {
        this.recruitGold = recruitGold;
    }

    /**
     * Getter method for property <tt>recruitMoney</tt>.
     *
     * @return property value of recruitMoney
     */
    public BigDecimal getRecruitMoney() {
        return recruitMoney;
    }

    /**
     * Setter method for property <tt>recruitMoney</tt>.
     *
     * @param recruitMoney value to be assigned to property recruitMoney
     */
    public void setRecruitMoney(BigDecimal recruitMoney) {
        this.recruitMoney = recruitMoney;
    }

    /**
     * Getter method for property <tt>newbieRecruitGold</tt>.
     *
     * @return property value of newbieRecruitGold
     */
    public Integer getNewbieRecruitGold() {
        return newbieRecruitGold;
    }

    /**
     * Setter method for property <tt>newbieRecruitGold</tt>.
     *
     * @param newbieRecruitGold value to be assigned to property newbieRecruitGold
     */
    public void setNewbieRecruitGold(Integer newbieRecruitGold) {
        this.newbieRecruitGold = newbieRecruitGold;
    }

    /**
     * Getter method for property <tt>newbieRecruitMoney</tt>.
     *
     * @return property value of newbieRecruitMoney
     */
    public BigDecimal getNewbieRecruitMoney() {
        return newbieRecruitMoney;
    }

    /**
     * Setter method for property <tt>newbieRecruitMoney</tt>.
     *
     * @param newbieRecruitMoney value to be assigned to property newbieRecruitMoney
     */
    public void setNewbieRecruitMoney(BigDecimal newbieRecruitMoney) {
        this.newbieRecruitMoney = newbieRecruitMoney;
    }

    /**
     * Getter method for property <tt>registLoginGold</tt>.
     *
     * @return property value of registLoginGold
     */
    public Integer getRegistLoginGold() {
        return registLoginGold;
    }

    /**
     * Setter method for property <tt>registLoginGold</tt>.
     *
     * @param registLoginGold value to be assigned to property registLoginGold
     */
    public void setRegistLoginGold(Integer registLoginGold) {
        this.registLoginGold = registLoginGold;
    }

    /**
     * Getter method for property <tt>registLoginMoney</tt>.
     *
     * @return property value of registLoginMoney
     */
    public Integer getRegistLoginMoney() {
        return registLoginMoney;
    }

    /**
     * Setter method for property <tt>registLoginMoney</tt>.
     *
     * @param registLoginMoney value to be assigned to property registLoginMoney
     */
    public void setRegistLoginMoney(Integer registLoginMoney) {
        this.registLoginMoney = registLoginMoney;
    }

    /**
     * Getter method for property <tt>goldToMoney</tt>.
     *
     * @return property value of goldToMoney
     */
    public BigDecimal getGoldToMoney() {
        return goldToMoney;
    }

    /**
     * Setter method for property <tt>goldToMoney</tt>.
     *
     * @param goldToMoney value to be assigned to property goldToMoney
     */
    public void setGoldToMoney(BigDecimal goldToMoney) {
        this.goldToMoney = goldToMoney;
    }

    /**
     * Getter method for property <tt>signActivityDays</tt>.
     *
     * @return property value of signActivityDays
     */
    public Integer getSignActivityDays() {
        return signActivityDays;
    }

    /**
     * Setter method for property <tt>signActivityDays</tt>.
     *
     * @param signActivityDays value to be assigned to property signActivityDays
     */
    public void setSignActivityDays(Integer signActivityDays) {
        this.signActivityDays = signActivityDays;
    }

    /**
     * Getter method for property <tt>readActivityDays</tt>.
     *
     * @return property value of readActivityDays
     */
    public Integer getReadActivityDays() {
        return readActivityDays;
    }

    /**
     * Setter method for property <tt>readActivityDays</tt>.
     *
     * @param readActivityDays value to be assigned to property readActivityDays
     */
    public void setReadActivityDays(Integer readActivityDays) {
        this.readActivityDays = readActivityDays;
    }

    /**
     * Getter method for property <tt>newbieReadTime</tt>.
     *
     * @return property value of newbieReadTime
     */
    public Integer getNewbieReadTime() {
        return newbieReadTime;
    }

    /**
     * Setter method for property <tt>newbieReadTime</tt>.
     *
     * @param newbieReadTime value to be assigned to property newbieReadTime
     */
    public void setNewbieReadTime(Integer newbieReadTime) {
        this.newbieReadTime = newbieReadTime;
    }

    /**
     * Getter method for property <tt>awakenParentGold</tt>.
     *
     * @return property value of awakenParentGold
     */
    public Integer getAwakenParentGold() {
        return awakenParentGold;
    }

    /**
     * Setter method for property <tt>awakenParentGold</tt>.
     *
     * @param awakenParentGold value to be assigned to property awakenParentGold
     */
    public void setAwakenParentGold(Integer awakenParentGold) {
        this.awakenParentGold = awakenParentGold;
    }

    /**
     * Getter method for property <tt>awakenUserGold</tt>.
     *
     * @return property value of awakenUserGold
     */
    public Integer getAwakenUserGold() {
        return awakenUserGold;
    }

    /**
     * Setter method for property <tt>awakenUserGold</tt>.
     *
     * @param awakenUserGold value to be assigned to property awakenUserGold
     */
    public void setAwakenUserGold(Integer awakenUserGold) {
        this.awakenUserGold = awakenUserGold;
    }

    /**
     * Getter method for property <tt>readDownGold</tt>.
     *
     * @return property value of readDownGold
     */
    public Integer getReadDownGold() {
        return readDownGold;
    }

    /**
     * Setter method for property <tt>readDownGold</tt>.
     *
     * @param readDownGold value to be assigned to property readDownGold
     */
    public void setReadDownGold(Integer readDownGold) {
        this.readDownGold = readDownGold;
    }
}