/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: Adslot, v0.1 2018年03月13日 16:25闫迎军(YanYingJun) Exp $
 */
public class Adslot {

    /**
     * 广告位ID,由aiclk提供
     */
    private String id;

    /**
     * 广告位类型（1,NATIVE;2.BANNER）
     */
    private Integer type;

    /**
     * 广告位高尺寸
     */
    private Integer height;

    /**
     * 广告位宽尺寸
     */
    private Integer width;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
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
     * Getter method for property <tt>height</tt>.
     *
     * @return property value of height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Setter method for property <tt>height</tt>.
     *
     * @param height value to be assigned to property height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Getter method for property <tt>width</tt>.
     *
     * @return property value of width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Setter method for property <tt>width</tt>.
     *
     * @param width value to be assigned to property width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

}
