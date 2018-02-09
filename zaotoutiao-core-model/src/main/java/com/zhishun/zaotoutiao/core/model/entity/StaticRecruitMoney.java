/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 收徒奖励金币配置表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticRecruitMoney implements Serializable {

    private static final long serialVersionUID = -8651333755633708210L;
    /**
     * ID
     */
    private Long id;

    /**
     * 单位
     */
    private Long unit;

    /**
     * 零钱数
     */
    private BigDecimal money;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 等级
     */
    private Integer rank;

    /**
     * 构造方法
     */
    public StaticRecruitMoney(Long id, Long unit, BigDecimal money, String createDate, Integer rank) {
        this.id = id;
        this.unit = unit;
        this.money = money;
        this.createDate = createDate;
        this.rank = rank;
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
     * Getter method for property <tt>unit</tt>.
     *
     * @return property value of unit
     */
    public Long getUnit() {
        return unit;
    }

    /**
     * Setter method for property <tt>unit</tt>.
     *
     * @param unit value to be assigned to property unit
     */
    public void setUnit(Long unit) {
        this.unit = unit;
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
     * Getter method for property <tt>rank</tt>.
     *
     * @return property value of rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Setter method for property <tt>rank</tt>.
     *
     * @param rank value to be assigned to property rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }
}