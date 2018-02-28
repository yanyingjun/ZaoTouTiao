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
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.BeanMapUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.common.util.Md5Util;
import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.core.model.vo.UserGoldRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserRegisterController, v0.1 2018年02月23日 11:44闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserController extends BaseController{


    @Autowired
    private IUserService userService;

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Autowired
    private INewsService newsService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_REGISTER_REQ, method = RequestMethod.POST)
    public Map<Object, Object> search(final User user) {

        // 定义Map集合对象
        final Map<Object, Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(user, ErrorCodeEnum.PARAMETER_ANOMALY);
            }
            @Override
            public void handle() throws Exception {
                //业务逻辑

            }

        });

        return dataMap;
    }

    /**
     * 查询电话是否已存在
     * @param telephone
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_EXIST_ACCOUNT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> existAccount(final String telephone){

        final Map<Object,Object> dataMap = Maps.newHashMap();

        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone, ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByMap(telephone);
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result","success");
                    dataMap.put("msg","用户不存在，可以创建");
                }else{
                    dataMap.put("result","failure");
                    dataMap.put("msg","用户已存在，请直接登录");
                    dataMap.put("data",user);
                }
            }
        });
        return dataMap;
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_LOGIN_REQ, method = RequestMethod.POST)
    public Map<Object,Object> login(final UserVO user){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(user.getTelephone(), ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(user.getPassword(), ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //查询用户信息
                String password = Md5Util.md5Encode(user.getPassword());
                User userData = userService.getUserByMap(user.getTelephone());
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                }else{
                    Boolean userLogin = userService.isUserLogin(user.getTelephone(), password);
                    if(userLogin == false){
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "密码错误");
                    }else{
                        //更改用户登录状态为在线
                        dataMap.put("result", "success");
                        dataMap.put("msg", "用户存在，直接登录");
                        if(userData.getIsOnline() == 0){
                            dataMap.put("isFirstLogin", "true");
                        }else{
                            dataMap.put("isFirstLogin", "false");
                        }
                        userData.setIsOnline(1);
                        userService.updateUserInfo(userData);
                        dataMap.put("data", userData);
                    }
                }
            }
        });
        return dataMap;
    }

    /**
     * 忘记密码
     * @param userVO
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_FORGET_PASSWORD_REQ, method = RequestMethod.POST)
    public Map<Object,Object> forgetPassword(final UserVO userVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(userVO.getTelephone(), ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(userVO.getPassword(), ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String password = Md5Util.md5Encode(userVO.getTelephone());
                User user = userService.getUserByMap(userVO.getTelephone());
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                }else{
                    //更新用户密码
                    user.setPassword(password);
                    userService.updateUserInfo(user);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "修改密码成功");
                    dataMap.put("data", user);
                }
            }
        });

        return dataMap;
    }

    /**
     * 退出登录
     * @param telephone
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_LOGOUT_REQ, method = RequestMethod.POST)
    public Map<Object,Object> logout(final String telephone){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(telephone,ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByMap(telephone);
                //更改用户状态为离线
                user.setIsOnline(0);
                userService.updateUserInfo(user);
                dataMap.put("result", "success");
                dataMap.put("msg", "成功退出");
                dataMap.put("data", user);
            }
        });

        return dataMap;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_GET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getUser(final Long userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId,ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByUserId(userId);
                dataMap.put("result", "success");
                dataMap.put("msg", "信息返回成功");
                dataMap.put("data", user);
            }
        });

        return dataMap;
    }


    /**
     * 用户信息修改
     * @param userVO
     * @return
     */
    @RequestMapping(value = UserMsgReq.USER_SET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> setUser(final UserVO userVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(userVO.getBirthday(),ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(userVO.getHeadPath(),ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(userVO.getNickName(),ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //过滤微信emoji表情
                Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                String nickName = userVO.getNickName();
                Matcher emojiMatcher = emoji.matcher(nickName);
                if (emojiMatcher.find()) {
                    nickName = emojiMatcher.replaceAll("*");
                }

                Map<String,Object> map = Maps.newHashMap();
                map.put("nickName", nickName);
                User user = userService.getUserByParam(map);

                User user1 = userService.getUserByUserId(userVO.getUserId());
                if(StringUtils.isEmpty(user1)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在");
                }

                if(user == null || user.getUserId().equals(userVO.getUserId())){
                    User user2 = new User();
                    BeanMapUtil.copy(userVO, user2);
                    userService.updateUserInfo(user2);
                    dataMap.put("result", "success");
                    dataMap.put("msg", "个人信息修改成功");
                    dataMap.put("data", user);
                }else{
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "昵称已存在，换个呗");
                }
            }
        });

        return dataMap;
    }


    /**
     * 我的收入
     * @param userId
     * @return
     */
    @RequestMapping(value = UserMsgReq.MY_INCOME_REQ, method = RequestMethod.POST)
    public Map<Object,Object> getUserIncome(final Long userId){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId,ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                User user = userService.getUserByUserId(userId);
                dataMap.put("result", "success");
                dataMap.put("msg", "信息返回成功");
                //金币来源去向
                List<UserGoldRecordVO> listUGR = userService.getUserGoldRecord(userId);
                dataMap.put("gold_record", listUGR);
                List<UserMoneyRecordVO> listUMR = userService.getUserMoneyRecord(userId);
                dataMap.put("money_record", listUMR);
                //我的金币，我的零钱
                dataMap.put("user_info", user);
                //金币零钱汇率
                ExchangeRate exchangeRate = exchangeRateService.getGoldToMoney();
                dataMap.put("gold_to_money", new BigDecimal(0.001).divide(exchangeRate.getGoldToMoney()));
                //昨天金币收入
                BigDecimal goldCount = userService.getGoldYesterday(userId);
                dataMap.put("get_gold_yesterday", goldCount);
                //昨天零钱收入
                BigDecimal moneyCount = userService.getMoneyYesterday(userId);
                dataMap.put("get_money_yesterday", moneyCount.setScale(2,BigDecimal.ROUND_HALF_UP));
                //累计金币收入
                BigDecimal goldCountAll = userService.getGoldAll(userId);
                dataMap.put("get_gold_all", goldCountAll);
                //累计零钱收入
                BigDecimal moneyCountAll = userService.getMoneyAll(userId);
                dataMap.put("get_money_all", moneyCountAll.setScale(2,BigDecimal.ROUND_HALF_UP));
            }
        });

        return dataMap;
    }

    /**
     * 修改密码
     * @param userVO
     * @return
     */
    @RequestMapping(value = UserMsgReq.PASSWORD_UPDATE_REQ, method = RequestMethod.POST)
    public Map<Object,Object> updatePassword(final UserVO userVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(userVO.getOldPassword(),ErrorCodeEnum.PARAMETER_ANOMALY);
                AssertsUtil.isNotBlank(userVO.getNewPassword(),ErrorCodeEnum.PARAMETER_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String oldPassword = Md5Util.md5Encode(userVO.getOldPassword());
                String newPassword = Md5Util.md5Encode(userVO.getNewPassword());
                User user = userService.getUserByUserId(userVO.getUserId());
                if(StringUtils.isEmpty(user)){
                    dataMap.put("result", "failure");
                    dataMap.put("msg", "用户不存在，请先注册");
                }else{
                    if(user.getPassword().equals(oldPassword) == false){
                        dataMap.put("result", "failure");
                        dataMap.put("msg", "密码错误，请重新输入");
                    }else{
                        user.setPassword(newPassword);
                        userService.updateUserInfo(user);
                        dataMap.put("result", "success");
                        dataMap.put("msg", "修改密码成功");
                        dataMap.put("data", user);
                    }
                }
            }
        });

        return dataMap;
    }

    /**
     * 收徒，填写邀请码，绑定师徒关系
     * @param userId 当前用户id
     * @param invitation 师傅的邀请码
     * @return
     */
    @RequestMapping(value = UserMsgReq.RECRUIT_SET_REQ, method = RequestMethod.POST)
    public Map<Object,Object> recruitSet(final Long userId, final String invitation){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotBlank(invitation, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //获取当前用户信息
                User user = userService.getUserByUserId(userId);
                //根据邀请码获取师傅id
                User parent = userService.getUserParent(invitation);
                //判断是否已经绑定过师徒关系
                User userPar = userService.getParentApprentice(userId, parent.getUserId());
                dataMap.put("result", "success");
                if(StringUtils.isEmpty(userPar)){
                    //添加师傅id到自己的表里
                    user.setParentId(parent.getUserId());
                    userService.updateUserInfo(user);
                    //查询收徒奖励金币数
                    ExchangeRate exchangeRate = exchangeRateService.getGoldToMoney();
                    String gold = exchangeRate.getRecruitGold().toString();
                    //给师傅添加收徒奖励金
                    BigDecimal goldAll = new BigDecimal(parent.getGold()).add(new BigDecimal(gold));
                    parent.setGold(goldAll.longValue());
                    userService.updateUserInfo(parent);
                    //添加金币新增记录
                    UserGoldRecord userGoldRecord = new UserGoldRecord();
                    userGoldRecord.setGold(Long.valueOf(gold));
                    userGoldRecord.setSource(6);
                    userGoldRecord.setUserId(parent.getUserId());
                    userGoldRecord.setType((byte)1);
                    userGoldRecord.setApprenticeId(userId);
                    userGoldRecord.setCreateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
                    userGoldRecord.setShareId((long)0);
                    userService.addUserGoldRecord(userGoldRecord);

                    dataMap.put("msg", "师徒关系绑定成功");
                    dataMap.put("parent_info", parent);
                    dataMap.put("data", user);
                }else{
                    dataMap.put("msg", "师徒关系已存在");
                }
            }
        });

        return dataMap;
    }

    /**
     * 获取徒弟列表信息
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = UserMsgReq.APPRENTICE_GET_REQ, method = RequestMethod.GET)
    public Map<Object,Object> apprenticeGet(final Long userId, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Page<User> page = userService.getApprenticePage(userId, pageRequest);
                dataMap.put("result", "success");
                dataMap.put("msg", "查询徒弟列表信息成功");
                dataMap.put("data", page.getRows());
                dataMap.put("count", page.getTotal());
            }
        });

        return dataMap;
    }

    /**
     * 待唤醒徒弟列表(三天未活跃用户列表,每页50)
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = UserMsgReq.WAKE_UP_APPRENTICE_LIST_GET)
    public Map<Object,Object> wakeUpApprenticeListGet(final Long userId, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Page<UserVO> page = userService.getWakeUpApprenticePage(userId, pageRequest);
                if(page.getTotal() > 0){
                    for(UserVO userVO : page.getRows()){
                        User user = userService.getUserByUserId(userVO.getParentId());
                        userVO.setParent(user);
                    }
                }
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("getWarkUpApprentice", page.getRows());
            }
        });
        return dataMap;
    }

    /**
     * 获取用户历史记录
     * @param userId
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = UserMsgReq.LOOK_RECORD_GET)
    public Map<Object,Object> lookRecordGet(final Long userId, final PageRequest pageRequest){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                Page<InfosVo> page = newsService.listLookRecordPage(userId, pageRequest);
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", page.getRows());
                dataMap.put("total", page.getTotal());
            }
        });

        return dataMap;
    }

    /**
     * 根据type类型删除用户相关通知信息
     * @param userId
     * @param type
     * @return
     */
    @RequestMapping(value = UserMsgReq.DELETE_INFORMATION_BY_TYPE, method = RequestMethod.POST)
    public Map<Object,Object> noticeDel(final int userId ,final String type){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
                AssertsUtil.isNotNull(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                //返回执行的结果
                String result = userService.delUserInformation(userId,type);
                dataMap.put("result", "success");
                dataMap.put("msg", "相关信息返回成功");
                dataMap.put("data", result);
            }
        });

        return dataMap;
    }



}
