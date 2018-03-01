/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.ISignInService;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.StaticGoldConfig;
import com.zhishun.zaotoutiao.core.model.entity.UserSignIn;
import com.zhishun.zaotoutiao.dal.mapper.StaticGoldConfigMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserSignInMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: SignInServiceImpl, v0.1 2018年02月28日 12:30闫迎军(YanYingJun) Exp $
 */
@Service
public class SignInServiceImpl implements ISignInService{

    @Autowired
    private UserSignInMapper userSignInMapper;

    @Autowired
    private StaticGoldConfigMapper staticGoldConfigMapper;

    @Override
    public int isContinuousSingInToday(Long userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
        String zeroDay = sdf.format(new Date());
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("zeroDay", zeroDay);
        return userSignInMapper.isContinuousSingInToday(map);
    }

    @Override
    public List<UserSignIn> isContinuousSignInYesterday(Long userId) {
        return userSignInMapper.isContinuousSignInYesterday(userId);
    }

    @Override
    public int addUserSignIn(Long userId, int signDay) {
        UserSignIn userSignIn = new UserSignIn();
        userSignIn.setUserId(userId);
        userSignIn.setSignContinuousDay(signDay);
        userSignIn.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return userSignInMapper.insertSelective(userSignIn);
    }

    @Override
    public StaticGoldConfig getSignInGold(int continuousDay) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("name", "SIGN_IN");
        map.put("unit", continuousDay);
        return staticGoldConfigMapper.getSignInGold(map);
    }

    @Override
    public List<UserSignIn> getUserSignRecord(Long userId) {
        UserSignIn userSignIn = userSignInMapper.getUserSignRecord(userId);
        int dayNum = userSignIn.getSignContinuousDay();
        if(StringUtils.isEmpty(dayNum)){
            dayNum = 0;
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("dayNum", dayNum + 1);
        return userSignInMapper.getUserSignList(map);
    }

    @Override
    public List<StaticGoldConfig> getSignGoldList() {
        return staticGoldConfigMapper.listStaticGoldConfig();
    }
}
