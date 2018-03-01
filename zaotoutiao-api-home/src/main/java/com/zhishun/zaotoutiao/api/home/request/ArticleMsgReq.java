/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.request;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoMsgReq, v0.1 2018年02月25日 11:57闫迎军(YanYingJun) Exp $
 */
public class ArticleMsgReq {

    /**
     * 输入评论
     */
    public static final String ADD_COMMENTS_REQ = "/comments/add";

    /**
     * 获取文章相关内容
     */
    public static final String ARTICLE_RELATED_REQ = "/article/related";

    /**
     * 评论点赞和取消点赞
     */
    public static final String GIVE_LIKE_SET = "/give/like/set";

    /**
     * 文章类新闻详情页
     */
    public static final String VIEW_ARTICLE_HTML = "/view/articleHtml";

    /**
     * 获取文章json
     */
    public static final String GET_ARTICLE_REQ = "/article/json";

    /**
     * 我的评论列表
     */
    public static final String MY_COMMENTS_GET = "/my/comments/get";

    /**
     * 删除我的评论
     */
    public static final String MY_COMMENTS_DEL = "/my/comments/del";

    /**
     * 随机推荐文章
     */
    public static final String RANDOM_ARTICLE = "/random/article";

    /**
     * 获取webview内容
     */
    public static final String STATIC_HTML = "/static/html";

    /**
     * 文章评论人数和是否收藏
     */
    public static final String COLLECT_COMMENTS_GET = "/collect/comments/get";

    /**
     * 获取24小时热文
     */
    public static final String HOT24HOURS_GET = "/hot24hours/get";

    /**
     * 用户讨厌文章设置
     */
    public static final String HATE_NEWS_SET = "/hate/news/set";
}
