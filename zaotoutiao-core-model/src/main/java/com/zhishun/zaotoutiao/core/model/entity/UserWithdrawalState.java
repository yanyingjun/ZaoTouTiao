/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 体现状态实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserWithdrawalState implements Serializable {

    private static final long serialVersionUID = 2755017455458514697L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 当前状态（0:申请中  1:红包已发放，待领取  2:提现完成 3:红包超时未领取，已退还）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 订单编号
     */
    private String billNo;

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
     * @param userId value to be assigned to property userid
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * Getter method for property <tt>billNo</tt>.
     *
     * @return property value of billNo
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * Setter method for property <tt>billNo</tt>.
     *
     * @param billNo value to be assigned to property billNo
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}