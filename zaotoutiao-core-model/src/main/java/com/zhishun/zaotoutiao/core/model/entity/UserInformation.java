/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 用户消息记录表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 8018471865420041491L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 消息类型（MSG  消息   ，NOTICE  公告）
     */
    private String type;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 链接
     */
    private String url;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     *
     */
    private String groups;

    /**
     * 副标题
     */
    private String subhead;

    /**
     * 是否阅读（0没有阅读，1已阅读）
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
     * Getter method for property <tt>title</tt>.
     *
     * @return property value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for property <tt>title</tt>.
     *
     * @param title value to be assigned to property title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter method for property <tt>url</tt>.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     *
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
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
     * Getter method for property <tt>groups</tt>.
     *
     * @return property value of groups
     */
    public String getGroups() {
        return groups;
    }

    /**
     * Setter method for property <tt>groups</tt>.
     *
     * @param groups value to be assigned to property groups
     */
    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * Getter method for property <tt>subhead</tt>.
     *
     * @return property value of subhead
     */
    public String getSubhead() {
        return subhead;
    }

    /**
     * Setter method for property <tt>subhead</tt>.
     *
     * @param subhead value to be assigned to property subhead
     */
    public void setSubhead(String subhead) {
        this.subhead = subhead;
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