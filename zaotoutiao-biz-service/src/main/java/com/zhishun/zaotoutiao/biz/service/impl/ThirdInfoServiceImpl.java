/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.common.util.*;
import com.zhishun.zaotoutiao.core.model.information360.RequestURL.RequestUrlApi;
import com.zhishun.zaotoutiao.core.model.information360.RequestURL.ThirdConstantInfo;
import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ThirdInfoServiceImpl, v0.1 2018年03月07日 20:22闫迎军(YanYingJun) Exp $
 */
@Service
public class ThirdInfoServiceImpl implements IThirdInfoService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdInfoServiceImpl.class);

    @Override
    public ResponseResult getToken() {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //时间戳 10位秒级时间戳
        Long ts = DateUtil.getSenndTimestamp(new Date());
        dataMap.put("ts", ts);
        //随机12位字符
        char[] result = RandomUtil.getSecurityCode(12, RandomUtil.SecurityCodeLevel.Hard, false);
        dataMap.put("rn", String.valueOf(result));
        //应用平台分配的appid
        dataMap.put("ap", ThirdConstantInfo.APPID);
        //应用平台分配的appkey
        dataMap.put("sign", ThirdConstantInfo.APPKEY);
        //应用包名，SDK打包时自动生成
        dataMap.put("pn", ThirdConstantInfo.PN);
        //本次请求的校验码
        dataMap.put("chc", ApiUtil.getChs(String.valueOf(ts), String.valueOf(result), ThirdConstantInfo.APPID, ThirdConstantInfo.APPKEY, ThirdConstantInfo.PN, ThirdConstantInfo.SECRET));
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + ThirdConstantInfo.MY_UID));
        //客户端应用版本
        dataMap.put("version", ThirdConstantInfo.VERSION);

        JSONObject json = ApiUtil.httpGet(RequestUrlApi.TOKEN_URL, dataMap);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setErrmsg(json.getString("errmsg"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "Token校验成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setErrmsg(json.getString("errmsg"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "Token校验失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult listInformationFlow(String sessionId, String accessToken, String c, String device) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + ThirdConstantInfo.MY_UID));
        //获取新闻条数，最大为10
        dataMap.put("n", 10);
        //应用平台分配的appkey
        dataMap.put("sign", ThirdConstantInfo.APPKEY);
        //acceess token
        dataMap.put("access_token", accessToken);
        //垂直频道名称
        dataMap.put("c", c);
        //返回格式
        dataMap.put("f", "json");
        //客户端应用版本
        dataMap.put("version", ThirdConstantInfo.VERSION);
        //设备名称
        dataMap.put("device", device);
        //数据类型控制
        dataMap.put("sv", 1);
        //一次生存周期session内的ID
        dataMap.put("usid", sessionId);

        JSONObject json = ApiUtil.httpGet(RequestUrlApi.INFORMATION_FLOW_LIST_URL, dataMap);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取信息流数据成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取信息流数据失败，错误信息："+ JSON.toJSONString(responseResult));
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult channelInformation(String accessToken, String device) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + ThirdConstantInfo.MY_UID));
        //应用平台分配的appkey
        dataMap.put("sign", ThirdConstantInfo.APPKEY);
        //acceess token
        dataMap.put("access_token", accessToken);
        //客户端应用版本
        dataMap.put("version", ThirdConstantInfo.VERSION);
        //设备名称
        dataMap.put("device", device);

        JSONObject json = ApiUtil.httpGet(RequestUrlApi.CHANNEL_INFORMATION_URL, dataMap);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取频道信息成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取频道信息失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult pressClick(String accessToken, String device, String url, String channel, String a, String c, String source, String t, String sid) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + ThirdConstantInfo.MY_UID));
        //点击URL
        dataMap.put("url", url);
        //应用平台分配的appkey
        dataMap.put("sign", ThirdConstantInfo.APPKEY);
        //客户端应用版本
        dataMap.put("version", ThirdConstantInfo.VERSION);
        //设备名称
        dataMap.put("device", device);
        //频道
        dataMap.put("channel", channel);
        //数据类型
        dataMap.put("a", a);
        //新闻类别
        dataMap.put("c", c);
        //回传字段
        dataMap.put("source", source);
        //新闻点击时间
        dataMap.put("t", t);
        //请求标识
        dataMap.put("sid", sid);
        //场景标识
        dataMap.put("scene", 1);
        dataMap.put("func", "click");
        //新闻数据类型
        dataMap.put("s", "g");
        //操作行为
        dataMap.put("act", "click");
        //展现样式style字段
        dataMap.put("style", 1);

        JSONObject json = ApiUtil.httpGet(RequestUrlApi.PRESS_CLICK_URL, dataMap);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取新闻点击打点成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setSid(json.getString("sid"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取新闻打点失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }
}
