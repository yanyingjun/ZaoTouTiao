/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 金币和零钱来源解释表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticGoldMoneySource implements Serializable {

    private static final long serialVersionUID = 2698337786006845357L;
    /**
     * ID
     */
    private Integer id;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 解释
     */
    private String explanation;

    /**
     * 详情
     */
    private String details;

    /**
     * 类型
     */
    private String type;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }
}