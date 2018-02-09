/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 用户签到表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserSignIn implements Serializable {

    private static final long serialVersionUID = -784084013497431883L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 连续签到天数
     */
    private Integer signContinuousDay;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 构造方法
     */
    public UserSignIn(Long id, Long userId, Integer signContinuousDay, String createDate) {
        this.id = id;
        this.userId = userId;
        this.signContinuousDay = signContinuousDay;
        this.createDate = createDate;
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
     * Getter method for property <tt>userid</tt>.
     *
     * @return property value of userid
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userid</tt>.
     *
     * @param userid value to be assigned to property userid
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>signContinuousDay</tt>.
     *
     * @return property value of signContinuousDay
     */
    public Integer getSignContinuousDay() {
        return signContinuousDay;
    }

    /**
     * Setter method for property <tt>signContinuousDay</tt>.
     *
     * @param signContinuousDay value to be assigned to property signContinuousDay
     */
    public void setSignContinuousDay(Integer signContinuousDay) {
        this.signContinuousDay = signContinuousDay;
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
}