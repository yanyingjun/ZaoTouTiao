/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 推送消息表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserJpush implements Serializable {

    private static final long serialVersionUID = -2411066269375089457L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 推送类型（NOTICE 公告，MSG 消息 , NEWS 热文）
     */
    private String type;

    /**
     * 新闻ID
     */
    private String infoId;

    /**
     * 消息公告ID
     */
    private Integer informationId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 是否阅读（0 还没阅读 ，1已阅读）
     */
    private Integer isRead;

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

    /**
     * Getter method for property <tt>infoId</tt>.
     *
     * @return property value of infoId
     */
    public String getInfoId() {
        return infoId;
    }

    /**
     * Setter method for property <tt>infoId</tt>.
     *
     * @param infoId value to be assigned to property infoId
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    /**
     * Getter method for property <tt>informationId</tt>.
     *
     * @return property value of informationId
     */
    public Integer getInformationId() {
        return informationId;
    }

    /**
     * Setter method for property <tt>informationId</tt>.
     *
     * @param informationId value to be assigned to property informationId
     */
    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
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
     * Getter method for property <tt>isRead</tt>.
     *
     * @return property value of isRead
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * Setter method for property <tt>isRead</tt>.
     *
     * @param isRead value to be assigned to property isRead
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}