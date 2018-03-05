/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserBehavior;

import java.math.BigDecimal;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserBehaviorVO, v0.1 2018年03月03日 10:54闫迎军(YanYingJun) Exp $
 */
public class UserBehaviorVO extends UserBehavior{

    /**
     * 激活率
     */
    private BigDecimal activationRate;

    /**
     * 注册率
     */
    private BigDecimal registerRate;

    /**
     * 分组日期
     */
    private String times;

    /**
     * Getter method for property <tt>activationRate</tt>.
     *
     * @return property value of activationRate
     */
    public BigDecimal getActivationRate() {
        return activationRate;
    }

    /**
     * Setter method for property <tt>activationRate</tt>.
     *
     * @param activationRate value to be assigned to property activationRate
     */
    public void setActivationRate(BigDecimal activationRate) {
        this.activationRate = activationRate;
    }

    /**
     * Getter method for property <tt>registerRate</tt>.
     *
     * @return property value of registerRate
     */
    public BigDecimal getRegisterRate() {
        return registerRate;
    }

    /**
     * Setter method for property <tt>registerRate</tt>.
     *
     * @param registerRate value to be assigned to property registerRate
     */
    public void setRegisterRate(BigDecimal registerRate) {
        this.registerRate = registerRate;
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
}
