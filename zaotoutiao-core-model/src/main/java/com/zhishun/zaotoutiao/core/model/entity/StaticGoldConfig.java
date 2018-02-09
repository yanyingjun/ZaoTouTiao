/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 金币配置表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticGoldConfig implements Serializable {

    private static final long serialVersionUID = -6225452281409544388L;
    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 获得的几率,天数，小时，次数
     */
    private Integer unit;

    /**
     * 可以获得的金币数
     */
    private String value;

    /**
     * 介绍
     */
    private String comments;

    /**
     * 单位（1:获得几率数，2：天，3：小时，4：点赞加评论次数）
     */
    private Integer type;

    /**
     * 每日最高奖励金币数
     */
    private Integer highest;

    /**
     * 构造方法
     */
    public StaticGoldConfig(Long id, String name, Integer unit, String value, String comments, Integer type, Integer highest) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.value = value;
        this.comments = comments;
        this.type = type;
        this.highest = highest;
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

    /**
     * Getter method for property <tt>unit</tt>.
     *
     * @return property value of unit
     */
    public Integer getUnit() {
        return unit;
    }

    /**
     * Setter method for property <tt>unit</tt>.
     *
     * @param unit value to be assigned to property unit
     */
    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter method for property <tt>comments</tt>.
     *
     * @return property value of comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter method for property <tt>comments</tt>.
     *
     * @param comments value to be assigned to property comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>highest</tt>.
     *
     * @return property value of highest
     */
    public Integer getHighest() {
        return highest;
    }

    /**
     * Setter method for property <tt>highest</tt>.
     *
     * @param highest value to be assigned to property highest
     */
    public void setHighest(Integer highest) {
        this.highest = highest;
    }
}