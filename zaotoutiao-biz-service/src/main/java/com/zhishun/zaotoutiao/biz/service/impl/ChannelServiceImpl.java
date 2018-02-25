/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;
import com.zhishun.zaotoutiao.dal.mapper.ChannelsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelServiceImpl, v0.1 2018年02月25日 16:02闫迎军(YanYingJun) Exp $
 */
@Service
public class ChannelServiceImpl implements IChannelService{

    @Autowired
    private ChannelsMapper channelsMapper;

    @Override
    public Page<Channels> listChannelsPage(ChannelsVO channelsVO, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        if(StringUtils.isEmpty(channelsVO)){
            map.put("name", channelsVO.getName());
            map.put("status", channelsVO.getStatus());
        }
        int total = channelsMapper.countChannels(map);
        if (pageRequest != null) {
            map.put("limit", pageRequest.getPageNo());
            map.put("offset", pageRequest.getOffset());
        }
        List<Channels> list = channelsMapper.listChannelsPage(map);
        return PageBuilder.buildPage(pageRequest, list, total);
    }
}
