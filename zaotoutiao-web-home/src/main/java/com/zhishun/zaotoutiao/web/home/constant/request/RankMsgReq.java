/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.constant.request;

/**
 * @author BugMan
 * @version $Id: RankMsgReq, v0.1 2018年03月23日 11:33BugMan Exp $
 */

public class RankMsgReq {

    /**
     * 导航排行榜
     */
    public static final String NAV_RANK_REQ = "/nav/rank";

    /**
     * 一级标签排行榜
     */
    public static final String FIRST_TANK_REQ = "/first/rank";

    /**
     * 二级标签排行榜
     */
    public static final String SECOND_TANK_REQ = "/second/rank";

    /**
     * 关键词排行榜
     */
    public static final String KEYWORD_TANK_REQ = "/keyword/rank";

    /**
     * 文章排行榜
     */
    public static final String ARTICLE_TANK_REQ = "/article/rank";

    /**
     * 获得导航排行列表数据
     */
    public static final String NAV_TANK_LIST_REQ = "/nav/rank/list";

    /**
     * 获得导航info排行前30
     */
    public static final String GET_INFO_RANK_LIST_REQ = "/info/rank/list";

    /**
     * 获取新闻详情或视频URL
     */
    public static final String GET_INFO_CONTENT_BY_ID_REQ = "/info/rank/content";
}
