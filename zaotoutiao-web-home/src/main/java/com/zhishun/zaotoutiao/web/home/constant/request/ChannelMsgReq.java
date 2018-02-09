/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2017
 */
package com.zhishun.zaotoutiao.web.home.constant.request;

/**
 * @author
 * 支付渠道配置表 - 请求常量
 *
 */
public class ChannelMsgReq {

    /**
     * 渠道配置表页面管理 - 请求
     */
    public static final String CHANNEL_ALLOCATION_MANAGE_REQ = "/channel/manage.htm";

    /**
     * 渠道配置表查询 - 请求
     */
    public static final String CHANNEL_ALLOCATION_QUERY_PAG_REQ = "/channel/queryPage.json";

    /**
     * 渠道配置表添加 - 请求
     */
    public static final String CHANNEL_ALLOCATION_INSERT_REQ = "/channel/insert.json";

    /**
     * 渠道单个查询
     */
    public static final String CHANNEL_ALLOCATION_SEARCH_REQ = "/channel/getBuyId.json";

    /**
     * 渠道配置表修改 - 请求
     */
    public static final String CHANNEL_ALLOCATION_UPDATE_REQ = "/channel/update.json";

    /**
     * 渠道配置表批量删除 - 请求
     */
    public static final String CHANNEL_ALLOCATION_DELETEBATCH_REQ = "/channel/deleteBatch.json";

    /**
     * 每日额度配置表查询 - 请求
     */
    public static final String DAILY_LIMIT_QUERY_PAG_REQ = "/daily/queryPage.json";

    /**
     * 每日额度配置表添加 - 请求
     */
    public static final String DAILY_LIMIT_INSERT_REQ = "/daily/insert.json";

    /**
     * 每日额度配置表批量删除 - 请求
     */
    public static final String DAILY_LIMIT_DELETEBATCH_REQ = "/daily/deleteBatch.json";

}