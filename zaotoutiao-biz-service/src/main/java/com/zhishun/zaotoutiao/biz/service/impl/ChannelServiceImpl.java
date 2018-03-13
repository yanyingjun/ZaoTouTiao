/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.BeanMapUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.UserChannels;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;
import com.zhishun.zaotoutiao.dal.mapper.ChannelsMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserChannelsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private UserChannelsMapper userChannelsMapper;

    @Override
    public Page<Channels> listChannelsPage(ChannelsVO channelsVO, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        if(!StringUtils.isEmpty(channelsVO)){
            map.put("name", channelsVO.getName());
            map.put("status", channelsVO.getStatus());
        }
        int total = channelsMapper.countChannels(map);
        if (pageRequest != null) {
            map.put("limit", pageRequest.getPageSize());
            map.put("offset", pageRequest.getOffset());
        }
        List<Channels> list = channelsMapper.listChannelsPage(map);
        return PageBuilder.buildPage(pageRequest, list, total);
    }

    @Override
    public List<Channels> getChannelsList(String name, Integer status, Integer appType, Long parentId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("name",name);
        map.put("status",status);
        map.put("appType",appType);
        map.put("parentId",parentId);
        return channelsMapper.getChannelsList(map);
    }

    @Override
    public int addChannel(ChannelsVO channelsVO) {
        Channels channels = new Channels();
        BeanMapUtil.copy(channelsVO, channels);
        channels.setChannelType(channelsVO.getChannelOrder());
        channels.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        channels.setStatus(0);
        return channelsMapper.insertSelective(channels);
    }

    @Override
    public int updateChannel(ChannelsVO channelsVO) {
        Channels channels = new Channels();
        BeanMapUtil.copy(channelsVO, channels);
        channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        channels.setStatus(channelsVO.getStatus());
        return channelsMapper.updateByPrimaryKeySelective(channels);
    }

    @Override
    public UserChannels getUserChannel(Long userId) {
        return userChannelsMapper.getUserChannels(userId);
    }

    @Override
    public Channels getChannelById(Long id) {
        return channelsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUserChannel(UserChannels userChannels) {
        return userChannelsMapper.updateByPrimaryKeySelective(userChannels);
    }

    @Override
    public int updateChannels(Channels channels) {
        return channelsMapper.updateByPrimaryKeySelective(channels);
    }




    @Override
    public int deleteChannelById(Long id) {
        return channelsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int channelsOrder(Long id, int channelOrderChangeNum) {
        Channels channels = channelsMapper.selectByPrimaryKey(id);
        int oldOrderNum = channels.getChannelOrder();
        int newOrderNum = 0;

        Channels biggerOrderNum = channelsMapper.getBiggerOrder(oldOrderNum);
        if(channelOrderChangeNum == 1){
            //得到调换位置的另一个较小channelOrder的导航
            Channels smallerOrder = channelsMapper.getSmallerOrder(oldOrderNum);
            if(null != smallerOrder){
                newOrderNum = smallerOrder.getChannelOrder();
                channels.setChannelOrder(newOrderNum);
                channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                channelsMapper.updateByPrimaryKeySelective(channels);
                smallerOrder.setChannelOrder(oldOrderNum);
                smallerOrder.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                channelsMapper.updateByPrimaryKeySelective(smallerOrder);
            }
            return 1;
        }else if(channelOrderChangeNum == 2){
            //得到调换位置的另一个较大channelOrder的导航
            Channels biggerOrder = channelsMapper.getBiggerOrder(oldOrderNum);
            if(null != biggerOrder){
                newOrderNum = biggerOrder.getChannelOrder();
                channels.setChannelOrder(newOrderNum);
                channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                channelsMapper.updateByPrimaryKeySelective(channels);
                biggerOrder.setChannelOrder(oldOrderNum);
                biggerOrder.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                channelsMapper.updateByPrimaryKeySelective(biggerOrder);
            }
            return 1;
        }
        return 0;
    }

    /**
     * 新增导航
     * @param channels
     * @return
     */
    @Override
    public int addTheChannel(Channels channels) {
        channels.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        //新增排序数字最大，排在最后面
        channels.setChannelOrder(channelsMapper.getMaxOrder().getChannelOrder()+1);
        return channelsMapper.insertSelective(channels);
    }

    @Override
    public List<ChannelsVO> getTabs(String name, Long parentId, Integer appType) {
        Map map = Maps.newHashMap();
        map.put("name",name);
        map.put("parentId",parentId);
        map.put("appType",appType);
        List<ChannelsVO> channelsVOList = Lists.newArrayList();
        List<Channels> channelsList = channelsMapper.getTabList(map);
        for(Channels channels : channelsList){
            ChannelsVO channelsVO = new ChannelsVO();
            channelsVO.setName(channels.getName());
            channelsVO.setId(channels.getId());
            channelsVO.setUpdateDate(channels.getUpdateDate());
            //设置类别名
            if(channels.getAppType() == 1){
                channelsVO.setTypeName("文章");
            }else if(channels.getAppType() == 0){
                channelsVO.setTypeName("新闻");
            }
            //设置上一级标签名称
            channelsVO.setParentTab(channelsMapper.selectByPrimaryKey(channels.getParentId()).getName());
            //假如该列表是二级标签，那么上一级标签还有导航名
            Long showId = channelsMapper.selectByPrimaryKey(channels.getParentId()).getParentId();
            if(0 != showId){
                //设置导航标签名
                channelsVO.setAncestryTab(channelsMapper.selectByPrimaryKey(showId).getName());
            }
            channelsVOList.add(channelsVO);
        }
        return channelsVOList;
    }


}
