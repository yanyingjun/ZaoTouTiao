/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.enums;

/**
 * 错误信息枚举
 * @author 闫迎军(YanYingJun)
 * @version $Id: ErrorCodeEnum, v0.1 2018年02月06日 20:36闫迎军(YanYingJun) Exp $
 */
public enum ErrorCodeEnum {

    //********************************** 系统错误枚举 ********************************************
    /**
     * 请求成功
     */
    SUCCESS("E2016A000000001", "请求成功"),

    /**
     * 对不起，系统发生异常
     */
    SYSTEM_ANOMALY("E2016A000000002", "对不起，系统发生异常"),

    /**
     * 对不起，参数异常
     */
    PARAMETER_ANOMALY("E2016A000000002", "对不起，参数异常");

    //================================================================

    /**
     * @fields code : 错误Code值
     */
    private String errorCode;

    /**
     * @fields message : 错误信息
     */
    private String errorMessage;

    /**
     * 构造方法
     *
     * @param errorCode    错误Code
     * @param errorMessage 错误信息
     */
    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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
     * Getter method for property <tt>errorMessage</tt>.
     *
     * @return property value of errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
