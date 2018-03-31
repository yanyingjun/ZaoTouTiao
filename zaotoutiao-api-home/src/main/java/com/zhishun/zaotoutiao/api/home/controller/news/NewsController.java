/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.news;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.NewsMsgReq;
import com.zhishun.zaotoutiao.biz.service.*;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.UserChannels;
import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import com.zhishun.zaotoutiao.core.model.enums.ChannelEnum;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserCommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    private IVideoService videoService;

    @Autowired
    private ICommentsService iCommentService;

    @Autowired
    private IChannelService channelService;

    @Autowired
    private IUserCollectService userCollectService;

    /**
     * 获取新闻列表
     * @param pageNo
     * @param pageSize
     * @param channelId
     * @return
     */
    /*@RequestMapping(value = NewsMsgReq.NEWS_GET_REQ , method = RequestMethod.GET)
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
                List<InfosVO> list = iNewsService.getInfosByType("article",channelId ,pageNo,pageSize);
                for(InfosVO infosVo:list){
                    String infoid = infosVo.getInfoid();
                    int commentsNum = iCommentService.getCommentsNumByInfoId(infoid);
                    infosVo.setCommentsNum(commentsNum);
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "获取新闻列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }*/

    /**
     * 获取新闻分类列表
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEWS_CHANNELS_REQ, method = RequestMethod.GET)
    public Map<Object,Object> getNewsChannles(final Long userId){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<Channels> list = Lists.newArrayList();
                List<Channels> listAll = Lists.newArrayList();
                if(StringUtils.isEmpty(userId)){
                    list = videoService.listVideoChannels(1, ChannelEnum.NEWS.getValue());
                }else{
                    listAll = videoService.listVideoChannels(1, ChannelEnum.NEWS.getValue());
                    UserChannels userChannels = channelService.getUserChannel(userId);
                    if(StringUtils.isEmpty(userChannels.getChannels())){
                        list = listAll;
                    }else{
                        String[] channels = userChannels.getChannels().split(",");
                        for(String channel : channels){
                            Channels channels1 = channelService.getChannelsByChannelId(channel);
                            list.add(channels1);
                        }
                        for(Channels chan : list){
                            listAll.remove(chan);
                        }
                    }
                }
                dataMap.put("not_concern", listAll);
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
    /*@RequestMapping(value = NewsMsgReq.NEWS_CONTENT_REQ, method = RequestMethod.POST)
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
    }*/

    /**
     * 获取最新评论和评论点赞信息
     * @param infoId
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = NewsMsgReq.NEW_COMMENT_REQ, method = RequestMethod.GET)
    public Map<Object,Object> getNewsComment(final String infoId, final Long userId, final PageRequest pageRequest){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageRequest, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<UserCommentsVO> commentVOList = iCommentService.getNewCommentVO(infoId,userId,pageRequest);
                int total = 0;
                //循环添加isMyLike（自己是否点赞）
                if(!StringUtils.isEmpty(commentVOList)){
                    for(UserCommentsVO userCommentsVO:commentVOList){
                        Long commentsId = userCommentsVO.getId();
                        Boolean isMyLike = iCommentService.isMyLike(userId,commentsId);
                        userCommentsVO.setMyLike(isMyLike);
                    }
                    total = commentVOList.size();
                }
                boolean isCollect = userCollectService.isCollect(userId, infoId);
                dataMap.put("isCollect", isCollect);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取最新评论和评论点赞信息成功");
                dataMap.put("data", commentVOList);
                dataMap.put("total", total);
            }
        });
        return dataMap;
    }

    /**
     * 获取热门评论和评论点赞信息
     * @param infoId
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = NewsMsgReq.HOT_COMMENT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getHotComment(final String infoId, final Long userId, final PageRequest pageRequest){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infoId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageRequest, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<UserCommentsVO> commentVOList = iCommentService.getHotCommentVO(infoId,userId,pageRequest);
                //循环添加commentsId
                for(UserCommentsVO userCommentsVO:commentVOList){
                    Long commentsId = userCommentsVO.getId();
                    Boolean isMyLike = iCommentService.isMyLike(userId,commentsId);
                    userCommentsVO.setMyLike(isMyLike);
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "获取热门评论和评论点赞信息成功");
                dataMap.put("data", commentVOList);
            }
        });

        return dataMap;
    }

    /**
     * 搜索新闻
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    /*@RequestMapping(value = NewsMsgReq.SEARCH_NEWS, method = RequestMethod.POST)
    public Map<Object,Object> searchNews(final String keyword, final int pageNo, final int pageSize){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(keyword, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<InfosVO> infosVoList = iNewsService.searchNewsByKeyword(keyword,pageNo,pageSize);
                //循环添加留言数
                for(InfosVO infosVo:infosVoList){
                    String infoid = infosVo.getInfoid();
                    int commentsNum = iCommentService.getCommentsNumByInfoId(infoid);
                    infosVo.setCommentsNum(commentsNum);
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "搜索新闻成功");
                dataMap.put("date", infosVoList);
            }
        });

        return dataMap;
    }*/

    /**
     * 获取收藏列表
     * @param infosType news：新闻; video:视频
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = NewsMsgReq.COLLECT_GET, method = RequestMethod.GET)
    public Map<Object,Object> collectGet(final String infosType, final int userId, final PageRequest pageRequest){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infosType, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageRequest, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<UserCollect> infosVoList = iNewsService.getCollectList(infosType,userId,pageRequest);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取收藏列表成功");
                dataMap.put("data", infosVoList);
            }
        });

        return dataMap;
    }
}
