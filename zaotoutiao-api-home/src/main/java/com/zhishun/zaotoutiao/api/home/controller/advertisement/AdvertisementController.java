/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.advertisement;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.AdvertisementMsgReq;
import com.zhishun.zaotoutiao.api.home.request.ArticleMsgReq;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 广告相关
 * @author 闫迎军(YanYingJun)
 * @version $Id: AdvertisementController, v0.1 2018年02月27日 16:26闫迎军(YanYingJun) Exp $
 */
@RestController
public class AdvertisementController extends BaseController{


    /**
     * 获取广告接口
     * @return
     */
    @RequestMapping(value = AdvertisementMsgReq.GET_ADVERTISEMENT_REQ)
    public Map<Object,Object> getAdvertisement(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {

            }
        });

        return dataMap;
    }
}
