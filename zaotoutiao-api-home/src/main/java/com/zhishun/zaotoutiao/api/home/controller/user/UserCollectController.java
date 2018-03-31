/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IUserCollectService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserChannels;
import com.zhishun.zaotoutiao.core.model.entity.UserCollect;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserCollectController, v0.1 2018年02月28日 17:36闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserCollectController extends BaseController{

    @Autowired
    private IUserCollectService userCollectService;

    @RequestMapping(value = UserMsgReq.COLLECT_SET)
    public Map<Object,Object> myChannelsSet(final Long userId, final String infoId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(infoId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                if(StringUtils.isEmpty(infoId)){
                    //删除全部
                    userCollectService.delAllCollect(userId);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "删除全部收藏成功");
                }else{
                    UserCollect userCollect1 = userCollectService.getCollectByMap(userId, infoId);
                    if(!StringUtils.isEmpty(userCollect1)){
                        //删除单条
                        userCollectService.delOneCollect(userId, infoId);
                        dataMap.put("result", "success");
                        dataMap.put("msg", "删除单条收藏成功");
                    }else{
                        //不存在收藏
                        userCollectService.addUserCollect(userId, infoId);
                        UserCollect userCollect2 = userCollectService.getCollectByMap(userId, infoId);
                        dataMap.put("result", "success");
                        dataMap.put("msg", "添加收藏成功");
                        dataMap.put("data", userCollect2);
                    }
                }
            }
        });

        return dataMap;

    }
}
