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
    public static final String USER_LOGIN_REQ = "/user/login";

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

    /**
     * 阅读获取金币
     */
    public static final String READ_GOLD_GET = "/read/gold";

    /**
     * 用户签到
     */
    public static final String SIGN_IN = "/sign/in";

    /**
     * 获取我的页面广告图片和链接
     */
    public static final String AD_GET = "/ad/get";

    /**
     * 获取职业列表信息
     */
    public static final String JOBS_GET = "/jobs/get";

    /**
     * 常见问题
     */
    public static final String FAQ_GET = "/faq/get";

    /**
     * 怎么获得金币奖励
     */
    public static final String HOW_GET_GOLD = "/how/getGold";

    /**
     * 清空用户历史记录
     */
    public static final String LOOK_RECORD_DEL = "/record/del";

    /**
     * 添加或删除收藏
     */
    public static final String COLLECT_SET = "/collect/set";

    /**
     * 开宝箱
     */
    public static final String OPEN_TREASURE = "/open/treasure";

    /**
     * 周排行和总排行列表
     */
    public static final String GOLD_RANKINGS = "/gold/rankings";

    /**
     * 徒弟进贡排行榜
     */
    public static final String APPRENTICE_PAY_RANKING = "/apprentice/pay/ranking";

    /**
     * 用户消息通知
     */
    public static final String MSG_GET = "/msg/get";

    /**
     * 用户经纬度设置
     */
    public static final String LOCATION_SET = "/location/set";

    /**
     * 提现申请
     */
    public static final String WITHDRAWALS_APPLICATION = "/withdrawals/application";

    /**
     * 用户公告通知
     */
    public static final String NOTICE_GET = "/notice/get";

    /**
     * 建议与反馈常见问题列表
     */
    public static final String FEEDBACK_FAQ_GET = "/feedback/faq/get";

    /**
     * 用户建议与反馈提交
     */
    public static final String FEEDBACK_PUBLISH_ADD = "/feedback/publish/add";

    /**
     * 根据type类型删除用户相关通知信息
     */
    public static final String DELETE_INFORMATION_BY_TYPE = "del/user/information";

    /**
     * 登录随机红包(新人登录红包)
     */
    public static final String REGISTER_MONEY_ADD = "register/money/add";

    /**
     * 根据类型获得数据
     */
    public static final String FAKE_DATE_GET = "fake/date/get";

    /**
     * 用户已经阅读(24小时热文)，更改阅读状态
     */
    public static final String IS_READ = "is/read";

    /**
     * 获取未读消息公告条数
     */
    public static final String UNREAD_MSG_NUM_GET ="unread/msg/num/get";

    /**
     * 获取未读热文条数
     */
    public static final String  UNREAD_HOT_NUM_GET ="unread/hot/num/get";
}