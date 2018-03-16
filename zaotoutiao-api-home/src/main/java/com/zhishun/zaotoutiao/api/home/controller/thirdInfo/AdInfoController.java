/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.thirdInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.information360.RequestAdUrlApi;
import com.zhishun.zaotoutiao.core.model.thirdVo.AdInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: AdInfoController, v0.1 2018年03月13日 15:44闫迎军(YanYingJun) Exp $
 */
@RestController
public class AdInfoController extends BaseController{


    /*@Autowired
    private IThirdInfoService thirdInfoService;

    @RequestMapping(value = RequestAdUrlApi.AD_CONSTANT_REQ)
    public Map<Object,Object> adConstant(final String params){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(params, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                JSONObject jsonObject = JSON.parseObject(params);
                JSONObject json = thirdInfoService.getAdInfo(jsonObject);
                if(StringUtils.isEmpty(json)){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", "广告请求异常");
                }else{
                    JSONObject ads = JSONObject.parseObject(json.getString("ads"));
                    JSONObject nativeMaterial = JSONObject.parseObject(ads.getString("native_material"));
                    int type = nativeMaterial.getInteger("type");
                    int interationType = nativeMaterial.getInteger("interaction_type");
                    AdInfoVO adInfoVO = new AdInfoVO();
                    //单图，开屏
                    if((type == 2 || type == 6) && !StringUtils.isEmpty(nativeMaterial.getString("image_snippet"))){
                        JSONObject imageSnippet = JSONObject.parseObject(nativeMaterial.getString("image_snippet"));
                        adInfoVO.setTitle(imageSnippet.getString("title"));
                        adInfoVO.setUrl(imageSnippet.getString("url"));
                        adInfoVO.setcUrl(imageSnippet.getString("c_url"));
                        adInfoVO.setWidth(imageSnippet.getInteger("width"));
                        adInfoVO.setHeight(imageSnippet.getInteger("height"));
                        adInfoVO.setDesc(imageSnippet.getString("desc"));
                        adInfoVO.setType(type);
                        adInfoVO.setInteractionType(interationType);
                    }
                    //图文，三图，互动
                    if((type == 1 || type == 3 || type == 5) &&!StringUtils.isEmpty(nativeMaterial.getString("text_icon_snippet"))){
                        JSONObject textIconSnippet = JSONObject.parseObject(nativeMaterial.getString("text_icon_snippet"));
                        adInfoVO.setTitle(textIconSnippet.getString("title"));
                        adInfoVO.setDesc(textIconSnippet.getString("desc"));
                        adInfoVO.setcUrl(textIconSnippet.getString("c_url"));
                        adInfoVO.setUrl(textIconSnippet.getString("url"));
                        adInfoVO.setWidth(textIconSnippet.getInteger("width"));
                        adInfoVO.setHeight(textIconSnippet.getInteger("height"));
                        adInfoVO.setType(type);
                        adInfoVO.setInteractionType(interationType);
                    }
                    //视频
                    if(type == 4 && !StringUtils.isEmpty(nativeMaterial.getString("video_snippet"))){
                        JSONObject videoSnippet = JSONObject.parseObject(nativeMaterial.getString("video_snippet"));
                        adInfoVO.setUrl(videoSnippet.getString("url"));
                        adInfoVO.setcUrl(videoSnippet.getString("c_url"));
                        adInfoVO.setWidth(videoSnippet.getInteger("width"));
                        adInfoVO.setHeight(videoSnippet.getInteger("height"));
                        adInfoVO.setDuration(videoSnippet.getInteger("duration"));
                        adInfoVO.setType(type);
                        adInfoVO.setInteractionType(interationType);
                    }
                    //判断类型为下载
                    if(interationType == 2 && !StringUtils.isEmpty(nativeMaterial.getString("ext"))){
                        JSONObject ext = JSONObject.parseObject(nativeMaterial.getString("ext"));
                        adInfoVO.setAppname(ext.getString("appname"));
                        adInfoVO.setAppmd5(ext.getString("appmd5"));
                        adInfoVO.setApppackage(ext.getString("apppackage"));
                    }

                    dataMap.put("result", "success");
                    dataMap.put("msg", "广告请求成功");
                    dataMap.put("data", adInfoVO);
                }
            }
        });

        return dataMap;
    }*/
}
