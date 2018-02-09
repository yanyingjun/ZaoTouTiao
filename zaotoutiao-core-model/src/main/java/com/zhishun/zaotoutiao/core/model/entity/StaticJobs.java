/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 岗位表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticJobs implements Serializable {

    private static final long serialVersionUID = -4264569328662882333L;
    /**
     * ID
     */
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 行业ID
     */
    private Long industrysId;

    /**
     * 构造方法
     */
    public StaticJobs(Long id, String name, Long industrysId) {
        this.id = id;
        this.name = name;
        this.industrysId = industrysId;
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
     * Getter method for property <tt>industrysId</tt>.
     *
     * @return property value of industrysId
     */
    public Long getIndustrysId() {
        return industrysId;
    }

    /**
     * Setter method for property <tt>industrysId</tt>.
     *
     * @param industrysId value to be assigned to property industrysId
     */
    public void setIndustrysId(Long industrysId) {
        this.industrysId = industrysId;
    }
}