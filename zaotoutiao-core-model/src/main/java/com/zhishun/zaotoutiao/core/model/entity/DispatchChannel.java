/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class DispatchChannel implements Serializable {

    private static final long serialVersionUID = 5187040621053953175L;
    /**
     * ID
     */
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String apkLink;

    /**
     *
     */
    private String code;

    /**
     *
     */
    private String intro;

    /**
     *
     */
    public DispatchChannel(Long id, String name, String apkLink, String code, String intro) {
        this.id = id;
        this.name = name;
        this.apkLink = apkLink;
        this.code = code;
        this.intro = intro;
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
     * Getter method for property <tt>apkLink</tt>.
     *
     * @return property value of apkLink
     */
    public String getApkLink() {
        return apkLink;
    }

    /**
     * Setter method for property <tt>apkLink</tt>.
     *
     * @param apkLink value to be assigned to property apkLink
     */
    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>intro</tt>.
     *
     * @return property value of intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * Setter method for property <tt>intro</tt>.
     *
     * @param intro value to be assigned to property intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }
}