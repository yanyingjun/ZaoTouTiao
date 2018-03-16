/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * divice对象
 * @author 闫迎军(YanYingJun)
 * @version $Id: Device, v0.1 2018年03月13日 16:09闫迎军(YanYingJun) Exp $
 */
public class Device {

    /**
     * ios必须要传
     */
    private String id_idfa;

    /**
     * android必须要传
     */
    private String id_imei;

    /**
     * 屏幕的物理高度，以像素为单位
     */
    private Integer height;

    /**
     * 屏幕的物理宽度，以像素为单位
     */
    private Integer width;

    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 设备操作系统（需要三位数，例如3.1.2）
     */
    private String os_version;

    /**
     * 操作系统类型（1.ANDROID;2.IOS;3.WP）
     */
    private Integer os_type;

    /**
     * Getter method for property <tt>id_idfa</tt>.
     *
     * @return property value of id_idfa
     */
    public String getId_idfa() {
        return id_idfa;
    }

    /**
     * Setter method for property <tt>id_idfa</tt>.
     *
     * @param id_idfa value to be assigned to property id_idfa
     */
    public void setId_idfa(String id_idfa) {
        this.id_idfa = id_idfa;
    }

    /**
     * Getter method for property <tt>id_imei</tt>.
     *
     * @return property value of id_imei
     */
    public String getId_imei() {
        return id_imei;
    }

    /**
     * Setter method for property <tt>id_imei</tt>.
     *
     * @param id_imei value to be assigned to property id_imei
     */
    public void setId_imei(String id_imei) {
        this.id_imei = id_imei;
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

    /**
     * Getter method for property <tt>brand</tt>.
     *
     * @return property value of brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter method for property <tt>brand</tt>.
     *
     * @param brand value to be assigned to property brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter method for property <tt>model</tt>.
     *
     * @return property value of model
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter method for property <tt>model</tt>.
     *
     * @param model value to be assigned to property model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter method for property <tt>os_version</tt>.
     *
     * @return property value of os_version
     */
    public String getOs_version() {
        return os_version;
    }

    /**
     * Setter method for property <tt>os_version</tt>.
     *
     * @param os_version value to be assigned to property os_version
     */
    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    /**
     * Getter method for property <tt>os_type</tt>.
     *
     * @return property value of os_type
     */
    public Integer getOs_type() {
        return os_type;
    }

    /**
     * Setter method for property <tt>os_type</tt>.
     *
     * @param os_type value to be assigned to property os_type
     */
    public void setOs_type(Integer os_type) {
        this.os_type = os_type;
    }
}
