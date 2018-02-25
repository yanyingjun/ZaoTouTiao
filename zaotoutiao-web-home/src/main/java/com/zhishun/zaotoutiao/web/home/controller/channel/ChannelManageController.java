/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.channel;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ChannelMsgReq;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelManageController, v0.1 2018年02月25日 15:31闫迎军(YanYingJun) Exp $
 */

@RestController
public class ChannelManageController extends BaseController{


    private IChannelService channelService;

    /**
     * 获取导航列表
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNLE_MANAGE_REQ, method = RequestMethod.POST)
    public Map<Object,Object> listChannelsPage(final ChannelsVO channelsVO, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                Page<Channels> channelsPage = channelService.listChannelsPage(channelsVO, pageRequest);
                dataMap.put("total", channelsPage.getTotal());
                dataMap.put("rows", channelsPage.getRows());
            }
        });

        return dataMap;
    }
}
