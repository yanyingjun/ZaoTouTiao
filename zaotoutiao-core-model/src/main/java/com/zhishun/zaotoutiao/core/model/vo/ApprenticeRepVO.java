/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ApprenticeRepVO, v0.1 2018年03月27日 20:51闫迎军(YanYingJun) Exp $
 */
public class ApprenticeRepVO {


    private int sumGold;


    private int apprenticeId;


    private String nickName;

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
     * Getter method for property <tt>apprenticeId</tt>.
     *
     * @return property value of apprenticeId
     */
    public int getApprenticeId() {
        return apprenticeId;
    }

    /**
     * Setter method for property <tt>apprenticeId</tt>.
     *
     * @param apprenticeId value to be assigned to property apprenticeId
     */
    public void setApprenticeId(int apprenticeId) {
        this.apprenticeId = apprenticeId;
    }

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
}
