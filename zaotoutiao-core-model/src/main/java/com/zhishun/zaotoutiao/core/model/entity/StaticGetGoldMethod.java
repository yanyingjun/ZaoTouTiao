/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 如何获取金币表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticGetGoldMethod implements Serializable {

    private static final long serialVersionUID = 702171494354880068L;
    /**
     * ID
     */
    private Long id;

    /**
     * 步骤
     */
    private String step;

    /**
     * 详情
     */
    private String details;

    /**
     * 按钮名称
     */
    private String buttonName;

    /**
     * 跳转链接
     */
    private String skipUrl;

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
     * Getter method for property <tt>step</tt>.
     *
     * @return property value of step
     */
    public String getStep() {
        return step;
    }

    /**
     * Setter method for property <tt>step</tt>.
     *
     * @param step value to be assigned to property step
     */
    public void setStep(String step) {
        this.step = step;
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
     * Getter method for property <tt>buttonName</tt>.
     *
     * @return property value of buttonName
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * Setter method for property <tt>buttonName</tt>.
     *
     * @param buttonName value to be assigned to property buttonName
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * Getter method for property <tt>skipUrl</tt>.
     *
     * @return property value of skipUrl
     */
    public String getSkipUrl() {
        return skipUrl;
    }

    /**
     * Setter method for property <tt>skipUrl</tt>.
     *
     * @param skipUrl value to be assigned to property skipUrl
     */
    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }
}