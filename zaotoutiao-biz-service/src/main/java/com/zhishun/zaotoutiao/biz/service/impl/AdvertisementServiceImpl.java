/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IAdvertisementService;
import com.zhishun.zaotoutiao.core.model.entity.Advertisement;
import com.zhishun.zaotoutiao.dal.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: AdvertisementServiceImpl, v0.1 2018年03月01日 9:55闫迎军(YanYingJun) Exp $
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService{

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public Advertisement dealWithAds() {
        return advertisementMapper.dealWithAds();
    }
}
