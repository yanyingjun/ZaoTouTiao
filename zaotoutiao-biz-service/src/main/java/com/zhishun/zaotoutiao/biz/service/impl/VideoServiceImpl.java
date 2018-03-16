/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.dal.mapper.ChannelsMapper;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoServiceImpl, v0.1 2018年02月25日 12:25闫迎军(YanYingJun) Exp $
 */

@Service
public class VideoServiceImpl implements IVideoService{

    @Autowired
    private ChannelsMapper channelsMapper;

    @Autowired
    private InfosMapper infosMapper;

    @Override
    public List<Channels> listVideoChannels(Integer status, Integer appType) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("status", status);
        map.put("appType", appType);
        return channelsMapper.listVideoChannels(map);
    }

    /**
     * 根据类型查找视频或新闻
     * @param type
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<InfosVo> getInfosByType(String type, int channelId, int pageNo, int pageSize) {
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        Map map = Maps.newHashMap();
        map.put("type",type);
        map.put("channelId",channelId);
        map.put("endNo",endNo);
        map.put("startNo",startNo);
        List<InfosVo> voList= infosMapper.selectInfosByType(map);
        return voList;
    }

    @Override
    public List<Infos> getRandVideoList(int channelId) {
        return infosMapper.getRandVideoList(channelId);
    }

    @Override
    public Infos getInfosByInfoId(String infoId) {
        Map map = Maps.newHashMap();
        map.put("infoId", infoId);
        return infosMapper.getInfosByMap(map);
    }
}
