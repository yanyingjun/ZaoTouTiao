/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.api.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import com.zhishun.zaotoutiao.api.home.request.UserMsgReq;
import com.zhishun.zaotoutiao.api.home.request.WeChatMsgReq;
import com.zhishun.zaotoutiao.biz.service.IExchangeRateService;
import com.zhishun.zaotoutiao.biz.service.IInformationService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.biz.service.IWithdrawalService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.BeanMapUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.RedPackUtil;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提现相关
 * @author 闫迎军(YanYingJun)
 * @version $Id: WithdrawalsController, v0.1 2018年03月01日 14:20闫迎军(YanYingJun) Exp $
 */
@RestController
public class WithdrawalsController extends BaseController{

    @Autowired
    private IWithdrawalService iWithdrawalService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Autowired
    private IInformationService iInformationService;


    /**
     * 微信提现
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping(value = UserMsgReq.WITHDRAWALS_APPLICATION, method = RequestMethod.GET)
    public Map<Object,Object> withdrawwalsApplication(final Long userId, final BigDecimal money){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(money, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                BigDecimal allMoney = new BigDecimal(0);
                User user = userService.getUserByUserId(userId);
                List<UserWithdrawalState> listUWS = iWithdrawalService.getHasApplication(userId);
                if(listUWS.size() >= 1){
                    //申请进行中，判断本次申请额度是否超过中金币数
                    for(UserWithdrawalState uws : listUWS){
                        BigDecimal applicationMoney = uws.getMoney();
                        allMoney = allMoney.add(money).add(applicationMoney);
                    }
                    BigDecimal userMoney = user.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(allMoney.intValue() > userMoney.intValue()){
                        //超过用户总零钱，不允许提现
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "余额不足，申请失败");
                        dataMap.put("data", false);
                    }else{
                        //开始之前的申请
                        for(UserWithdrawalState userWithdrawalState : listUWS){
                            //正常提现申请
                            BigDecimal amount = money.multiply(new BigDecimal(100));
                            //获取oppenId判断用户之前是否提现过
                            if(!StringUtils.isEmpty(user.getOppenId())){
                                //发红包
                                String billNo = userWithdrawalState.getBillNo();
                                String openId = user.getOppenId();
                                String result = RedPackUtil.sendRedPack(openId, amount.intValue(), billNo, "早头条新闻提现",
                                        "发送红包新消息","提现红包新消息","备注", "192.168.1.1");
                                Map<String,Object> result2 = JSONObject.parseObject(result, new TypeReference<Map<String, Object>>(){});
                                String resultCode = result2.get("result_code").toString();
                                if("FAIL".equals(resultCode)){
                                    String errCode = result2.get("err_code").toString();
                                    if("MONEY_LIMIT".equals(errCode)){
                                        //金额输入错误
                                        dataMap.put("result", "failure");
                                        dataMap.put("msg", "金额输入错误");
                                        dataMap.put("data", false);
                                    }else if("OPENID_ERROR".equals(errCode)){
                                        //还未关注公主号
                                        dataMap.put("result", "failure");
                                        dataMap.put("msg", "请先关注微信公众号(智顺文化)");
                                        dataMap.put("data", "http://www.cloudconfs.com/early");
                                    }else if("PARAM_ERROR".equals(errCode)){
                                        //还未关注公众号
                                        dataMap.put("result", "failure");
                                        dataMap.put("msg", "openid字段不正确");
                                        dataMap.put("data", false);

                                    }else{
                                        //其他错误
                                        dataMap.put("result", "failure");
                                        dataMap.put("msg", "其他错误");
                                        dataMap.put("data", errCode);
                                    }
                                }else if("SUCCESS".equals(resultCode)){
                                    String errCode = result2.get("err_code").toString();
                                    if("SUCCESS".equals(errCode)){
                                        dataMap.put("result", "success");
                                        dataMap.put("msg", "红包发送成功，请进入公众号(智顺文化)查看");
                                        dataMap.put("data", true);

                                        //修改请求状态
                                        userWithdrawalState.setStatus(1);
                                        iWithdrawalService.updateWithdrawalState(userWithdrawalState);
                                        //修改用户零钱
                                        //添加零钱记录
                                        userService.updateUserMoneyRecord(userId, money);
                                    }else{
                                        dataMap.put("result", "failure");
                                        dataMap.put("msg", "其他错误");
                                        dataMap.put("data", errCode);
                                    }
                                }

                            }else{
                               //请先关注微信公众号"智顺文化"
                                dataMap.put("result", "failure");
                                dataMap.put("msg", "请先关注微信公众号(智顺文化)");
                                dataMap.put("data", "http://www.cloudconfs.com/early");
                            }
                        }
                    }
                }else{
                    //正常提现申请
                    //添加零钱提现申请，状态为申请中
                    int status = 0;
                    Map<String,Object> result = iWithdrawalService.addWithdrawalState(userId, money, status);
                    BigDecimal amount = money.multiply(new BigDecimal(100));
                    //获取oppenId判断用户之前是否提现过
                    if(!StringUtils.isEmpty(user.getWechatId())){
                        if(!StringUtils.isEmpty(user.getOppenId())){
                            //发红包
                            String billNo = result.get("billNo").toString();
                            Long id = Long.valueOf(result.get("insertId").toString());
                            String openId = user.getOppenId();
                            String result1 = RedPackUtil.sendRedPack(openId, amount.intValue(), billNo,
                                    "早头条提现", "发送红包新消息",
                                    "提现红包新消息", "备注", "192.168.1.1");
                            Map<String,Object> result2 = JSONObject.parseObject(result1, new TypeReference<Map<String, Object>>(){});
                            String resultCode = result2.get("result_code").toString();
                            if("FAIL".equals(resultCode)){
                                String errCode = result2.get("err_code").toString();
                                if("MONEY_LIMIT".equals(errCode)){
                                    //金额输入错误
                                    dataMap.put("result", "failure");
                                    dataMap.put("msg", "金额输入错误");
                                    dataMap.put("data", false);
                                }else if("OPENID_ERROR".equals(errCode)){
                                    //还未关注公主号
                                    dataMap.put("result", "failure");
                                    dataMap.put("msg", "请先关注微信公众号(智顺文化)");
                                    dataMap.put("data", "http://www.cloudconfs.com/early");
                                }else if("PARAM_ERROR".equals(errCode)){
                                    //还未关注公众号
                                    dataMap.put("result", "failure");
                                    dataMap.put("msg", "openid字段不正确");
                                    dataMap.put("data", false);

                                }else{
                                    //其他错误
                                    dataMap.put("result", "failure");
                                    dataMap.put("msg", "其他错误");
                                    dataMap.put("data", errCode);
                                }
                            }else if("SUCCESS".equals(resultCode)){
                                String errCode = result2.get("err_code").toString();
                                if("SUCCESS".equals(errCode)){
                                    dataMap.put("result", "success");
                                    dataMap.put("msg", "红包发送成功，请进入公众号(智顺文化)查看");
                                    dataMap.put("data", true);

                                    //修改请求状态
                                    UserWithdrawalState userWithdrawalState = new UserWithdrawalState();
                                    userWithdrawalState.setStatus(1);
                                    userWithdrawalState.setId(id);
                                    iWithdrawalService.updateWithdrawalState(userWithdrawalState);
                                    //修改用户零钱
                                    //添加零钱记录
                                    userService.updateUserMoneyRecord(userId, money);
                                }else{
                                    dataMap.put("result", "failure");
                                    dataMap.put("msg", "其他错误");
                                    dataMap.put("data", errCode);
                                }
                            }
                        }else{
                            //请先关注微信公众号"智顺文化"
                            dataMap.put("result", "failure");
                            dataMap.put("msg", "请先关注微信公众号(智顺文化)");
                            dataMap.put("data", "http://www.cloudconfs.com/early");
                        }
                    }else{
                        //请先绑定微信
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "请先绑定微信");
                        dataMap.put("data", false);
                    }
                }
            }
        });

        return dataMap;

    }

    /**
     * 兑换微信红包
     * @return
     */
    @RequestMapping(value = WeChatMsgReq.RED_ENVELOPE_GET)
    public Map<Object,Object> redEnvelopeGet(){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
            }

