/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 假数据列表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticFakeData implements Serializable {

    private static final long serialVersionUID = 6763204803195628037L;
    /**
     * ID
     */
    private Long id;

    /**
     * 假数据内容
     */
    private String content;

    /**
     * 假数据内容
     */
    private String content1;

    /**
     * 假数据内容
     */
    private String content2;

    /**
     * 假数据内容
     */
    private String content3;

    /**
     * 假数据类型（RECRUIT收徒 ，REGISTER注册红包, week_rankings周排行，all_rankings 总排行）
     */
    private String type;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 假数据内容
     */
    private String content4;

    /**
     * 构造方法
     */
    public StaticFakeData(Long id, String content, String content1, String content2, String content3, String type, String createDate, String content4) {
        this.id = id;
        this.content = content;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.type = type;
        this.createDate = createDate;
        this.content4 = content4;
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
     * Getter method for property <tt>content1</tt>.
     *
     * @return property value of content1
     */
    public String getContent1() {
        return content1;
    }

    /**
     * Setter method for property <tt>content1</tt>.
     *
     * @param content1 value to be assigned to property content1
     */
    public void setContent1(String content1) {
        this.content1 = content1;
    }

    /**
     * Getter method for property <tt>content2</tt>.
     *
     * @return property value of content2
     */
    public String getContent2() {
        return content2;
    }

    /**
     * Setter method for property <tt>content2</tt>.
     *
     * @param content2 value to be assigned to property content2
     */
    public void setContent2(String content2) {
        this.content2 = content2;
    }

    /**
     * Getter method for property <tt>content3</tt>.
     *
     * @return property value of content3
     */
    public String getContent3() {
        return content3;
    }

    /**
     * Setter method for property <tt>content3</tt>.
     *
     * @param content3 value to be assigned to property content3
     */
    public void setContent3(String content3) {
        this.content3 = content3;
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
     * Getter method for property <tt>content4</tt>.
     *
     * @return property value of content4
     */
    public String getContent4() {
        return content4;
    }

    /**
     * Setter method for property <tt>content4</tt>.
     *
     * @param content4 value to be assigned to property content4
     */
    public void setContent4(String content4) {
        this.content4 = content4;
    }
}