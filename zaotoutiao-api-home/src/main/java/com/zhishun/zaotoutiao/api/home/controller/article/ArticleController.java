/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.article;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.ArticleMsgReq;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IArticleService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.entity.UserGiveLike;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ArticleController, v0.1 2018年02月26日 17:41闫迎军(YanYingJun) Exp $
 */

@RestController
public class ArticleController extends BaseController{

    @Autowired
    private IArticleService articleService;


    /**
     * 输入评论
     * @param userComments
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.ADD_COMMENTS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> addComments(final UserComments userComments){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userComments, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                articleService.addComments(userComments);
                dataMap.put("result", "success");
                dataMap.put("msg", "评论成功");
            }
        });

        return dataMap;
    }


    /**
     * 获取文章相关内容
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.ARTICLE_RELATED_REQ, method = RequestMethod.POST)
    public Map<Object,Object> articleRelated(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
            }

            @Override
            public void handle() throws Exception {

                dataMap.put("result", "success");
                dataMap.put("msg", "返回相关信息成功");
            }
        });

        return dataMap;

    }

    /**
     * 评论点赞和取消点赞
     * @param userId
     * @param commentsId 评论id is_like:表示用户当前是否已点赞 \n like_num:表示该评论点赞人数  \n data:false表示评论已不存在
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.GIVE_LIKE_SET)
    public Map<Object,Object> giveLikeSet(final Long userId, final Long commentsId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(commentsId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                dataMap.put("result", "success");
                //判断评论是否存在
                UserComments userComments = articleService.getUserCommonets(commentsId);
                if(!StringUtils.isEmpty(userComments)){
                   //判断是否点过赞
                   UserGiveLike userGiveLike = articleService.getUserGiveLike(null, commentsId);
                   if(!StringUtils.isEmpty(userGiveLike)){
                       //判断是否自己点赞
                       UserGiveLike userGiveLike1 = articleService.getUserGiveLike(userId, commentsId);
                       if(!StringUtils.isEmpty(userGiveLike1)){
                           //判断点赞或取消点赞
                           if(userGiveLike1.getIsLike() == 0){
                               userGiveLike1.setIsLike((byte)1);
                               articleService.upateUserGiveLike(userGiveLike1);
                               //返回点赞总人数
                               int count = articleService.countUserGiveLike(commentsId);
                               //修改点赞人数
                               userComments.setLikes(count);
                               articleService.updateUserComments(userComments);
                               //获取点赞详情
                               UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                               dataMap.put("msg", "已点赞");
                               dataMap.put("isLike", "true");
                               dataMap.put("likeNum", count);
                               dataMap.put("data", commentsInfo);
                           }else {
                               //取消点赞
                               userGiveLike1.setIsLike((byte)0);
                               articleService.upateUserGiveLike(userGiveLike1);
                               //返回点赞总人数
                               int count = articleService.countUserGiveLike(commentsId);
                               //修改点赞人数
                               userComments.setLikes(count);
                               articleService.updateUserComments(userComments);
                               //获取点赞详情
                               UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                               dataMap.put("msg", "已取消点赞");
                               dataMap.put("isLike", "false");
                               dataMap.put("data", commentsInfo);
                           }
                       }else{
                           //不是自己点赞
                           articleService.addUserGiveLike(userId, commentsId);
                           //返回点赞总人数
                           int count = articleService.countUserGiveLike(commentsId);
                           //修改点赞人数
                           userComments.setLikes(count);
                           articleService.updateUserComments(userComments);
                           //获取点赞详情
                           UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                           dataMap.put("msg", "已点赞");
                           dataMap.put("isLike", "true");
                           dataMap.put("likeNum", count);
                           dataMap.put("data", commentsInfo);
                           //是否获得奖励
                           articleService.isCommentsLikeGold(commentsId, count);

                       }
                   }else{
                       //直接点赞，添加点赞
                       articleService.addUserGiveLike(userId, commentsId);
                       //返回点赞总人数
                       int count = articleService.countUserGiveLike(commentsId);
                       //修改点赞人数
                       userComments.setLikes(count);
                       articleService.updateUserComments(userComments);
                       //获取点赞详情
                       UserComments commentsInfo = articleService.getUserCommonetsInfo(commentsId,userId);
                       dataMap.put("msg", "已点赞");
                       dataMap.put("isLike", "true");
                       dataMap.put("likeNum", count);
                       dataMap.put("data", commentsInfo);

                   }
                }else{
                    dataMap.put("msg", "该评论不存在");
                }
            }
        });

        return dataMap;
    }

    /**
     * 文章类新闻详情页
     * @param infoId
     * @param userId
     * @param isShare 本次是否是分享，不传代表不是分享  \n 分享时必须传userId
     * @return
     */
    @RequestMapping(value = ArticleMsgReq.VIEW_ARTICLE_HTML)
    public Map<Object,Object> viewArticleIosHtml(final Long infoId, final Long userId, final String isShare){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(isShare, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

            }
        });

        return dataMap;
    }

}
