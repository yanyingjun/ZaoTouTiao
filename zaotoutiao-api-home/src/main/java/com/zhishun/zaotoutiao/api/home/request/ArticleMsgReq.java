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
}
