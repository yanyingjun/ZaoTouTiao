/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.thirdVo.ResponseResult;

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
    ResponseResult getToken();

    /**
     * 获取信息流数据
     * @return
     */
    ResponseResult listInformationFlow(String sessionId, String accessToken, String c, String device);

    /**
     * 获取频道信息
     * @param accessToken
     * @param device
     * @return
     */
    ResponseResult channelInformation(String accessToken, String device);

    /**
     * 新闻点击打点
     * @param device
     * @return
     */
    ResponseResult pressClick(String accessToken, String device, String url, String channel, String a, String c, String source, String t, String sid);
}