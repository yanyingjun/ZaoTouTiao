/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.news;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.NewsMsgReq;
import com.zhishun.zaotoutiao.biz.service.ICommentService;
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.Content;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: NewsController, v0.1 2018年02月26日 13:19BugMan Exp $
 * 新闻相关
 */
@RestController
public class NewsController extends BaseController{

    @Autowired
    private INewsService iNewsService;

    @Autowired
    private ICommentService iCommentService;

    /**
     * 获取新闻列表
     * @param pageNo
     * @param pageSize
     * @param channelId
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEWS_GET_REQ , method = RequestMethod.GET)
    public Map<Object,Object> getNews(final int pageNo, final int pageSize, final int channelId){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);

            }

            @Override
            public void handle() throws Exception {
                List<InfosVo> list = iNewsService.getInfosByType("article",channelId ,pageNo,pageSize);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取新闻列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }

    /**
     * 获取新闻分类列表
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEWS_CHANNELS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getNewsChannles(){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<Channels> list = iNewsService.listChannels();
                dataMap.put("result", "success");
                dataMap.put("msg", "获取新闻分类列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }

    /**
     * 根据infoId  获取新闻详情页
     * @param infoId
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEWS_CONTENT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getNewsContent(final String infoId){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Content content = iNewsService.getNewsByInfoId(infoId);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取新闻详情页成功");
                dataMap.put("data", content);
            }
        });
        return dataMap;
    }

    /**
     * 获取最新评论和评论点赞信息
     * @param infoId
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEW_COMMENT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getNewsComment(final String infoId, final int userId, final int pageNo, final int pageSize){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<InfosVo> commentVOList = iCommentService.getNewCommentVO(infoId,userId,pageNo,pageSize);
                for(InfosVo infosVo:commentVOList){
                    int commentsId = infosVo.getCommentsId();
                    Boolean isMyLike = iCommentService.isMyLike(userId,commentsId);
                    infosVo.setMyLike(isMyLike);
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "获取最新评论和评论点赞信息成功");
                dataMap.put("date", commentVOList);
            }
        });
        return dataMap;
    }

    /**
     * 获取热门评论和评论点赞信息
     * @param infoId
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = NewsMsgReq.HOT_COMMENT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getHotComment(final String infoId, final int userId, final int pageNo, final int pageSize){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<InfosVo> commentVOList = iCommentService.getHotCommentVO(infoId,userId,pageNo,pageSize);
                for(InfosVo infosVo:commentVOList){
                    int commentsId = infosVo.getCommentsId();
                    Boolean isMyLike = iCommentService.isMyLike(userId,commentsId);
                    infosVo.setMyLike(isMyLike);
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "获取热门评论和评论点赞信息成功");
                dataMap.put("date", commentVOList);
            }
        });
        return dataMap;
    }
}
