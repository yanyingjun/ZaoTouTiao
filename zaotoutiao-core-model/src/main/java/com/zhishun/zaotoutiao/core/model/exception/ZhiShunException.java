package com.zhishun.zaotoutiao.core.model.exception;


import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;

/**
 * 自定义异常
 */
public class ZhiShunException extends Exception {

    /**
     * @fields serialVersionUID : 序列号
     */
    private static final long serialVersionUID = -1552235609039936234L;

    /**
     * @fields errorCodeEnum : 错误信息枚举
     */
    private ErrorCodeEnum errorCodeEnum;

    /**
     * @fields errMsg:不适合使用errorCodeEnum时使用
     */
    private String errMsg;

    /**
     * 构造方法
     */
    public ZhiShunException() {
        super();
    }

    /**
     * 构造方法
     *
     * @param errorCodeEnum
     */
    public ZhiShunException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.errorCodeEnum = errorCodeEnum;
    }

    /**
     * 构造方法
     *
     * @param errMsg
     */
    public ZhiShunException(String errMsg) {
        super();
        this.errMsg = errMsg;
    }

    /**
     * Getter method for property <tt>errorCodeEnum</tt>.
     *
     * @return property value of errorCodeEnum
     */
    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    /**
     * Setter method for property <tt>errorCodeEnum</tt>.
     *
     * @param errorCodeEnum value to be assigned to property errorMsgEnum
     */
    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    /**
     * Getter method for property <tt>errMsg</tt>.
     *
     * @return property value of errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Setter method for property <tt>errMsg</tt>.
     *
     * @param errMsg value to be assigned to property errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}