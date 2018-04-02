/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.thirdInfo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.biz.service.IThirdInfoService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.enums.ChannelEnum;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.information360.AdConstantInfo;
import com.zhishun.zaotoutiao.core.model.information360.RequestUrlApi;
import com.zhishun.zaotoutiao.core.model.thirdVo.AdInfoVO;
import com.zhishun.zaotoutiao.core.model.thirdVo.InformationVO;
import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;
import com.zhishun.zaotoutiao.core.model.vo.ThirdNewsVO;
import com.zhishun.zaotoutiao.core.model.vo.ThirdVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ThirdInfoController, v0.1 2018年03月07日 20:19闫迎军(YanYingJun) Exp $
 */
@Controller
public class ThirdInfoController extends BaseController{

    @Autowired
    private IThirdInfoService thirdInfoService;

    @Autowired
    private IChannelService channelService;

    /**
     * 获取信息流列表接口
     * @return
     */
    /*@RequestMapping(value = RequestUrlApi.INFORMATION_FLOW_LIST, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> listInformationFlow(final HttpServletRequest request, final String c, final String device, final String userId){

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

                ResponseResult responseResult = thirdInfoService.getToken(userId);
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    ResponseResult res = thirdInfoService.listInformationFlow(sessionId, accessToken, c, device, userId);
                    JSONObject jsonObject = JSONObject.parseObject(res.getData());
                    JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("res"));
                    List<ThirdNewsVO> list = Lists.newArrayList();
                    for(int i = 0;i<jsonArray.size();i++){
                        ThirdNewsVO thirdNewsVO = new ThirdNewsVO();
                        JSONObject job = jsonArray.getJSONObject(i);
                        thirdNewsVO.setTitle(job.getString("t"));
                        thirdNewsVO.setAuthor(job.getString("f"));
                        Date date = new Date(Long.valueOf(job.getString("p"))*1000L);
                        thirdNewsVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                        thirdNewsVO.setNewsType(job.getString("c"));
                        thirdNewsVO.setFilter(job.getString("filter"));
                        thirdNewsVO.setSource(job.getString("source"));
                        thirdNewsVO.setUrl(job.getString("u"));
                        thirdNewsVO.setRawurl(job.getString("rawurl"));
                        thirdNewsVO.setSid(res.getSid());
                        thirdNewsVO.setChannel(c);
                        thirdNewsVO.setA(job.getString("a"));
                        thirdNewsVO.setS(job.getString("s"));
                        thirdNewsVO.setPicUrl(job.getString("i"));
                        thirdNewsVO.setRptId(job.getString("r"));
                        if(!StringUtils.isEmpty(job.getString("style"))){
                            thirdNewsVO.setStyle(job.getString("style"));
                        }else{
                            thirdNewsVO.setStyle("1");
                        }
                        list.add(thirdNewsVO);
                    }


                    dataMap.put("data", list);
                }
            }
        });

        return dataMap;
    }*/

