/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.information360.RequestURL;

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
     * 信息流请求接口URL
     */
    public static final String INFORMATION_FLOW_LIST = "/information/list";

    /**
     * 获取频道信息接口URL
     */
    public static final String CHANNEL_INFORMATION = "/channel/information";

    /**
     * 新闻点击打点接口URL
     */
    public static final String PRESS_CLICK = "/press/click";
}
