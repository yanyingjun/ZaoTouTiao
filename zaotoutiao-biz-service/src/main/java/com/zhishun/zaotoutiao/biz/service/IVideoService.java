package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.VideoChannels;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IVideoService, v0.1 2018年02月25日 12:15闫迎军(YanYingJun) Exp $
 */
public interface IVideoService {

    /**
     * 获取视频分类列表
     * @return
     */
    List<VideoChannels> listVideoChannels();
}
