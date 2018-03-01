/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.VideoMsgReq;
import com.zhishun.zaotoutiao.biz.service.IAdvertisementService;
import com.zhishun.zaotoutiao.biz.service.ICommentsService;
import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Advertisement;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.entity.UserComments;
import com.zhishun.zaotoutiao.core.model.entity.VideoChannels;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoController, v0.1 2018年02月25日 11:52闫迎军(YanYingJun) Exp $
 */
@RestController
public class VideoController extends BaseController{

    @Autowired
    private IVideoService videoService;

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private IAdvertisementService advertisementService;

    /**
     * 获取视频分类列表
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_CHANNELS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getVideoChannles(){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<VideoChannels> list = videoService.listVideoChannels();
                dataMap.put("result", "success");
                dataMap.put("msg", "获取视频分类列表成功");
                dataMap.put("data", list);
            }
        });
        return dataMap;
    }

    /**
     * 获取视频新闻列表
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_GET_REQ, method = RequestMethod.GET)
    public Map<Object,Object> getVideos(final int pageNo, final int pageSize, final int channelId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(pageNo, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(pageSize, ErrorCodeEnum.PARAMETER_ANOMALY);

            }

            @Override
            public void handle() throws Exception {
                List<InfosVo> list = videoService.getInfosByType("video",channelId ,pageNo,pageSize);
                dataMap.put("result", "success");
                dataMap.put("msg", "获取视频新闻列表成功");
                dataMap.put("data", list);
            }
        });

        return dataMap;
    }

    /**
     * 获取视频相关内容
     * @param channelId
     * @param infoId
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_RALATED, method = RequestMethod.GET)
    public Map<Object,Object> videoRalated(final int channelId, final String infoId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                int commentsNum = 0;
                List<Infos> list = videoService.getRandVideoList(channelId);
                for(int i = 0; i< list.size(); i++){
                    Infos infos = list.get(i);
                    if(infos.getHascontent() == 0){
                        infos.setWebUrl(URLDecoder.decode(infos.getH5url()));
                    }
                    String infoId = infos.getInfoid();
                    UserComments userComments = commentsService.getUserCommentsByInfoId(infoId);
                    if(!StringUtils.isEmpty(userComments)){

                        int count = commentsService.countUserComments(infoId);
                        if(count < 1){
                            commentsNum = 0;
                        }else{
                            commentsNum = count;
                        }
                    }
                    if(i == 2){
                        Advertisement advertisement = advertisementService.dealWithAds();
                        dataMap.put("data", advertisement);
                    }else{
                        dataMap.put("data", commentsNum);
                    }
                }

            }
        });

        return dataMap;

    }


}
