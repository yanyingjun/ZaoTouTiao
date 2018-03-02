/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.common;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: SendRedPack, v0.1 2018年03月01日 14:55闫迎军(YanYingJun) Exp $
 */
public class SendRedPack {

    private String nonce_str;
    private String sign;
    private String mch_billno;
    private String mch_id;
    private String wxappid;
    private String send_name;
    private String re_openid;
    private int total_amount;
    private int total_num;
    private String wishing;
    private String client_ip;
    private String act_name;
    private String remark;

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
     * Getter method for property <tt>wxappid</tt>.
     *
     * @return property value of wxappid
     */
    public String getWxappid() {
        return wxappid;
    }

    /**
     * Setter method for property <tt>wxappid</tt>.
     *
     * @param wxappid value to be assigned to property wxappid
     */
    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    /**
     * Getter method for property <tt>send_name</tt>.
     *
     * @return property value of send_name
     */
    public String getSend_name() {
        return send_name;
    }

    /**
     * Setter method for property <tt>send_name</tt>.
     *
     * @param send_name value to be assigned to property send_name
     */
    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    /**
     * Getter method for property <tt>re_openid</tt>.
     *
     * @return property value of re_openid
     */
    public String getRe_openid() {
        return re_openid;
    }

    /**
     * Setter method for property <tt>re_openid</tt>.
     *
     * @param re_openid value to be assigned to property re_openid
     */
    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    /**
     * Getter method for property <tt>total_amount</tt>.
     *
     * @return property value of total_amount
     */
    public int getTotal_amount() {
        return total_amount;
    }

    /**
     * Setter method for property <tt>total_amount</tt>.
     *
     * @param total_amount value to be assigned to property total_amount
     */
    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * Getter method for property <tt>total_num</tt>.
     *
     * @return property value of total_num
     */
    public int getTotal_num() {
        return total_num;
    }

    /**
     * Setter method for property <tt>total_num</tt>.
     *
     * @param total_num value to be assigned to property total_num
     */
    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }

    /**
     * Getter method for property <tt>wishing</tt>.
     *
     * @return property value of wishing
     */
    public String getWishing() {
        return wishing;
    }

    /**
     * Setter method for property <tt>wishing</tt>.
     *
     * @param wishing value to be assigned to property wishing
     */
    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    /**
     * Getter method for property <tt>client_ip</tt>.
     *
     * @return property value of client_ip
     */
    public String getClient_ip() {
        return client_ip;
    }

    /**
     * Setter method for property <tt>client_ip</tt>.
     *
     * @param client_ip value to be assigned to property client_ip
     */
    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    /**
     * Getter method for property <tt>act_name</tt>.
     *
     * @return property value of act_name
     */
    public String getAct_name() {
        return act_name;
    }

    /**
     * Setter method for property <tt>act_name</tt>.
     *
     * @param act_name value to be assigned to property act_name
     */
    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
