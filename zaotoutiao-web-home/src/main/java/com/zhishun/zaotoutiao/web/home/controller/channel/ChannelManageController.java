/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.channel;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ChannelMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ChannelMsgView;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelManageController, v0.1 2018年02月25日 15:31闫迎军(YanYingJun) Exp $
 */

@RestController
public class ChannelManageController extends BaseController{

    @Autowired
    private IChannelService channelService;

    /**
     * 获取导航列表
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNLE_MANAGE_REQ, method = RequestMethod.GET)
    public Map<Object,Object> listChannelsPage(final String name, final Integer status, final Integer page, final Integer limit){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                PageRequest pageRequest = new PageRequest();
                pageRequest.setPageNo(page);
                pageRequest.setPageSize(limit);
                ChannelsVO channelsVO = new ChannelsVO();
                channelsVO.setName(name);
                channelsVO.setStatus(status);
                Page<Channels> channelsPage = channelService.listChannelsPage(channelsVO, pageRequest);
                dataMap.put("data", channelsPage.getRows());
                dataMap.put("count", channelsPage.getTotal());
            }
        });

        return dataMap;
    }


    /**
     * 新增导航
     * @param channelsVO
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_ADD_REQ, method = RequestMethod.POST)
    public Map<Object,Object> addChannel(final ChannelsVO channelsVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(channelsVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = channelService.addChannel(channelsVO);
                if(num > 0){
                    dataMap.put("code", 1);
                    dataMap.put("msg","新增成功");
                }else{
                    dataMap.put("code", 0);
                    dataMap.put("msg","新增失败");
                }
            }
        });

        return dataMap;
    }

    /**
     * 修改导航
     * @param channelsVO
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_UPDATE_REQ, method = RequestMethod.POST)
    public Map<Object,Object> updateChannel(final ChannelsVO channelsVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(channelsVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = channelService.updateChannel(channelsVO);
                if(num > 0){
                    dataMap.put("code", 1);
                    dataMap.put("msg","修改成功");
                }else{
                    dataMap.put("code", 0);
                    dataMap.put("msg","修改失败");
                }
            }
        });

        return dataMap;
    }


    /**
     * 获取一级标签列表
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.FIRST_LEVEL_LABEL, method = RequestMethod.GET)
    public Map<Object,Object> firstLabelPage(final String name, final Integer status, final Integer page, final Integer limit){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                PageRequest pageRequest = new PageRequest();
                pageRequest.setPageNo(page);
                pageRequest.setPageSize(limit);
                ChannelsVO channelsVO = new ChannelsVO();
                channelsVO.setName(name);
                channelsVO.setStatus(status);
                Page<Channels> channelsPage = channelService.listChannelsPage(channelsVO, pageRequest);
                dataMap.put("data", channelsPage.getRows());
                dataMap.put("count", channelsPage.getTotal());
            }
        });

        return dataMap;
    }


}
