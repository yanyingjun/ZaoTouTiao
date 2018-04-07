/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.api.home.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.ShareMsgReq;
import com.zhishun.zaotoutiao.biz.service.IUserShareService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.UserShare;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.IncomeAndImgUrlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author BugMan
 * @version $Id: UserShareController, v0.1 2018年03月01日 16:38BugMan Exp $
 */
@RestController
public class UserShareController extends BaseController {

    @Autowired
    private IUserShareService userShareService;

    /**
     * 用户分享
     * @param userId
     * @param type
     * @param source
     * @param infoId
     * @param apprenticeId
     * @return
     */
    @RequestMapping(value = ShareMsgReq.SHARE_ADD, method = RequestMethod.POST)
    public Map<Object,Object> shareAdd(final long userId, final String type ,final String source, final String infoId, final String apprenticeId){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(type, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(source, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                UserShare userShare = userShareService.getUserShare(userId,type,source,infoId,apprenticeId);
                dataMap.put("result", "success");
                dataMap.put("msg", "用户分享成功");
                dataMap.put("data", userShare);
            }
        });
        return dataMap;
    }

    /**
     * 用户分享成功后调用
     * @param userId
     * @param shareId
     * @return
     */
    @RequestMapping(value = ShareMsgReq.SHARE_SUCCESS_GET, method = RequestMethod.POST)
    public Map<Object,Object> shareSuccessGet(final long userId, final long shareId){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotNull(shareId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotZero(shareId, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Map<String,Object> resMap = userShareService.shareSuccessGet(userId,shareId);
                dataMap.put("result", "success");
                dataMap.put("msg", "用户分享成功");
                dataMap.put("getGold", resMap.get("getGold"));
                dataMap.put("type", resMap.get("type" +
                        "" +
                        ""));
            }
        });
        return dataMap;
    }

    /**
     * 返回某用户赚取零钱总额和相关页面图片URL
     * @param userId
     * @return
     */
    @RequestMapping(value = ShareMsgReq.INCOME_IMG_URL_REQ, method = RequestMethod.POST)
    public Map<Object,Object> shareIncome(final Long userId){
        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                IncomeAndImgUrlVO data = userShareService.getShareIncome(userId);
                dataMap.put("result", "success");
                dataMap.put("msg", "调用成功");
                dataMap.put("data",data);
            }
        });
        return dataMap;
    }
}
