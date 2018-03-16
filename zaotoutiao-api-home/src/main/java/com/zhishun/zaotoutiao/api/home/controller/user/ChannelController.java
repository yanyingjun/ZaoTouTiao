/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.UserChannels;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelController, v0.1 2018年02月26日 20:45闫迎军(YanYingJun) Exp $
 */

@RestController
public class ChannelController extends BaseController{

    @Autowired
    private IChannelService channelService;

    /**
     * 获取我关注的频道
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.MY_CHANNELS_GET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> myChannelsGet(final Long userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<Channels> listAll = channelService.listChannels(null);
                UserChannels userChannels = channelService.getUserChannel(userId);
                List<Channels> list = Lists.newArrayList();
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
                dataMap.put("result", "success");
                dataMap.put("msg", "我的频道列表获取成功");
                dataMap.put("data", list);
                dataMap.put("not_concern", listAll);
            }
        });

        return dataMap;

    }

    /**
     * 更改我的关注频道
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.MY_CHANNELS_SET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> myChannelsSet(final Long userId ,final String channels){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(channels, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = channelService.updateUserChannelByUserId(userId, channels);
                if(num > 0){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "我的频道列表修改成功");
                }else{
                    dataMap.put("result", "fail");
                    dataMap.put("msg", "我的频道列表修改失败");
                }
            }
        });

        return dataMap;

    }
}
