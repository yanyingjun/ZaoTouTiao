/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.core.model.entity.VideoChannels;
import com.zhishun.zaotoutiao.dal.mapper.VideoChannelsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: VideoServiceImpl, v0.1 2018年02月25日 12:25闫迎军(YanYingJun) Exp $
 */

@Service
public class VideoServiceImpl implements IVideoService{

    @Autowired
    private VideoChannelsMapper videoChannelsMapper;

    @Override
    public List<VideoChannels> listVideoChannels() {
        return videoChannelsMapper.listVideoChannels();
    }
}
