package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.UserChannels;
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

    /**
     * 新增导航
     * @param channelsVO
     * @return
     */
    int addChannel(ChannelsVO channelsVO);

    /**
     * 修改导航
     * @param channelsVO
     * @return
     */
    int updateChannel(ChannelsVO channelsVO);

    /**
     * 获取我的频道信息
     * @param userId
     * @return
     */
    UserChannels getUserChannel(Long userId);

    /**
     * 根据ID获取我的频道列表
     * @param id
     * @return
     */
    Channels getChannelById(Long id);

    /**
     * 修改我的频道列表
     * @param userId
     * @return
     */
    int updateUserChannel(UserChannels userChannels);
}
