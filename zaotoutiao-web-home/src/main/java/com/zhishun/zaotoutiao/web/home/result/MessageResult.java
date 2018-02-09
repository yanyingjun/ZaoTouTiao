/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2012-2016
 */
package com.zhishun.zaotoutiao.web.home.result;

/**
 * 执行信息结果
 *
 */
public class MessageResult {

    /** serialVersionUID */
    private static final long serialVersionUID = 581061410857998563L;

    /** 客户端（系统） */
    private String            appName;

    /** 是否成功 */
    private boolean           success;

    /** 操作状态 */
    private String            status;

    /** 错误Code值 */
    private String            errorCode;

    /** 错误信息 */
    private String            errorMessage;

    /**
     * 无参构造方法
     */
    public MessageResult() {
        super();
    }

    /**
     * 有参构造方法
     * 
     * @param appName 系统名称
     * @param success 是否成功
     * @param status 结果状态
     * @param errorCode 错误Code值
     * @param errorMessage 错误信息
     */
    public MessageResult(String appName, boolean success, String status, String errorCode,
                         String errorMessage) {
        super();
        this.appName = appName;
        this.success = success;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Getter method for property <tt>appName</tt>.
     * 
     * @return property value of appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Setter method for property <tt>appName</tt>.
     * 
     * @param appName value to be assigned to property appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Getter method for property <tt>success</tt>.
     * 
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     * 
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     * 
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     * 
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorMessage</tt>.
     * 
     * @return property value of errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Setter method for property <tt>errorMessage</tt>.
     * 
     * @param errorMessage value to be assigned to property errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}