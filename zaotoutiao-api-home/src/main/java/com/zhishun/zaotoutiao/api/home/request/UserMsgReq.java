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

    /**
     * 获取用户信息
     */
    public static final String USER_GET_REQ = "/user/get";

    /**
     * 修改用户信息
     */
    public static final String USER_SET_REQ = "/user/set";

    /**
     * 我的收入
     */
    public static final String MY_INCOME_REQ = "/my/income";

    /**
     * 修改密码
     */
    public static final String PASSWORD_UPDATE_REQ = "/password/update";

    /**
     * 获取我关注的频道
     */
    public static final String MY_CHANNELS_GET_REQ = "/my/channels/get";

    /**
     * 更改我关注的频道
     */
    public static final String MY_CHANNELS_SET_REQ = "/my/channels/set";

    /**
     * 收徒，填写邀请码，绑定师徒关系
     */
    public static final String RECRUIT_SET_REQ = "/recruit/set";

    /**
     * 获取徒弟列表信息
     */
    public static final String APPRENTICE_GET_REQ = "/apprentice/get";

    /**
     * 待唤醒徒弟列表
     */
    public static final String WAKE_UP_APPRENTICE_LIST_GET = "/warkUp/apprentice/list";

    /**
     * 获取用户历史记录
     */
    public static final String LOOK_RECORD_GET = "/look/record/get";

    /**
     * 发送验证码
     */
    public static final String SEND_SMS_CODE = "/send/smsCode";

}