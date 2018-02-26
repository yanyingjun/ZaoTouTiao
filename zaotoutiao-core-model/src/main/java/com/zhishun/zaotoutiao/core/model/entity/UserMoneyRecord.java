/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 零钱记录表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class    UserMoneyRecord implements Serializable {

    private static final long serialVersionUID = 4905798915650520874L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 类型（增加：1，减少：0）
     */
    private Integer type;

    /**
     * 增加或减少的零钱
     */
    private BigDecimal money;

    /**
     * 来源去向（0:注册登录(新人礼包),1:金币转换零钱, 2零钱提现,3首次收徒, 4提现红包超时未领取退回）
     */
    private Integer source;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 徒弟ID(徒弟进贡时使用，表示当前用户id)
     */
    private Long apprenticeId;

    /**
     * 构造方法
     */
    public UserMoneyRecord(Long id, Long userId, Integer type, BigDecimal money, Integer source, String createDate, Long apprenticeId) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.money = money;
        this.source = source;
        this.createDate = createDate;
        this.apprenticeId = apprenticeId;
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
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
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
     * Getter method for property <tt>source</tt>.
     *
     * @return property value of source
     */
    public Integer getSource() {
        return source;
    }

    /**
     * Setter method for property <tt>source</tt>.
     *
     * @param source value to be assigned to property source
     */
    public void setSource(Integer source) {
        this.source = source;
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
     * Getter method for property <tt>apprenticeId</tt>.
     *
     * @return property value of apprenticeId
     */
    public Long getApprenticeId() {
        return apprenticeId;
    }

    /**
     * Setter method for property <tt>apprenticeId</tt>.
     *
     * @param apprenticeId value to be assigned to property apprenticeId
     */
    public void setApprenticeId(Long apprenticeId) {
        this.apprenticeId = apprenticeId;
    }
}