    /**
     * 获取频道信息接口
     * @return
     */
    @RequestMapping(value = RequestUrlApi.CHANNEL_INFORMATION, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> channelInformation(final String device, final String userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                JSONArray jsonArray = null;
                ResponseResult responseResult = thirdInfoService.getToken(userId);
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    ResponseResult res = thirdInfoService.channelInformation(accessToken, device, userId);
                    jsonArray = JSONArray.parseArray(res.getData());
                    for(int i = 0;i<jsonArray.size();i++){
                        JSONObject job = jsonArray.getJSONObject(i);
                        if(job.getString("status").equals("show")){
                            Channels channels = channelService.getChannelsByChannelId(job.getString("c"));
                            if(StringUtils.isEmpty(channels)){
                                channels = new Channels();
                                channels.setName(job.getString("name"));
                                channels.setChannelId(job.getString("c"));
                                channels.setStatus(1);
                                channels.setAppType(ChannelEnum.NEWS.getValue());
                                channels.setParentId("0");
                                channels.setChannelOrder(i+1);
                                channels.setChannelType(i+1);
                                channels.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                                channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                                channelService.addVideoChannel(channels);
                            }
                        }
                    }
                }

                dataMap.put("data", jsonArray);
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
    public Map<Object,Object> pressClick(final String device, final String url, final String channel,
                                         final String a, final String newsType, final String source, final String t, final String sid, final String s, final String style, final String userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.getToken(userId);
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    String urlPrefix = thirdInfoService.pressClick(accessToken, device, channel, a, newsType, source, t, sid, s, style, userId);
                    dataMap.put("state", "success");
                    dataMap.put("msg", "新闻点击单点请求成功");
                    dataMap.put("urlPrefix", urlPrefix);
                    dataMap.put("url", url);
                }
            }
        });

        return dataMap;
    }


    /**
     * 新闻点击打点
     * @return
     *//*
    @RequestMapping(value = RequestUrlApi.CHANNEL_LIST, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> channelList(){

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
    }*/


    /**
     * 视频频道列表
     * @param osType  客户端类型（android,ios）
     * @return
     */
    @RequestMapping(value = RequestUrlApi.VIDEO_CHANNEL_LIST)
    @ResponseBody
    public Map<Object,Object> videoChannelList(final String osType, final String userId, final String ip){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(osType, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.channelList(osType, userId, ip);
                JSONArray jsonArray = JSONArray.parseArray(responseResult.getData());
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    for(int i = 0;i<jsonArray.size();i++){
                        JSONObject job = jsonArray.getJSONObject(i);
                        Channels channels = channelService.getChannelsByChannelId(job.getString("id"));
                        if(StringUtils.isEmpty(channels)){
                            channels = new Channels();
                            channels.setName(job.getString("name"));
                            channels.setChannelId(job.getString("id"));
                            channels.setStatus(1);
                            channels.setAppType(ChannelEnum.VIDEO.getValue());
                            channels.setParentId("0");
                            channels.setChannelOrder(i+1);
                            channels.setChannelType(i+1);
                            channels.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                            channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                            channelService.addVideoChannel(channels);
                        }
                    }
                    dataMap.put("result", "success");
                    dataMap.put("data", jsonArray);
                }
            }
        });

        return dataMap;
    }
    /**
     * 观看人数
     */
    private String playCnt;

    /**
     * 播放时长
     */
    private String duration;

    /**
     * 作者头像
     */
    private String avatar;

    /**
     * 获取视频列表（综合和频道）
     * @return
     */
    @RequestMapping(value = RequestUrlApi.VIDEO_LIST)
    @ResponseBody
    public Map<Object,Object> ListVideos(final InformationVO informationVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(informationVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.ListVideos(informationVO.getVideoOsType(), informationVO.getUserId(), informationVO.getChannelId(), informationVO.getIp(), informationVO.getBackdata(), "8");
                JSONObject jsonObject = JSONObject.parseObject(responseResult.getData());

                JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("list"));
                List<ThirdVideoVO> thirdVideoVOS = Lists.newArrayList();
                for(int i = 0;i < jsonArray.size();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    ThirdVideoVO thirdVideoVO = new ThirdVideoVO();
                    thirdVideoVO.setTitle(json.getString("title"));
                    thirdVideoVO.setUrl(json.getString("cover"));
                    JSONObject jsonObject1 = JSONObject.parseObject(json.getString("author"));
                    thirdVideoVO.setAuthor(jsonObject1.getString("name"));
                    thirdVideoVO.setAvatar(jsonObject1.getString("avatar"));
                    Date date = new Date(Long.valueOf(json.getString("publicTime"))*1000L);
                    thirdVideoVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                    thirdVideoVO.setId(json.getString("id"));
                    thirdVideoVO.setExtdata(json.getString("extData"));
                    thirdVideoVO.setBackdata(jsonObject.getString("backdata"));
                    thirdVideoVO.setDuration(jsonObject.getString("duration"));
                    thirdVideoVO.setPlayCnt(jsonObject.getString("playCnt"));
                    thirdVideoVOS.add(thirdVideoVO);
                }
                List<Map<String,Object>> listVideos = Lists.newArrayList();
                for(ThirdVideoVO thirdVideoVO : thirdVideoVOS){

                    Map<String,Object> map = Maps.newHashMap();
                    map.put("type", ChannelEnum.VIDEO.getValue());
                    map.put("obj", thirdVideoVO);
                    listVideos.add(map);
                }

                AdInfoVO adInfoVO2 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map2 = Maps.newHashMap();
                map2.put("type", ChannelEnum.AD.getValue());
                map2.put("obj", adInfoVO2);
                listVideos.add(5, map2);

                AdInfoVO adInfoVO3 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map3 = Maps.newHashMap();
                map3.put("type", ChannelEnum.AD.getValue());
                map3.put("obj", adInfoVO3);
                listVideos.add(map3);

                dataMap.put("data", listVideos);
            }
        });

        return dataMap;
    }

    /**
     * 视频详情
     * @param osType
     * @param userId
     * @param id
     * @param extdata
     * @return
     */
    @RequestMapping(value = RequestUrlApi.VIDEO_DATIL)
    @ResponseBody
    public Map<Object,Object> getVideoDetail(final String osType, final String userId, final String id, final String extdata, final String ip){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(osType, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(id, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(extdata, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //获取视频详情
                ResponseResult responseResult = thirdInfoService.getVideoDetail(osType, userId, id, extdata, ip);
                //获取视频播放地址
                ResponseResult responseResult1 = thirdInfoService.getVideoPlayAddress(osType, userId, id, extdata, ip);
                JSONObject jsonObject = JSONObject.parseObject(responseResult1.getData());
                JSONObject json = JSONObject.parseObject(responseResult.getData());
                ThirdVideoVO thirdVideoVO = new ThirdVideoVO();
                thirdVideoVO.setTitle(json.getString("title"));
                thirdVideoVO.setUrl(json.getString("cover"));
                JSONObject jsonObject1 = JSONObject.parseObject(json.getString("author"));
                thirdVideoVO.setAuthor(jsonObject1.getString("name"));
                Date date = new Date(Long.valueOf(json.getString("publicTime"))*1000L);
                thirdVideoVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                thirdVideoVO.setId(json.getString("id"));
                thirdVideoVO.setExtdata(json.getString("extData"));
                thirdVideoVO.setCdnUrl(jsonObject.getString("cdn_url"));
                dataMap.put("data", thirdVideoVO);

            }
        });

        return dataMap;
    }

    /**
     * 获取视频播放地址
     * @param osType
     * @param userId
     * @param id
     * @param extdata
     * @return
     */
    @RequestMapping(value = RequestUrlApi.VIDEO_PLAY_ADDRESS)
    @ResponseBody
    public Map<Object,Object> getVideoPlayAddress(final String osType, final String userId, final String id, final String extdata, final String ip){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(osType, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(id, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(extdata, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.getVideoPlayAddress(osType, userId, id, extdata, ip);
                JSONObject json = JSONObject.parseObject(responseResult.getData());
                /*ThirdVideoVO thirdVideoVO = new ThirdVideoVO();
                thirdVideoVO.setTitle(json.getString("title"));
                thirdVideoVO.setUrl(json.getString("cover"));
                JSONObject jsonObject1 = JSONObject.parseObject(json.getString("author"));
                thirdVideoVO.setAuthor(jsonObject1.getString("name"));
                Date date = new Date(Long.valueOf(json.getString("publicTime"))*1000L);
                thirdVideoVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                thirdVideoVO.setId(json.getString("id"));
                thirdVideoVO.setExtdata(json.getString("extData"));*/
                dataMap.put("data", json.getString("cdn_url"));
            }
        });

        return dataMap;
    }

    /**
     * 获取相关视频
     * @param informationVO
     * @return
     */
    @RequestMapping(value = RequestUrlApi.RELATED_VIDEO)
    @ResponseBody
    public Map<Object,Object> relatedVideo(final InformationVO informationVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(informationVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                ResponseResult responseResult = thirdInfoService.relatedVideo(informationVO.getVideoOsType(), informationVO.getUserId(), informationVO.getId(), informationVO.getExtdata(), informationVO.getIp(), informationVO.getPage(), "6");

                JSONArray jsonArray = JSONArray.parseArray(responseResult.getData());
                List<ThirdVideoVO> thirdVideoVOS = Lists.newArrayList();
                for(int i = 0;i < jsonArray.size();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    ThirdVideoVO thirdVideoVO = new ThirdVideoVO();
                    thirdVideoVO.setTitle(json.getString("title"));
                    thirdVideoVO.setUrl(json.getString("cover"));
                    JSONObject jsonObject1 = JSONObject.parseObject(json.getString("author"));
                    thirdVideoVO.setAuthor(jsonObject1.getString("name"));
                    thirdVideoVO.setAvatar(jsonObject1.getString("avatar"));
                    Date date = new Date(Long.valueOf(json.getString("publicTime"))*1000L);
                    thirdVideoVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                    thirdVideoVO.setId(json.getString("id"));
                    thirdVideoVO.setExtdata(json.getString("extData"));
                    thirdVideoVO.setPlayCnt(json.getString("playCnt"));
                    thirdVideoVO.setDuration(json.getString("duration"));
                    thirdVideoVOS.add(thirdVideoVO);
                }

                List<Map<String,Object>> listVideos = Lists.newArrayList();
                for(ThirdVideoVO thirdVideoVO : thirdVideoVOS){

                    Map<String,Object> map = Maps.newHashMap();
                    map.put("type", ChannelEnum.VIDEO.getValue());
                    map.put("obj", thirdVideoVO);
                    listVideos.add(map);
                }

                AdInfoVO adInfoVO1 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map1 = Maps.newHashMap();
                map1.put("type", ChannelEnum.AD.getValue());
                map1.put("obj", adInfoVO1);
                listVideos.add(0, map1);

                AdInfoVO adInfoVO2 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map2 = Maps.newHashMap();
                map2.put("type", ChannelEnum.AD.getValue());
                map2.put("obj", adInfoVO2);
                listVideos.add(2, map2);

                AdInfoVO adInfoVO3 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map3 = Maps.newHashMap();
                map3.put("type", ChannelEnum.AD.getValue());
                map3.put("obj", adInfoVO3);
                listVideos.add(3,map3);

                AdInfoVO adInfoVO4 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_VIDEO_POSITION);
                Map map4 = Maps.newHashMap();
                map4.put("type", ChannelEnum.AD.getValue());
                map4.put("obj", adInfoVO4);
                listVideos.add(7,map4);

                dataMap.put("data", listVideos);
            }
        });

        return dataMap;
    }

    /**
     * 获取信息流列表接口
     * @return
     */
    @RequestMapping(value = RequestUrlApi.INFORMATION_FLOW_LIST, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> listInformationFlow(final HttpServletRequest request, final InformationVO informationVO){
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

                ResponseResult responseResult = thirdInfoService.getToken(informationVO.getUserId());
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    ResponseResult res = thirdInfoService.listInformationFlow(sessionId, accessToken, informationVO.getC(), informationVO.getDevice(), informationVO.getUserId(), 7);
                    JSONObject jsonObject = JSONObject.parseObject(res.getData());
                    JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("res"));
                    List<ThirdNewsVO> list = Lists.newArrayList();
                    for(int i = 0;i<jsonArray.size();i++){
                        ThirdNewsVO thirdNewsVO = new ThirdNewsVO();
                        JSONObject job = jsonArray.getJSONObject(i);
                        thirdNewsVO.setTitle(job.getString("t"));
                        thirdNewsVO.setAuthor(job.getString("f"));
                        Date date = new Date(Long.valueOf(job.getString("p"))*1000L);
                        thirdNewsVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                        thirdNewsVO.setNewsType(job.getString("c"));
                        thirdNewsVO.setFilter(job.getString("filter"));
                        thirdNewsVO.setSource(job.getString("source"));
                        thirdNewsVO.setUrl(job.getString("u"));
                        thirdNewsVO.setRawurl(job.getString("rawurl"));
                        thirdNewsVO.setSid(res.getSid());
                        thirdNewsVO.setChannel(informationVO.getC());
                        thirdNewsVO.setA(job.getString("a"));
                        thirdNewsVO.setS(job.getString("s"));
                        thirdNewsVO.setPicUrl(job.getString("i"));
                        thirdNewsVO.setRptId(job.getString("r"));
                        int cmtNum = 0;
                        if(StringUtils.isEmpty(job.getString("cmt_num"))){
                            Random random = new Random();
                            cmtNum = new BigDecimal(job.getString("cmt_num")).multiply(new BigDecimal(100)).add(new BigDecimal(random.nextInt(100))).intValue();
                        }
                        thirdNewsVO.setReadCnt(cmtNum);
                        if(!StringUtils.isEmpty(job.getString("style"))){
                            thirdNewsVO.setStyle(job.getString("style"));
                        }else{
                            thirdNewsVO.setStyle("1");
                        }
                        list.add(thirdNewsVO);
                    }
                    List<Map<String,Object>> list1 = Lists.newArrayList();
                    for(ThirdNewsVO thirdNewsVO : list){

                        Map<String,Object> map = Maps.newHashMap();
                        map.put("type", ChannelEnum.NEWS.getValue());
                        map.put("obj", thirdNewsVO);
                        list1.add(map);
                    }

                    AdInfoVO adInfoVO1 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map1 = Maps.newHashMap();
                    map1.put("type", ChannelEnum.AD.getValue());
                    map1.put("obj", adInfoVO1);
                    list1.add(1, map1);

                    AdInfoVO adInfoVO2 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map2 = Maps.newHashMap();
                    map2.put("type", ChannelEnum.AD.getValue());
                    map2.put("obj", adInfoVO2);
                    list1.add(5, map2);

                    AdInfoVO adInfoVO3 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map3 = Maps.newHashMap();
                    map3.put("type", ChannelEnum.AD.getValue());
                    map3.put("obj", adInfoVO3);
                    list1.add(map3);

                    dataMap.put("data", list1);
                }
            }
        });
        return dataMap;
    }


    /**
     * 获取相关新闻列表接口
     * @return
     */
    @RequestMapping(value = RequestUrlApi.INFORMATION_FLOW_RELEVANT_LIST, method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> listInformationFlowRelevant(final HttpServletRequest request, final InformationVO informationVO){
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

                ResponseResult responseResult = thirdInfoService.getToken(informationVO.getUserId());
                if(responseResult.getErrno() != 0){
                    dataMap.put("result", "fail");
                    dataMap.put("msg", responseResult.getErrmsg());
                }else{
                    JSONObject json = JSONObject.parseObject(responseResult.getData());
                    String accessToken = json.getString("access_token");
                    ResponseResult res = thirdInfoService.listInformationFlow(sessionId, accessToken, "youlike", informationVO.getDevice(), informationVO.getUserId(), 6);
                    JSONObject jsonObject = JSONObject.parseObject(res.getData());
                    JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("res"));
                    List<ThirdNewsVO> list = Lists.newArrayList();
                    for(int i = 0;i<jsonArray.size();i++){
                        ThirdNewsVO thirdNewsVO = new ThirdNewsVO();
                        JSONObject job = jsonArray.getJSONObject(i);
                        thirdNewsVO.setTitle(job.getString("t"));
                        thirdNewsVO.setAuthor(job.getString("f"));
                        Date date = new Date(Long.valueOf(job.getString("p"))*1000L);
                        thirdNewsVO.setCreateDate(DateUtil.toString(date, DateUtil.DEFAULT_DATETIME_FORMAT));
                        thirdNewsVO.setNewsType(job.getString("c"));
                        thirdNewsVO.setFilter(job.getString("filter"));
                        thirdNewsVO.setSource(job.getString("source"));
                        thirdNewsVO.setUrl(job.getString("u"));
                        thirdNewsVO.setRawurl(job.getString("rawurl"));
                        thirdNewsVO.setSid(res.getSid());
                        thirdNewsVO.setChannel("youlike");
                        thirdNewsVO.setA(job.getString("a"));
                        thirdNewsVO.setS(job.getString("s"));
                        thirdNewsVO.setPicUrl(job.getString("i"));
                        thirdNewsVO.setRptId(job.getString("r"));
                        int cmtNum = 0;
                        if(StringUtils.isEmpty(job.getString("cmt_num"))){
                            Random random = new Random();
                            cmtNum = new BigDecimal(job.getString("cmt_num")).multiply(new BigDecimal(100)).add(new BigDecimal(random.nextInt(100))).intValue();
                        }
                        thirdNewsVO.setReadCnt(cmtNum);
                        if(!StringUtils.isEmpty(job.getString("style"))){
                            thirdNewsVO.setStyle(job.getString("style"));
                        }else{
                            thirdNewsVO.setStyle("1");
                        }
                        list.add(thirdNewsVO);
                    }
                    List<Map<String,Object>> list1 = Lists.newArrayList();
                    for(ThirdNewsVO thirdNewsVO : list){

                        Map<String,Object> map = Maps.newHashMap();
                        map.put("type", ChannelEnum.NEWS.getValue());
                        map.put("obj", thirdNewsVO);
                        list1.add(map);
                    }

                    AdInfoVO adInfoVO1 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map1 = Maps.newHashMap();
                    map1.put("type", ChannelEnum.AD.getValue());
                    map1.put("obj", adInfoVO1);
                    list1.add(0, map1);

                    AdInfoVO adInfoVO2 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map2 = Maps.newHashMap();
                    map2.put("type", ChannelEnum.AD.getValue());
                    map2.put("obj", adInfoVO2);
                    list1.add(2, map2);

                    AdInfoVO adInfoVO3 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map3 = Maps.newHashMap();
                    map3.put("type", ChannelEnum.AD.getValue());
                    map3.put("obj", adInfoVO3);
                    list1.add(3,map3);

                    AdInfoVO adInfoVO4 = thirdInfoService.getAdInfo(informationVO, AdConstantInfo.AD_NEWS_POSITION);
                    Map map4 = Maps.newHashMap();
                    map4.put("type", ChannelEnum.AD.getValue());
                    map4.put("obj", adInfoVO4);
                    list1.add(7,map4);

                    dataMap.put("data", list1);
                }
            }
        });
        return dataMap;
    }


}
