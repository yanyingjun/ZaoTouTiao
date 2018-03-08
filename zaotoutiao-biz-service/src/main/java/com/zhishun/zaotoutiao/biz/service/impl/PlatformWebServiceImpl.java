/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IPlatformWebService;
import com.zhishun.zaotoutiao.core.model.entity.PlatformChannel;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserBehavior;
import com.zhishun.zaotoutiao.core.model.entity.UserPlatform;
import com.zhishun.zaotoutiao.core.model.vo.UserBehaviorVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.dal.mapper.PlatformChannelMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserBehaviorMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserPlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PlatformWebServiceImpl, v0.1 2018年03月03日 9:59闫迎军(YanYingJun) Exp $
 */
@Service
public class PlatformWebServiceImpl implements IPlatformWebService{

    @Autowired
    private UserPlatformMapper userPlatformMapper;

    @Autowired
    private PlatformChannelMapper platformChannelMapper;

    @Autowired
    private UserBehaviorMapper userBehaviorMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserPlatform> listUserPlatform() {
        return userPlatformMapper.listUserPlatform();
    }

    @Override
    public List<PlatformChannel> listPlatformChannelByPlatformId(int platformId) {
        return platformChannelMapper.listPlatformChannelByPlatformId(platformId);
    }

    @Override
    public List<UserBehaviorVO> listBehaviorByType(int platformId, int channelId, String type, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(platformId) && platformId != 0){
            map.put("platformId", platformId);
        }
        if(!StringUtils.isEmpty(channelId) && channelId != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userBehaviorMapper.listBehaviorByType(map);
    }

    @Override
    public List<UserBehaviorVO> listOpenAppCount(int platformId, int channelId, String type, String startDate, String endDate){
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(platformId) && platformId != 0){
            map.put("platformId", platformId);
        }
        if(!StringUtils.isEmpty(channelId) && channelId != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userBehaviorMapper.listOpenAppCount(map);
    }

    @Override
    public List<UserVO> listUserCount(int platformId, int channelId, String type, String startDate, String endDate){
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(platformId) && platformId != 0){
            map.put("platformId", platformId);
        }
        if(!StringUtils.isEmpty(channelId) && channelId != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userMapper.listUserCount(map);
    }
}
