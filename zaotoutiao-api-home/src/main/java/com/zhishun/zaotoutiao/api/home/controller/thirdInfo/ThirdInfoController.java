/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.thirdInfo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.information360.RequestURL.RequestUrlApi;
import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ThirdInfoController, v0.1 2018年03月07日 20:19闫迎军(YanYingJun) Exp $
 */
@Controller
public class ThirdInfoController extends BaseController{

    @Autowired
    private IThirdInfoService thirdInfoService;

    /**
     * 获取信息流列表接口
     * @return
     */
    @RequestMapping(value = RequestUrlApi.INFORMATION_FLOW_LIST, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> listInformationFlow(final HttpServletRequest request, final String c, final String device){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(360);
                String sessionId = session.getId();

                ResponseResult responseResult = thirdInfoService.getToken();
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    thirdInfoService.listInformationFlow(sessionId, accessToken, c, device);

                }


            }
        });

        return dataMap;
    }


    /**
     * 获取频道信息接口
     * @return
     */
    @RequestMapping(value = RequestUrlApi.CHANNEL_INFORMATION, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> channelInformation(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.getToken();
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    //thirdInfoService.listInformationFlow(accessToken);

                }


            }
        });

        return dataMap;
    }

    /**
     * 新闻点击打点
     * @return
     */
    @RequestMapping(value = RequestUrlApi.PRESS_CLICK, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> pressClick(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.getToken();
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    //thirdInfoService.listInformationFlow(accessToken);

                }


            }
        });

        return dataMap;
    }



}
