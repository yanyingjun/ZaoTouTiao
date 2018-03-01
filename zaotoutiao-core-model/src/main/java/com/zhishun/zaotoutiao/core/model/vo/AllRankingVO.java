/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import java.math.BigDecimal;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: AllRankingVO, v0.1 2018年02月28日 21:32闫迎军(YanYingJun) Exp $
 */
public class AllRankingVO {


    private Long userId;


    private BigDecimal allSum;

    /**
     * 徒弟总数
     */
    private Integer apprenticeSum;

    /**
     * 用户名称
     */
    private String name;

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
     * Getter method for property <tt>allSum</tt>.
     *
     * @return property value of allSum
     */
    public BigDecimal getAllSum() {
        return allSum;
    }

    /**
     * Setter method for property <tt>allSum</tt>.
     *
     * @param allSum value to be assigned to property allSum
     */
    public void setAllSum(BigDecimal allSum) {
        this.allSum = allSum;
    }

    /**
     * Getter method for property <tt>apprenticeSum</tt>.
     *
     * @return property value of apprenticeSum
     */
    public Integer getApprenticeSum() {
        return apprenticeSum;
    }

    /**
     * Setter method for property <tt>apprenticeSum</tt>.
     *
     * @param apprenticeSum value to be assigned to property apprenticeSum
     */
    public void setApprenticeSum(Integer apprenticeSum) {
        this.apprenticeSum = apprenticeSum;
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
}
