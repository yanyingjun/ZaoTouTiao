package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IChannelService, v0.1 2018年02月25日 16:00闫迎军(YanYingJun) Exp $
 */
public interface IChannelService {

    /**
     * 获取导航列表
     * @return
     */
    Page<Channels> listChannelsPage(ChannelsVO channelsVO, PageRequest pageRequest);
}
