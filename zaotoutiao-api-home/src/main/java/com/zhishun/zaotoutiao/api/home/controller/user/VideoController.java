/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.api.home.request.VideoMsgReq;
import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.core.model.entity.VideoChannels;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 获取视频分类列表
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_CHANNELS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getVideoChannles(final ModelMap modelMap, HttpServletRequest request){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(modelMap, request, new ControllerCallback() {
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
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = VideoMsgReq.VIDEO_GET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getVideos(final ModelMap modelMap, HttpServletRequest request){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {

            }
        });

        return dataMap;
    }
}
