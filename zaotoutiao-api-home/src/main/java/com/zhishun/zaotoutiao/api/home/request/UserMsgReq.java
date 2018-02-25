/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.request;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoMsgReq, v0.1 2018年02月25日 11:57闫迎军(YanYingJun) Exp $
 */
public class UserMsgReq {

    /**
     * 用户登录
     */
    public static final String USER_LOGIN_REQ = "/user/common";

    /**
     * 用户注册
     */
    public static final String USER_REGISTER_REQ = "/user/register";

    /**
     * 查询用户账号是否存在
     */
    public static final String USER_EXIST_ACCOUNT_REQ = "/user/existAccount";

    /**
     * 忘记密码
     */
    public static final String USER_FORGET_PASSWORD_REQ = "/user/forgetPassword";

    /**
     * 退出登录
     */
    public static final String USER_LOGOUT_REQ = "/user/logout";

}