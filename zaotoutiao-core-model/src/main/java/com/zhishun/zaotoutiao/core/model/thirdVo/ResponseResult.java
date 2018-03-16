/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.thirdVo;

/**
 * 第三方返回实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: ResponseResult, v0.1 2018年03月07日 20:25闫迎军(YanYingJun) Exp $
 */
public class ResponseResult {

    /**
     * 错误码（0：表示没有错误）
     */
    private Integer errno;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 数据
     */
    private String data;


    private String sid;

    /**
     * 数据格式版本
     */
    private String version;

    /**
     * Getter method for property <tt>errno</tt>.
     *
     * @return property value of errno
     */
    public Integer getErrno() {
        return errno;
    }

    /**
     * Setter method for property <tt>errno</tt>.
     *
     * @param errno value to be assigned to property errno
     */
    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    /**
     * Getter method for property <tt>errmsg</tt>.
     *
     * @return property value of errmsg
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * Setter method for property <tt>errmsg</tt>.
     *
     * @param errmsg value to be assigned to property errmsg
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public String getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>sid</tt>.
     *
     * @return property value of sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * Setter method for property <tt>sid</tt>.
     *
     * @param sid value to be assigned to property sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * Getter method for property <tt>version</tt>.
     *
     * @return property value of version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter method for property <tt>version</tt>.
     *
     * @param version value to be assigned to property version
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
