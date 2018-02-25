/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 新闻分类表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class Channels implements Serializable {

    private static final long serialVersionUID = -5716137180911774953L;
    /**
     * ID
     */
    private Long id;

    /**
     * 导航ID
     */
    private Integer channelId;

    /**
     * 导航名称
     */
    private String name;

    /**
     * 导航排序
     */
    private Integer channelOrder;

    /**
     * 导航类型
     */
    private Integer channelType;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 激活状态
     */
    private Integer status;

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
     * Getter method for property <tt>channelId</tt>.
     *
     * @return property value of channelId
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * Setter method for property <tt>channelId</tt>.
     *
     * @param channelId value to be assigned to property channelId
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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
     * Getter method for property <tt>channelOrder</tt>.
     *
     * @return property value of channelOrder
     */
    public Integer getChannelOrder() {
        return channelOrder;
    }

    /**
     * Setter method for property <tt>channelOrder</tt>.
     *
     * @param channelOrder value to be assigned to property channelOrder
     */
    public void setChannelOrder(Integer channelOrder) {
        this.channelOrder = channelOrder;
    }

    /**
     * Getter method for property <tt>channelType</tt>.
     *
     * @return property value of channelType
     */
    public Integer getChannelType() {
        return channelType;
    }

    /**
     * Setter method for property <tt>channelType</tt>.
     *
     * @param channelType value to be assigned to property channelType
     */
    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
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
     * Getter method for property <tt>updateDate</tt>.
     *
     * @return property value of updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * Setter method for property <tt>updateDate</tt>.
     *
     * @param updateDate value to be assigned to property updateDate
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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
}