/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.common;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: QueryRedPack, v0.1 2018年03月01日 14:57闫迎军(YanYingJun) Exp $
 */
public class QueryRedPack {

    private String nonce_str;
    private String sign;
    private String mch_billno;
    private String mch_id;
    private String appid;
    private String bill_type = "MCHT";

    /**
     * Getter method for property <tt>nonce_str</tt>.
     *
     * @return property value of nonce_str
     */
    public String getNonce_str() {
        return nonce_str;
    }

    /**
     * Setter method for property <tt>nonce_str</tt>.
     *
     * @param nonce_str value to be assigned to property nonce_str
     */
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    /**
     * Getter method for property <tt>sign</tt>.
     *
     * @return property value of sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * Setter method for property <tt>sign</tt>.
     *
     * @param sign value to be assigned to property sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * Getter method for property <tt>mch_billno</tt>.
     *
     * @return property value of mch_billno
     */
    public String getMch_billno() {
        return mch_billno;
    }

    /**
     * Setter method for property <tt>mch_billno</tt>.
     *
     * @param mch_billno value to be assigned to property mch_billno
     */
    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    /**
     * Getter method for property <tt>mch_id</tt>.
     *
     * @return property value of mch_id
     */
    public String getMch_id() {
        return mch_id;
    }

    /**
     * Setter method for property <tt>mch_id</tt>.
     *
     * @param mch_id value to be assigned to property mch_id
     */
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    /**
     * Getter method for property <tt>appid</tt>.
     *
     * @return property value of appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * Setter method for property <tt>appid</tt>.
     *
     * @param appid value to be assigned to property appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * Getter method for property <tt>bill_type</tt>.
     *
     * @return property value of bill_type
     */
    public String getBill_type() {
        return bill_type;
    }

    /**
     * Setter method for property <tt>bill_type</tt>.
     *
     * @param bill_type value to be assigned to property bill_type
     */
    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }
}
