/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.common.util.*;
import com.zhishun.zaotoutiao.core.model.information360.AdConstantInfo;
import com.zhishun.zaotoutiao.core.model.information360.RequestUrlApi;
import com.zhishun.zaotoutiao.core.model.information360.ThirdConstantInfo;
import com.zhishun.zaotoutiao.core.model.thirdVo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
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
    public ResponseResult getToken(String userId) {
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
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + userId));
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
    public ResponseResult listInformationFlow(String sessionId, String accessToken, String c, String device, String userId, Integer n) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + userId));
        //获取新闻条数，最大为10
        dataMap.put("n", n);
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
    public ResponseResult channelInformation(String accessToken, String device, String userId) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + userId));
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
    public String pressClick (String accessToken, String device, String channel, String a, String c, String source, String t, String sid, String s, String style, String userId) throws Exception {

        Map<String,Object> dataMap = Maps.newHashMap();
        //用户id
        dataMap.put("u", Md5Util.md5Encode(ThirdConstantInfo.APPKEY + ":" + userId));
        //点击URL
        //dataMap.put("url", URLEncoder.encode(url, "utf-8"));
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
        dataMap.put("source", URLEncoder.encode(source, "utf-8"));
        //新闻点击时间
        dataMap.put("t", t);
        //请求标识
        dataMap.put("sid", sid);
        //场景标识
        dataMap.put("scene", 1);
        dataMap.put("func", "click");
        //新闻数据类型
        dataMap.put("s", s);
        //操作行为
        dataMap.put("act", "click");
        //展现样式style字段
        dataMap.put("style", style);

        return ApiUtil.doGetParam(RequestUrlApi.PRESS_CLICK_URL, dataMap);
    }

    @Override
    public ResponseResult channelList(String osType, String userId, String ip) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,String> dataMap = Maps.newHashMap();
        //渠道唯一标识
        dataMap.put("appid", ThirdConstantInfo.SH_APPID);
        //发送请求的时间戳
        dataMap.put("time", String.valueOf((int)(System.currentTimeMillis() / 1000)));
        //随机数
        dataMap.put("rand_num", String.valueOf(ApiUtil.randomInt(1, 1000)));
        //用户唯一标识32位的字符串
        dataMap.put("m2", ApiUtil.MD5(userId));
        //客户端类型（android,ios）
        dataMap.put("os_type", osType);
        //客户端ip推荐法会地域
        dataMap.put("client_ip", ip);
        //签名参数
        String authCode = ApiUtil.makeSign(dataMap, ThirdConstantInfo.SECRET_KEY);
        dataMap.put("auth_code", authCode);
        String url = RequestUrlApi.VIDEO_CHANNEL_LIST_URL + "?" + ApiUtil.buildQueryString(dataMap);
        String resp = ApiUtil.httpRequest(url);
        JSONObject json = JSONObject.parseObject(resp);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取视频渠道列表成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取视频渠道列表失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult ListVideos(String osType, String userId, String channelId, String ip, String backdata, String pageSize) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,String> dataMap = Maps.newHashMap();
        //渠道唯一标识
        dataMap.put("appid", ThirdConstantInfo.SH_APPID);
        //发送请求的时间戳
        dataMap.put("time", String.valueOf((int)(System.currentTimeMillis() / 1000)));
        //随机数
        dataMap.put("rand_num", String.valueOf(ApiUtil.randomInt(1, 1000)));
        //用户唯一标识32位的字符串
        dataMap.put("m2", ApiUtil.MD5(userId));
        //客户端类型（android,ios）
        dataMap.put("os_type", osType);
        //客户端ip推荐法会地域
        dataMap.put("client_ip", ip);
        //渠道标识
        dataMap.put("channel_id", channelId);
        //回传参数，首次为1，第二次以后为响应中的backddata
        dataMap.put("backdata", backdata);
        //显示条数
        dataMap.put("pageSize", pageSize);
        //签名参数
        String authCode = ApiUtil.makeSign(dataMap, ThirdConstantInfo.SECRET_KEY);
        dataMap.put("auth_code", authCode);
        String url = RequestUrlApi.VIDEO_LIST_URL + "?" + ApiUtil.buildQueryString(dataMap);
        String resp = ApiUtil.httpRequest(url);
        JSONObject json = JSONObject.parseObject(resp);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取视频列表成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取视频列表失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult getVideoDetail(String osType, String userId, String id, String extdata, String ip) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,String> dataMap = Maps.newHashMap();
        //渠道唯一标识
        dataMap.put("appid", ThirdConstantInfo.SH_APPID);
        //发送请求的时间戳
        dataMap.put("time", String.valueOf((int)(System.currentTimeMillis() / 1000)));
        //随机数
        dataMap.put("rand_num", String.valueOf(ApiUtil.randomInt(1, 1000)));
        //用户唯一标识32位的字符串
        dataMap.put("m2", ApiUtil.MD5(userId));
        //客户端类型（android,ios）
        dataMap.put("os_type", osType);
        //客户端ip推荐法会地域
        dataMap.put("client_ip", ip);
        //视频标识
        dataMap.put("id", id);
        //透传视频信息对应扩展字段
        dataMap.put("extdata", extdata);
        //签名参数
        String authCode = ApiUtil.makeSign(dataMap, ThirdConstantInfo.SECRET_KEY);
        dataMap.put("auth_code", authCode);
        String url = RequestUrlApi.VIDEO_DATIL_URL + "?" + ApiUtil.buildQueryString(dataMap);
        String resp = ApiUtil.httpRequest(url);
        JSONObject json = JSONObject.parseObject(resp);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取视频详情成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取视频详情失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult getVideoPlayAddress(String osType, String userId, String id, String extdata, String ip) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,String> dataMap = Maps.newHashMap();
        //渠道唯一标识
        dataMap.put("appid", ThirdConstantInfo.SH_APPID);
        //发送请求的时间戳
        dataMap.put("time", String.valueOf((int)(System.currentTimeMillis() / 1000)));
        //随机数
        dataMap.put("rand_num", String.valueOf(ApiUtil.randomInt(1, 1000)));
        //用户唯一标识32位的字符串
        dataMap.put("m2", ApiUtil.MD5(userId));
        //客户端类型（android,ios）
        dataMap.put("os_type", osType);
        //客户端ip推荐法会地域
        dataMap.put("client_ip", ip);
        //视频标识
        dataMap.put("id", id);
        //透传视频信息对应扩展字段
        dataMap.put("extdata", extdata);
        //签名参数
        String authCode = ApiUtil.makeSign(dataMap, ThirdConstantInfo.SECRET_KEY);
        dataMap.put("auth_code", authCode);
        String url = RequestUrlApi.VIDEO_PLAY_ADDRESS_URL + "?" + ApiUtil.buildQueryString(dataMap);
        String resp = ApiUtil.httpRequest(url);
        JSONObject json = JSONObject.parseObject(resp);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取视频播放地址成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取视频播放地址失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult relatedVideo(String osType, String userId, String id, String extdata, String ip, String page, String pageSize) {
        ResponseResult responseResult = new ResponseResult();

        Map<String,String> dataMap = Maps.newHashMap();
        //渠道唯一标识
        dataMap.put("appid", ThirdConstantInfo.SH_APPID);
        //发送请求的时间戳
        dataMap.put("time", String.valueOf((int)(System.currentTimeMillis() / 1000)));
        //随机数
        dataMap.put("rand_num", String.valueOf(ApiUtil.randomInt(1, 1000)));
        //用户唯一标识32位的字符串
        dataMap.put("m2", ApiUtil.MD5(userId));
        //客户端类型（android,ios）
        dataMap.put("os_type", osType);
        //客户端ip推荐法会地域
        dataMap.put("client_ip", ip);
        //视频标识
        dataMap.put("id", id);
        //页码从0开始
        dataMap.put("pageNo", page);
        //透传视频对应扩展字段
        dataMap.put("pageSize", pageSize);
        //透传视频信息对应扩展字段
        dataMap.put("extdata", extdata);
        //签名参数
        String authCode = ApiUtil.makeSign(dataMap, ThirdConstantInfo.SECRET_KEY);
        dataMap.put("auth_code", authCode);
        String url = RequestUrlApi.RELATED_VIDEO_URL + "?" + ApiUtil.buildQueryString(dataMap);
        String resp = ApiUtil.httpRequest(url);
        JSONObject json = JSONObject.parseObject(resp);
        if(!StringUtils.isEmpty(json)){
            //Map<String,Object> map = JSONObject.toJavaObject(json, Map.class);
            if(Integer.valueOf(json.getString("errno")) == 0){
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.info(LOGGER, "获取相关视频成功，信息："+ JSON.toJSONString(responseResult));
            }else{
                responseResult.setErrno(Integer.parseInt(json.getString("errno")));
                responseResult.setVersion(json.getString("version"));
                responseResult.setData(json.getString("data").toString());
                LoggerUtils.error(LOGGER, "获取相关视频失败，错误信息："+ JSON.toJSONString(responseResult));
                return null;
            }
        }
        return responseResult;
    }

    /*@Override
    public JSONObject getAdInfo(JSONObject json){

        App app = new App();
        app.setPackage_name(json.getString("packageName"));
        app.setApp_version(json.getString("appVersion"));

        Site site = new Site();
        site.setDomain(json.getString("domain"));
        site.setUrls(json.getString("urls"));

        Browser browser = new Browser();
        browser.setUser_agent(json.getString("userAgent"));

        Media media = new Media();
        media.setType(1);
        media.setApp(app);
        media.setSite(site);
        media.setBrowser(browser);

        Device device = new Device();
        device.setId_idfa(json.getString("idIdfa"));
        device.setId_imei(json.getString("idImei"));
        device.setHeight(json.getInteger("height"));
        device.setWidth(json.getInteger("width"));
        device.setBrand(json.getString("brand"));
        device.setModel(json.getString("model"));
        device.setOs_version(json.getString("osVersion"));
        device.setOs_type(json.getInteger("osType"));

        Network network = new Network();
        network.setIp(json.getString("ip"));
        network.setType(json.getInteger("type"));

        Client client = new Client();
        client.setType(3);
        client.setVersion(AdConstantInfo.VERSION);

        Adslot adslot = new Adslot();
        adslot.setId(AdConstantInfo.TEST_AD_POSITION);
        adslot.setType(2);
        adslot.setHeight(200);
        adslot.setWidth(100);

        Map<String,Object> map = Maps.newHashMap();
        map.put("media", media);
        map.put("device", device);
        map.put("network", network);
        map.put("client", client);
        map.put("adslot", adslot);

        JSONObject jsonObject = ApiUtil.httpPost(AdConstantInfo.TEST_REQUEST_URL, map);
        return jsonObject;
    }*/


    @Override
    public AdInfoVO getAdInfo(InformationVO informationVO, String id){

        App app = new App();
        app.setPackage_name(informationVO.getPackageName());
        app.setApp_version(informationVO.getAppVersion());

        Site site = new Site();
        site.setDomain(informationVO.getDomain());
        site.setUrls(informationVO.getUrls());

        Browser browser = new Browser();
        browser.setUser_agent(informationVO.getUserAgent());

        Media media = new Media();
        media.setType(1);
        media.setApp(app);
        media.setSite(site);
        media.setBrowser(browser);

        Device device = new Device();
        device.setId_idfa(informationVO.getIdIdfa());
        device.setId_imei(informationVO.getIdImei());
        device.setHeight(informationVO.getHeight());
        device.setWidth(informationVO.getWidth());
        device.setBrand(informationVO.getBrand());
        device.setModel(informationVO.getModel());
        device.setOs_version(informationVO.getOsVersion());
        device.setOs_type(informationVO.getOsType());

        Network network = new Network();
        network.setIp(informationVO.getIp());
        network.setType(informationVO.getType());

        Client client = new Client();
        client.setType(3);
        client.setVersion(AdConstantInfo.VERSION);

        Adslot adslot = new Adslot();
        adslot.setId(id);
        adslot.setType(1);
        adslot.setHeight(200);
        adslot.setWidth(100);

        Map<String,Object> map = Maps.newHashMap();
        map.put("media", media);
        map.put("device", device);
        map.put("network", network);
        map.put("client", client);
        map.put("adslot", adslot);

        JSONObject jsonss = ApiUtil.httpPost(AdConstantInfo.AD_REQUEST_URL, map);

        JSONObject ads = JSONObject.parseObject(jsonss.getString("ads"));
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
            if(!StringUtils.isEmpty(imageSnippet.getString("clk"))){
                JSONArray jsonArray = JSONObject.parseArray(imageSnippet.getString("clk"));
                String clk = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    clk += jsonArray.get(i) + ",";
                }
                adInfoVO.setClk(clk);
            }
            if(!StringUtils.isEmpty(imageSnippet.getString("imp"))){
                JSONArray jsonArray = JSONObject.parseArray(imageSnippet.getString("imp"));
                String imp = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    imp += jsonArray.get(i) + ",";
                }
                adInfoVO.setImp(imp);
            }
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
            if(!StringUtils.isEmpty(textIconSnippet.getString("clk"))){
                JSONArray jsonArray = JSONObject.parseArray(textIconSnippet.getString("clk"));
                String clk = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    clk += jsonArray.get(i) + ",";
                }
                adInfoVO.setClk(clk);
            }
            if(!StringUtils.isEmpty(textIconSnippet.getString("imp"))){
                JSONArray jsonArray = JSONObject.parseArray(textIconSnippet.getString("imp"));
                String imp = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    imp += jsonArray.get(i) + ",";
                }
                adInfoVO.setImp(imp);
            }
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
            if(!StringUtils.isEmpty(videoSnippet.getString("clk"))){
                JSONArray jsonArray = JSONObject.parseArray(videoSnippet.getString("clk"));
                String clk = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    clk += jsonArray.get(i) + ",";
                }
                adInfoVO.setClk(clk);
            }
            if(!StringUtils.isEmpty(videoSnippet.getString("video_imp"))){
                JSONObject videoImp = JSONObject.parseObject(videoSnippet.getString("video_imp"));
                JSONArray jsonArray = JSONObject.parseArray(videoImp.getString("imp_url"));
                String imp = "";
                for(int i=0 ;i<jsonArray.size();i++){
                    imp += jsonArray.get(i) + ",";
                }
                adInfoVO.setImp(imp);
                adInfoVO.setTime(videoImp.getString("time"));
            }
            adInfoVO.setInteractionType(interationType);
        }
        //判断类型为下载
        if(interationType == 2 && !StringUtils.isEmpty(nativeMaterial.getString("ext"))){
            JSONObject ext = JSONObject.parseObject(nativeMaterial.getString("ext"));
            adInfoVO.setAppname(ext.getString("appname"));
            adInfoVO.setAppmd5(ext.getString("appmd5"));
            adInfoVO.setApppackage(ext.getString("apppackage"));
        }

        return adInfoVO;
    }

}
