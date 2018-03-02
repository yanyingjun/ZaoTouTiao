/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.request;

/**
 * @author BugMan
 * @version $Id: NewsMsgReq, v0.1 2018年02月26日 19:04BugMan Exp $
 */

public class NewsMsgReq {

    /**
     * 获取新闻列表
     */
    public static final String NEWS_GET_REQ = "/news/get";

    /**
     * 获取视频分类列表
     */
    public static final String NEWS_CHANNELS_REQ = "/news/channels";

    /**
     * 根据infoId 获取新闻（参数可以有UserId）
     */
    public static final String NEWS_CONTENT_REQ = "/news/content";

    /**
     * 获取最新评论和评论点赞信息
     */
    public static final String NEW_COMMENT_REQ = "/comment/new";

    /**
     * 获取热门评论和评论点赞信息
     */
    public static final String HOT_COMMENT_REQ = "/comment/hot";

    /**
     * 搜索新闻
     */
    public static final String SEARCH_NEWS = "search/news";

    /**
     * 新闻收藏
     */
    public static final String COLLECT_GET = "collect/get";

}
