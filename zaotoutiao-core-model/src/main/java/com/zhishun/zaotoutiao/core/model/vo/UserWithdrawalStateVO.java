/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserWithdrawalState;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserWithdrawalStateVO, v0.1 2018年03月20日 9:36闫迎军(YanYingJun) Exp $
 */
public class UserWithdrawalStateVO extends UserWithdrawalState{

    private String nickName;


    private String telephone;


    private String createDate;


    private String lastVisitDate;


    private Integer readNumber;


    private Integer apprenticeNum;


    private String channelName;


    private String apprenticeRate;


    private Integer auditStatus;


    private Integer accountStatus;


    private Integer incomeStatus;


    private Integer presentStatus;


    private Integer speechStatus;


    /**
     * Getter method for property <tt>nickName</tt>.
     *
     * @return property value of nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickName</tt>.
     *
     * @param nickName value to be assigned to property nickName
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
     * Getter method for property <tt>createDate</tt>.
     *
     * @return property value of createDate
     */
    @Override
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     *
     * @param createDate value to be assigned to property createDate
     */
    @Override
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    /**
     * Getter method for property <tt>apprenticeRate</tt>.
     *
     * @return property value of apprenticeRate
     */
    public String getApprenticeRate() {
        return apprenticeRate;
    }

    /**
     * Setter method for property <tt>apprenticeRate</tt>.
     *
     * @param apprenticeRate value to be assigned to property apprenticeRate
     */
    public void setApprenticeRate(String apprenticeRate) {
        this.apprenticeRate = apprenticeRate;
    }

    /**
     * Getter method for property <tt>presentStatus</tt>.
     *
     * @return property value of presentStatus
     */
    public Integer getPresentStatus() {
        return presentStatus;
    }

    /**
     * Setter method for property <tt>presentStatus</tt>.
     *
     * @param presentStatus value to be assigned to property presentStatus
     */
    public void setPresentStatus(Integer presentStatus) {
        this.presentStatus = presentStatus;
    }

    /**
     * Getter method for property <tt>auditStatus</tt>.
     *
     * @return property value of auditStatus
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * Setter method for property <tt>auditStatus</tt>.
     *
     * @param auditStatus value to be assigned to property auditStatus
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * Getter method for property <tt>accountStatus</tt>.
     *
     * @return property value of accountStatus
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    /**
     * Setter method for property <tt>accountStatus</tt>.
     *
     * @param accountStatus value to be assigned to property accountStatus
     */
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * Getter method for property <tt>incomeStatus</tt>.
     *
     * @return property value of incomeStatus
     */
    public Integer getIncomeStatus() {
        return incomeStatus;
    }

    /**
     * Setter method for property <tt>incomeStatus</tt>.
     *
     * @param incomeStatus value to be assigned to property incomeStatus
     */
    public void setIncomeStatus(Integer incomeStatus) {
        this.incomeStatus = incomeStatus;
    }

    /**
     * Getter method for property <tt>speechStatus</tt>.
     *
     * @return property value of speechStatus
     */
    public Integer getSpeechStatus() {
        return speechStatus;
    }

    /**
     * Setter method for property <tt>speechStatus</tt>.
     *
     * @param speechStatus value to be assigned to property speechStatus
     */
    public void setSpeechStatus(Integer speechStatus) {
        this.speechStatus = speechStatus;
    }
}
