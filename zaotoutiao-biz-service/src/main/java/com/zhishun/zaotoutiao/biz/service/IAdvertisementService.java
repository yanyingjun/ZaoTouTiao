package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.Advertisement;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IAdvertisementService, v0.1 2018年03月01日 9:55闫迎军(YanYingJun) Exp $
 */
public interface IAdvertisementService {

    /**
     * 从广告中随机取一条记录
     * @return
     */
    Advertisement dealWithAds();
}
