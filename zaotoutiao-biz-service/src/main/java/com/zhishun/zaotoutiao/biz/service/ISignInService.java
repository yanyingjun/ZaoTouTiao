package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.StaticGoldConfig;
import com.zhishun.zaotoutiao.core.model.entity.UserSignIn;

import java.util.List;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ISignInService, v0.1 2018年02月28日 12:28闫迎军(YanYingJun) Exp $
 */
public interface ISignInService {

    /**
     * 判断今天是否已经签到
     * @param userId
     * @return
     */
    int isContinuousSingInToday(Long userId);

    /**
     * 判断昨天是否已经签到
     * @param userId
     * @return
     */
    List<UserSignIn> isContinuousSignInYesterday(Long userId);

    /**
     * 新增用户签到记录
     * @param userId
     * @param signDay
     * @return
     */
    int addUserSignIn(Long userId, int signDay);

    /**
     * 根据连续签到天数查询签到奖励
     * @param continuousDay
     * @return
     */
    StaticGoldConfig getSignInGold(int continuousDay);

    /**
     * 获取用户已经连续签到的天数
     * @param userId
     * @return
     */
    List<UserSignIn> getUserSignRecord(Long userId);

    /**
     * 获取签到奖励列表
     * @return
     */
    List<StaticGoldConfig> getSignGoldList();

}
