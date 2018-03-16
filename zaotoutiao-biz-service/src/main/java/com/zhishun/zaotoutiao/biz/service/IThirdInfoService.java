/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.alibaba.fastjson.JSONObject;
import com.zhishun.zaotoutiao.core.model.thirdVo.AdInfoVO;
import com.zhishun.zaotoutiao.core.model.thirdVo.InformationVO;
import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 360信息流接口
 * @author 闫迎军(YanYingJun)
 * @version $Id: IThirdInfoService, v0.1 2018年03月07日 20:22闫迎军(YanYingJun) Exp $
 */
public interface IThirdInfoService {

    /**
     * Token校验
     * @return
     */
    ResponseResult getToken(String userId);

    /**
     * 获取信息流数据
     * @return
     */
    ResponseResult listInformationFlow(String sessionId, String accessToken, String c, String device, String userId, Integer n);

    /**
     * 获取新闻频道信息
     * @param accessToken
     * @param device
     * @return
     */
    ResponseResult channelInformation(String accessToken, String device, String userId);

    /**
     * 新闻点击打点
     * @param device
     * @return
     */
    Boolean pressClick(String accessToken, String device, String url, String channel, String a, String c, String source, String t, String sid, String s, String style, String userId) throws Exception;

    /**
     * 视频频道列表
     * @param osType  客户端类型（android,ios）
     * @param imei  手机唯一设备码
     * @param serialNo  设备序列号
     * @param androidID  设备ID
     * @return
     */
    ResponseResult channelList(String osType, String userId, String ip);

    /**
     * 获取视频列表（综合和频道）
     * @return
     */
    ResponseResult ListVideos(String osType, String userId, String channelId, String ip, String backdata, String pageSize);

    /**
     * 视频详情
     * @param osType
     * @param imei
     * @param serialNo
     * @param androidID
     * @param id
     * @param extdata
     * @return
     */
    ResponseResult getVideoDetail(String osType, String userId, String id, String extdata, String ip);

    /**
     * 获取视频播放地址
     * @param osType
     * @param imei
     * @param serialNo
     * @param androidID
     * @param id
     * @param extdata
     * @return
     */
    ResponseResult getVideoPlayAddress(String osType, String userId, String id, String extdata, String ip);

    /**
     * 获取相关视频
     * @param osType
     * @param imei
     * @param serialNo
     * @param androidID
     * @param id
     * @param extdata
     * @return
     */
    ResponseResult relatedVideo(String osType, String userId, String id, String extdata, String ip, String page, String pageSize);

    /**
     * 获取广告
     * @param json
     * @return
     */
    AdInfoVO getAdInfo(InformationVO informationVO);

}