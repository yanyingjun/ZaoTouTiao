/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.constant.request;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ZttWebMsgReq, v0.1 2018年02月25日 13:46闫迎军(YanYingJun) Exp $
 */
public class ZttWebMsgReq {

    /**
     * 登录页面请求
     */
    public static final String ZTT_MANAGE_LOGIN_REQ = "/login";

    /**
     * 主页面请求
     */
    public static final String ZTT_MANAGE_INDEX_REQ = "/index";

    /**
     * 工作台
     */
    public static final String ZTT_MANAGE_MAIN_REQ = "/main";

    /**
     * 用户行为统计
     */
    public static final String ZTT_USER_BEHAVIOR_REQ = "/behavior";

    /**
     * 用户行为统计数据请求
     */
    public static final String ZTT_BEHAVIOR_LIST_REQ = "/behavior/list";

    /**
     * 用户存留页面请求
     */
    public static final String ZTT_USER_RETENTION_REQ = "/retention";

    /**
     * 用户存留数据请求
     */
    public static final String ZTT_RETENTION_LIST_REQ = "/retention/list";

    /**
     * 金币统计页面请求
     */
    public static final String ZTT_USER_GOLD_REQ = "/userGold";

    /**
     * 金币统计数据请求
     */
    public static final String ZTT_GOLD_LIST_REQ = "/userGold/list";

    /**
     * 按日按周按月金币统计数据请求
     */
    public static final String ZTT_GOLD_LISTGROUP_REQ = "/userGold/listGroup";

    /**
     * 零钱统计页面请求
     */
    public static final String ZTT_USER_MONEY_REQ = "/userMoney";

    /**
     * 零钱统计数据请求
     */
    public static final String ZTT_MONEY_LIST_REQ = "/userMoney/list";

    /**
     * 按日按周按月零钱统计数据请求
     */
    public static final String ZTT_MONEY_LISTGROUP_REQ = "/userMoney/listGroup";

    /**
     * 用户可提现列表页面请求
     */
    public static final String ZTT_CAN_BE_PRESENTED_USER_REQ = "/bePresentedUser";

    /**
     * 可提现用户列表
     */
    public static final String ZTT_CAN_BE_PRESENTED_USER_LIST_REQ = "/bePresentedUser/list";

    /**
     * 更新用户信息
     */
    public static final String ZTT_UPDATE_USER_REQ = "/updateUser";

    /**
     * 推送列表页面请求
     */
    public static final String ZTT_PUSH_MANAGE_VIEW = "/push/manage";

    /**
     * 新增新闻推送
     */
    public static final String ZTT_ADD_NEWS_VIEW = "/push/addNews";

    /**
     * 新增视频推送
     */
    public static final String ZTT_ADD_VIDEO_VIEW = "/push/addVideo";

    /**
     * 提现申请页面请求
     */
    public static final String ZTT_PRESENT_VIEW = "/present/req";

    /**
     * 金币预警页面请求
     */
    public static final String ZTT_EARLY_WARNING_VIEW = "/gold/earlyWarning";

    /**
     * 提现申请
     */
    public static final String ZTT_PRESENT_REQ = "/present/list";

    /**
     * 更新用户提现状态
     */
    public static final String ZTT_UPDATE_USER_PRESENTSTATUS_REQ = "/updateUser/presentStatus";

    /**
     * 新增新闻页面请求
     */
    public static final String ZTT_CONTENT_ADD_VIEW = "/content";

    /**
     * 内容列表页面请求
     */
    public static final String ZTT_CONTENT_LIST_VIEW = "/content/list/view";

    /**
     * 上传图片
     */
    public static final String ZTT_UPLOAD_LIST_PIC_REQ = "/upload/listPic";

    /**
     * 编辑器上传图片
     */
    public static final String ZTT_UPLOAD_IMAGE_REQ = "/upload/image";

}
