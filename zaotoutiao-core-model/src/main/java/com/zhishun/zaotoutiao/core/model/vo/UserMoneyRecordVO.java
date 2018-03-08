/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserMoneyRecord;

import java.math.BigDecimal;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserMoneyRecordVO, v0.1 2018年02月26日 19:39闫迎军(YanYingJun) Exp $
 */
public class UserMoneyRecordVO extends UserMoneyRecord{

    /**
     * 解释
     */
    private String explanation;

    /**
     * 详情
     */
    private String details;

    /**
     * 分组时间
     */
    private String times;

    /**
     * 零钱总数量
     */
    private BigDecimal sumMoney;

    /**
     * Getter method for property <tt>explanation</tt>.
     *
     * @return property value of explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Setter method for property <tt>explanation</tt>.
     *
     * @param explanation value to be assigned to property explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * Getter method for property <tt>details</tt>.
     *
     * @return property value of details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Setter method for property <tt>details</tt>.
     *
     * @param details value to be assigned to property details
     */
    public void setDetails(String details) {
        this.details = details;
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
     * Getter method for property <tt>sumMoney</tt>.
     *
     * @return property value of sumMoney
     */
    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    /**
     * Setter method for property <tt>sumMoney</tt>.
     *
     * @param sumMoney value to be assigned to property sumMoney
     */
    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }
}
