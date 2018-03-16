/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.information360;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: RequestUrlApi, v0.1 2018年03月07日 19:38闫迎军(YanYingJun) Exp $
 */
public class RequestUrlApi {

    /**
     * Token校验请求地址
     */
    public static final String TOKEN_URL = "http://openapi.look.360.cn/v2/access_token";

    /**
     * 获取信息流列表地址
     */
    public static final String INFORMATION_FLOW_LIST_URL = "http://openapi.look.360.cn/v2/list";

    /**
     * 获取频道信息地址
     */
    public static final String CHANNEL_INFORMATION_URL = "http://openapi.look.360.cn/v2/tabs";

    /**
     * 新闻点击打点地址
     */
    public static final String PRESS_CLICK_URL = "http://openapi.look.360.cn/srv/c";

    /**
     * 视频频道列表
     */
    public static final String VIDEO_CHANNEL_LIST_URL = "http://open.k.360kan.com/api/server/video/channel";

    /**
     * 视频列表（综合和频道）
     */
    public static final String VIDEO_LIST_URL = "http://open.k.360kan.com/api/server/video/list";

    /**
     * 视频详情
     */
    public static final String VIDEO_DATIL_URL = "http://open.k.360kan.com/api/server/video/detail";

    /**
     * 视频播放地址
     */
    public static final String VIDEO_PLAY_ADDRESS_URL = "http://open.k.360kan.com/api/server/video/play";

    /**
     * 相关视频
     */
    public static final String RELATED_VIDEO_URL = "http://open.k.360kan.com/api/server/video/relate";


    /**
     * 信息流请求接口URL
     */
    public static final String INFORMATION_FLOW_LIST = "/information/list";

    /**
     * 信息流相关新闻请求接口URL
     */
    public static final String INFORMATION_FLOW_RELEVANT_LIST = "/information/relevant/list";

    /**
     * 获取频道信息接口URL
     */
    public static final String CHANNEL_INFORMATION = "/channel/information";

    /**
     * 新闻点击打点接口URL
     */
    public static final String PRESS_CLICK = "/press/click";

    /**
     * 频道列表
     */
    public static final String CHANNEL_LIST = "/channel/list";

    /**
     * 视频频道列表
     */
    public static final String VIDEO_CHANNEL_LIST = "/video/channel";

    /**
     * 视频列表（综合和频道）
     */
    public static final String VIDEO_LIST = "/video/list";

    /**
     * 视频详情
     */
    public static final String VIDEO_DATIL = "/video/datil";

    /**
     * 视频播放地址
     */
    public static final String VIDEO_PLAY_ADDRESS = "/video/playAddress";

    /**
     * 相关视频
     */
    public static final String RELATED_VIDEO = "/video/related";
}
