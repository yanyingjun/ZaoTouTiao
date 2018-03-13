/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.constant.request;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelMsgReq, v0.1 2018年02月25日 15:35闫迎军(YanYingJun) Exp $
 */
public class ChannelMsgReq {

    /**
     * 获取导航列表
     */
    public static final String CHANNEL_MANAGE_REQ = "/channel/list";

    /**
     * 跳转到导航管理页面
     */
    public static final String TO_CHANNEL_MANAGE_REQ = "/channel/manageView";

    /**
     * 新增导航
     */
    public static final String CHANNEL_ADD_REQ = "/channel/add";

    /**
     * 导航修改
     */
    public static final String CHANNEL_UPDATE_REQ = "/channel/update";

    /**
     * 一级标签
     */
    public static final String FIRST_LEVEL_LABEL = "/label/first";

    /**
     * 二级标签
     */
    public static final String TWO_LEVEL_LABEL = "/label/two";

    /**
     * 更新导航状态
     */
    public static final String UPDATE_CHANNEL_STATUS = "/update/channel/status";

    /**
     * 删除导航
     */
    public static final String DELETE_CHANNEL = "/delete/channel";

    /**
     * 导航排序
     */
    public static final String CHANNEL_ORDER = "/do/channel/order";

    /**
     * 更新导航名称
     */
    public static final String UPDATE_CHANNEL_NAME ="/update/channel/name";

    /**
     * 新增导航
     */
    public static final String ADD_CHANNEL_REQ = "/add/channel/do";

    /**
     * 一级标签页面
     */
    public static final String FIRST_TABS_REQ = "/first/tab";

    /**
     * 二级标签页面
     */
    public static final String SECOND_TABS_REQ ="/second/tab";

    /**
     * 获取一级标签列表
     */
    public static final String FIRST_TAB_LIST_REQ = "/first/tab/list";

    /**
     * 获取二级标签列表
     */
    public static final String SECOND_TAB_LIST_REQ = "/second/tab/list";

    /**
     * 更新标签
     */
    public static final String UPDATE_TAB_REQ = "/update/tab";

    /**
     * 新增一级标签
     */
    public static final String ADD_FIRST_TAB_REQ = "/add/first/tab";

    /**
     * 新增二级标签
     */
    public static final String ADD_SECOND_TAB_REQ = "/add/second/tab";
}
