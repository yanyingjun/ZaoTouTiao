/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 静态网页表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticHtml implements Serializable {

    private static final long serialVersionUID = 760931882183968603L;
    /**
     * ID
     */
    private Long staticId;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 标题（ABOUT_US 关于我们;  \\n AGREEMENT 隐私协议;  \\n NEWBIE 新手必看;  \\n WAKE_UP 唤醒规则;  \\n SHARE_RECRUIT 分享收徒）
     */
    private String title;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     *
     */
    private String content;

    /**
     * 构造方法
     */
    public StaticHtml(Long staticId, String name, String title, String createDate, String content) {
        this.staticId = staticId;
        this.name = name;
        this.title = title;
        this.createDate = createDate;
        this.content = content;
    }

    /**
     * Getter method for property <tt>staticId</tt>.
     *
     * @return property value of staticId
     */
    public Long getStaticId() {
        return staticId;
    }

    /**
     * Setter method for property <tt>staticId</tt>.
     *
     * @param staticId value to be assigned to property staticId
     */
    public void setStaticId(Long staticId) {
        this.staticId = staticId;
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
}