            @Override
            public void handle() throws Exception {

                List<StaticRedEnvelope> list = iWithdrawalService.listRedEnvelope();
                dataMap.put("result", "success");
                dataMap.put("msg", "返回信息成功");
                dataMap.put("data", list);
            }
        });

        return dataMap;
    }

    /**
     * 用户登录(第三方账号登录)(微信登录)
     * @param wechatId
     * @return
     */
    @RequestMapping(value = WeChatMsgReq.LOGIN_WECHAT)
    public Map<Object,Object> loginWechat(final String wechatId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(wechatId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                //判断用户是否存在
                Map map = Maps.newHashMap();
                map.put("wechatId", wechatId);
                User user = userService.getUserByParam(map);
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                    dataMap.put("data", null);
                }else{
                    dataMap.put("result", "success");
                    dataMap.put("msg", "用户已存在，请直接登录");
                    dataMap.put("data", user);
                }
            }
        });

        return dataMap;

    }

    /**
     * 用户微信注册(绑定微信个人信息注册)
     * 手机号，密码，微信id,微信头像，微信昵称
     * is_first_login:是否是首次登录 \n telephone 手机号，password 密码，wechatId 微信id, wechat_head 微信头像， wechat_name 微信昵称
     * @param telephone
     * @param wechatId
     * @return
     */
    @RequestMapping(value = WeChatMsgReq.USER_REGISTER_WECHAT)
    public Map<Object,Object> userRegisterWechat(final String telephone, final String wechatId,
                                                 final String password, final String wechatHead, final String wechatName){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(wechatId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {

                User user = userService.getUserByMap(telephone);
                Map map = Maps.newHashMap();
                map.put("wechatId", wechatId);
                User user1 = userService.getUserByParam(map);
                if(StringUtils.isEmpty(user)){
                    //判断微信是否已经绑定
                    if(StringUtils.isEmpty(user1)){
                        //微信还不存在
                        Long userId = userService.addUser(telephone, password, wechatId, wechatHead, wechatName);
                        //为新用户新增金币和零钱
                        ExchangeRate exchangeRate = exchangeRateService.getGoldToMoney();

                        //为用户添加消息和公告
                        List<UserInformationTemplate> listInformation = iInformationService.listInformationNew();
                        for(UserInformationTemplate userInfo : listInformation){
                            UserInformation userInformation = new UserInformation();
                            BeanMapUtil.copy(userInfo, userInformation);
                            userInformation.setId(null);
                            userInformation.setUserId(userId);
                            userInformation.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                            iInformationService.addUserInformation(userInformation);
                        }
                        User user2 = userService.getUserByMap(telephone);
                        //查询是否是第一次登录
                        if(user2.getIsOnline() == 0){
                            dataMap.put("isFirstLogin", true);
                        }else{
                            dataMap.put("isFirstLogin", false);
                        }
                        dataMap.put("result", "success");
                        dataMap.put("msg", "新增用户成功");
                        dataMap.put("data", user2);
                    }else{
                        dataMap.put("result", "success");
                        dataMap.put("msg", "微信号已绑定");
                        dataMap.put("data", user1);
                    }
                }else{
                    dataMap.put("result", "success");
                    dataMap.put("msg", "手机号已注册，暂时不能用微信号注册");
                    dataMap.put("data", user);
                }

            }
        });

        return dataMap;
    }

    /**
     * 绑定微信(修改用户信息)
     * @param telephone
     * @param wechatId
     * @param wechatName
     * @param wechatHead
     * @return
     */
    @RequestMapping(value = WeChatMsgReq.BINDING_WECHAT, method = RequestMethod.POST)
    public Map<Object,Object> bingingWechat(final String telephone, final String wechatId,
                                            final String wechatName, final String wechatHead){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(wechatId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(wechatName, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(wechatHead, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String weName = wechatName;
                //过滤微信emoji表情
                Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                Matcher emojiMatcher = emoji.matcher(weName);
                if(emojiMatcher.find()){
                    weName = emojiMatcher.replaceAll("*");
                }

                //判断用户是否存在
                User user = userService.getUserByMap(telephone);
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                    dataMap.put("data", null);
                }else{
                    Map map = Maps.newHashMap();
                    map.put("wechatId", wechatId);
                    User user1 = userService.getUserByParam(map);
                    if(StringUtils.isEmpty(user1)){
                        //绑定微信
                        userService.updateWechatUser(telephone, weName, wechatId, wechatHead);
                        dataMap.put("result", "success");
                        dataMap.put("msg", "微信绑定成功");
                        dataMap.put("data", user);
                    }else{
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "微信已绑定，不可修改，请更换其他微信");
                        dataMap.put("data", null);
                    }
                }

            }
        });

        return dataMap;
    }
}
