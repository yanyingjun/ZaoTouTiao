/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.biz.service.IExchangeRateService;
import com.zhishun.zaotoutiao.biz.service.ISignInService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: SignInController, v0.1 2018年02月28日 12:27闫迎军(YanYingJun) Exp $
 */

@RestController
public class SignInController extends BaseController{

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISignInService signInService;


    /**
     * 用户签到
     * data:false表示不可签到，true表示可以签到   \n is_submit:1，用户签到，0,只获取用户签到信息 \n source:来源去向，0:注册登录, 1:新闻阅读, 2:徒弟阅读进贡, 3:签到, 4:开宝箱, 5:评论,6:收徒
     * @param userId
     * @param isSubmit
     * @return
     */
    @RequestMapping(value = UserMsgReq.SIGN_IN, method = RequestMethod.POST)
    public Map<Object,Object> signIn(final Long userId, final int isSubmit){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //金币来源为签到
                int source = 3;
                //获取活动天数
                //获取用户注册时间
                //判断是否超过活动天数
                ExchangeRate exchangeRate = exchangeRateService.getGoldToMoney();
                //int signActivityDays = exchangeRate.getSignActivityDays();
                //User user = userService.isSurpassingActivtiy(signActivityDays, userId);
                /*if(!StringUtils.isEmpty(user)){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "该活动已过期");
                    dataMap.put("data", false);
                }else{*/
                    UserGoldRecord userGoldRecord = userService.getUserGoldRecordInfo(userId);
                    int count = signInService.isContinuousSingInToday(userId);
                    if(count > 0){
                        //当天已经签到
                        dataMap.put("result", "success");
                        dataMap.put("msg", "今天已经签到");
                        dataMap.put("data", false);
                        dataMap.put("getGold", 0);
                        dataMap.put("getGoldRecord", userGoldRecord);
                    }else{
                        if(isSubmit == 1){
                            //可以签到
                            //判断是否连续签到，大于七天则从头开始签到
                            List<UserSignIn> signInList = signInService.isContinuousSignInYesterday(userId);
                            if(signInList.size() >= 1){
                                //查询签到天数，大于七天则从头开始签到
                                int signContinuousDay = signInList.get(0).getSignContinuousDay();
                                Calendar cal = Calendar.getInstance();
                                int day = cal.get(Calendar.DAY_OF_MONTH);
                                if(day == 1){
                                    //从头开始签到
                                    signInService.addUserSignIn(userId, 1);
                                    //添加金币
                                    //根据连续签到天数查询签到奖励
                                    StaticGoldConfig staticGoldConfig = signInService.getSignInGold(signContinuousDay);
                                    int gold = Integer.parseInt(staticGoldConfig.getValue());
                                    userService.addUserGoldRecord(source,userId, gold, null);
                                    userService.updateUserInfo(userId, gold);
                                    dataMap.put("result", "success");
                                    dataMap.put("msg", "已经连续签到超过七天，执行新的签到");
                                    dataMap.put("data", false);
                                    dataMap.put("getGold", gold);
                                    dataMap.put("getGoldRecord", userGoldRecord);
                                }else{
                                    signContinuousDay = signContinuousDay + 1;
                                    signInService.addUserSignIn(userId, signContinuousDay);
                                    //添加金币
                                    //根据连续签到天数查询签到奖励
                                    StaticGoldConfig staticGoldConfig = signInService.getSignInGold(signContinuousDay);
                                    BigDecimal money = new BigDecimal(0);
                                    int gold = 0;
                                    if(signContinuousDay == 28){
                                        money = new BigDecimal(Integer.parseInt(staticGoldConfig.getValue()));
                                        userService.addUserMoneyRecord(source, userId, money, null);
                                        userService.updateUserMoneyRecord(userId, money);
                                    }else{
                                        gold = Integer.parseInt(staticGoldConfig.getValue());
                                        userService.addUserGoldRecord(source,userId, gold, null);
                                        userService.updateUserInfo(userId, gold);
                                    }
                                    dataMap.put("result", "success");
                                    dataMap.put("msg", "正常签到");
                                    dataMap.put("data", false);
                                    dataMap.put("getGold", gold);
                                    dataMap.put("getMoney", money);
                                    dataMap.put("getGoldRecord", userGoldRecord);
                                }
                            }else{
                                //从头开始签到
                                signInService.addUserSignIn(userId, 1);
                                //添加金币
                                //根据连续签到天数查询签到奖励
                                StaticGoldConfig staticGoldConfig = signInService.getSignInGold(1);
                                int gold = Integer.parseInt(staticGoldConfig.getValue());
                                userService.addUserGoldRecord(source,userId, gold, null);
                                userService.updateUserInfo(userId, gold);
                                dataMap.put("result", "success");
                                dataMap.put("msg", "未连续签到，从头开始签到");
                                dataMap.put("data", false);
                                dataMap.put("getGold", gold);
                                dataMap.put("getGoldRecord", userGoldRecord);
                            }
                        }else{
                            dataMap.put("result", "success");
                            dataMap.put("msg", "今天还没签到");
                            dataMap.put("data", true);
                            dataMap.put("getGold", 0);
                            dataMap.put("getGoldRecord", userGoldRecord);
                        }
                    }

                    //获取用户已经连续签到的天数
                    List<UserSignIn> signList = signInService.getUserSignRecord(userId);
                    dataMap.put("signList", signList);
                    //获取签到奖励列表
                    List<StaticGoldConfig> sgcList = signInService.getSignGoldList();
                    dataMap.put("signGetGold", sgcList);
                //}
            }
        });

        return dataMap;
    }
}
