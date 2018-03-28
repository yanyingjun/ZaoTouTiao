/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserGoldRecordVO, v0.1 2018年02月26日 19:18闫迎军(YanYingJun) Exp $
 */
public class UserGoldRecordVO extends UserGoldRecord{

    /**
     * 解释
     */
    private String explanation;

    /**
     * 详情
     */
    private String details;

    /**
     * 金币
     */
    private int sumGold;

    /**
     * 姓名
     */
    private String apprenticeName;

    /**
     * 昵称
     */
    private String apprenticeNickName;

    /**
     * 分组时间
     */
    private String times;




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
     * Getter method for property <tt>sumGold</tt>.
     *
     * @return property value of sumGold
     */
    public int getSumGold() {
        return sumGold;
    }

    /**
     * Setter method for property <tt>sumGold</tt>.
     *
     * @param sumGold value to be assigned to property sumGold
     */
    public void setSumGold(int sumGold) {
        this.sumGold = sumGold;
    }

    /**
     * Getter method for property <tt>apprenticeName</tt>.
     *
     * @return property value of apprenticeName
     */
    public String getApprenticeName() {
        return apprenticeName;
    }

    /**
     * Setter method for property <tt>apprenticeName</tt>.
     *
     * @param apprenticeName value to be assigned to property apprenticeName
     */
    public void setApprenticeName(String apprenticeName) {
        this.apprenticeName = apprenticeName;
    }

    /**
     * Getter method for property <tt>apprenticeNickName</tt>.
     *
     * @return property value of apprenticeNickName
     */
    public String getApprenticeNickName() {
        return apprenticeNickName;
    }

    /**
     * Setter method for property <tt>apprenticeNickName</tt>.
     *
     * @param apprenticeNickName value to be assigned to property apprenticeNickName
     */
    public void setApprenticeNickName(String apprenticeNickName) {
        this.apprenticeNickName = apprenticeNickName;
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
