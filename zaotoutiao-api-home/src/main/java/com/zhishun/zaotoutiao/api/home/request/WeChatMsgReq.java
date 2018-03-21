/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.request;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: WeChatMsgReq, v0.1 2018年02月28日 19:56闫迎军(YanYingJun) Exp $
 */
public class WeChatMsgReq {

    /**
     * 兑换微信红包
     */
    public static final String RED_ENVELOPE_GET = "/red/envelope/get";

    /**
     * 用户登录(第三方账号登录)(微信登录)
     */
    public static final String LOGIN_WECHAT = "/login/wechat";

    /**
     * 绑定手机号
     */
    public static final String USER_BINDING_TELEPHONE = "/binding/telephone";

    /**
     * 绑定微信
     */
    public static final String BINDING_WECHAT = "/binding/wechat";

    /**
     * 提现申请
     */
    public static final String ADD_WITHDRAWWALS = "/withdrawwals/add";
}
