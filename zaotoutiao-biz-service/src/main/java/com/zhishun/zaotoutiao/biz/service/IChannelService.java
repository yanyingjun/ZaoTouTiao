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
    List<Channels> listChannels(ChannelsVO channelsVO);

    /**
     * 获取导航列表
     * @param name
     * @param status
     * @param appType
     * @param parentId
     * @return
     */
    List<Channels> getChannelsList(String name, Integer status, Integer appType, Long parentId);

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

    /**
     * 更新导航
     * @param channels
     * @return
     */
    int updateChannels(Channels channels);

    /**
     * 根据id删除导航
     * @param id
     * @return
     */
    int deleteChannelById(Long id);

    /**
     * 导航排序
     * @param id
     * @param channelOrderChangeNum
     * @return
     */
    int channelsOrder(Long id, int channelOrderChangeNum);

    /**
     * 新增导航
     * @param channels
     * @return
     */
    int addTheChannel(Channels channels);

    /**
     * 添加视频导航
     * @param channels
     * @return
     */
    int addVideoChannel(Channels channels);

    /**
     * 获取新闻或视频分类信息
     * @param channelId
     * @return
     */
    Channels getChannelsByChannelId(String channelId);

    /**
     * 修改用户关注的频道
     * @param userId
     * @param channels
     * @return
     */
    int updateUserChannelByUserId(Long userId, String channels);


    List<ChannelsVO> getTabs(String name, Long parentId, Integer appType);
}
