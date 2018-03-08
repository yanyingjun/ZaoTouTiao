/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IPlatformWebService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.PlatformChannel;
import com.zhishun.zaotoutiao.core.model.entity.UserPlatform;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserBehaviorVO;
import com.zhishun.zaotoutiao.core.model.vo.UserVO;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserBehaviorController, v0.1 2018年03月02日 15:34闫迎军(YanYingJun) Exp $
 */
@Controller
public class UserBehaviorController extends BaseController{

    @Autowired
    private IPlatformWebService platformWebService;

    /**
     * 跳转到新增用户页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_USER_BEHAVIOR_REQ)
    public ModelAndView behavior(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_USER_BEHAVIOR_VIEW);
        return mv;
    }

    /**
     * 跳转到用户存留页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_USER_RETENTION_REQ)
    public ModelAndView retention(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_USER_RETENTION_VIEW);
        return mv;
    }

    /**
     * 获取平台列表
     * @return
     */
    @RequestMapping("/listPlatform")
    @ResponseBody
    public List<UserPlatform> listPlatform(){
        List<UserPlatform> list = platformWebService.listUserPlatform();
        UserPlatform userPlatform = new UserPlatform();
        userPlatform.setId(0);
        userPlatform.setPlatform("全部");
        list.add(0, userPlatform);
        return list;
    }

    /**
     * 根据平台获取渠道列表
     * @return
     */
    @RequestMapping("/listPlatformChannel")
    @ResponseBody
    public List<PlatformChannel> listPlatformChannel(final int platformId){
        List<PlatformChannel> listChannel = platformWebService.listPlatformChannelByPlatformId(platformId);
        PlatformChannel platformChannel = new PlatformChannel();
        if(platformId == 0){
            platformChannel.setId(0);
            platformChannel.setChannelName("全部");
            listChannel.add(0, platformChannel);
        }
        return listChannel;
    }

    /**
     * 获取用户行为数据
     * @param platformId 平台
     * @param channelId 渠道
     * @param type 时间类型
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_BEHAVIOR_LIST_REQ)
    @ResponseBody
    public Map<Object,Object> listBehavior(final String platformId, final String channelId, final String type, final String startDate, final String endDate){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String channelId1 = "0";
                String platformId1 = "0";
                if(!StringUtils.isEmpty(channelId)){
                    channelId1 = channelId;
                }
                if(!StringUtils.isEmpty(platformId)){
                    platformId1 = platformId;
                }
                String daysType = null;
                String startDate1 = null;
                String endDate1 = null;
                if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)){
                    startDate1 = DateUtil.getDateFormat(startDate);
                    endDate1 = DateUtil.getDateFormat(endDate);
                    int days = DateUtil.differentDaysByMillisecond(startDate, endDate);
                    if(days < 1){
                        daysType = "hours";

                    }else if( days >= 1 && days < 7){
                        daysType = "days";
                    }else if(days >= 7 && days < 30){
                        daysType = "weeks";
                    }else{
                        daysType = "months";
                    }
                }else{
                    daysType = type;
                }
                List<UserBehaviorVO> list = platformWebService.listBehaviorByType(Integer.parseInt(platformId1),Integer.parseInt(channelId1), daysType, startDate1, endDate1);
                if(!type.equals("days") && !type.equals("weeks") && !type.equals("months")){
                    //下载量
                    int countDownLoadNum = 0;
                    //激活
                    int countActivationNum = 0;
                    //注册
                    int countRegisterNum = 0;
                    //操作次数
                    int countOperationNum = 0;
                    for(UserBehaviorVO userBehaviorVO : list){
                        countDownLoadNum += userBehaviorVO.getDownloadNum();
                        countActivationNum += userBehaviorVO.getActivationNum();
                        countRegisterNum += userBehaviorVO.getRegisterNum();
                        countOperationNum += userBehaviorVO.getOperationNum();
                    }
                    //激活率
                    BigDecimal countActivationRateNum = new BigDecimal(countActivationNum).divide(new BigDecimal(countDownLoadNum),2,BigDecimal.ROUND_HALF_UP);
                    //注册率
                    BigDecimal countRegisterRateNum = new BigDecimal(countRegisterNum).divide(new BigDecimal(countActivationNum),2, BigDecimal.ROUND_HALF_UP);

                    dataMap.put("countDownLoadNum", countDownLoadNum);
                    dataMap.put("countActivationNum", countActivationNum);
                    dataMap.put("countRegisterNum", countRegisterNum);
                    dataMap.put("countOperationNum", countOperationNum);
                    dataMap.put("countActivationRateNum", countActivationRateNum);
                    dataMap.put("countRegisterRateNum", countRegisterRateNum);
                }
                dataMap.put("list", list);
            }
        });

        return dataMap;
    }

    /**
     * 获取用户存留信息
     * @param platformId 平台
     * @param channelId 渠道
     * @param type 时间类型
     * @param activeType 活跃类型
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_RETENTION_LIST_REQ)
    @ResponseBody
    public Map<Object,Object> listRetention(final String platformId, final String channelId, final String type, final String activeType, final String startDate, final String endDate){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String channelId1 = "0";
                String platformId1 = "0";
                if(!StringUtils.isEmpty(channelId)){
                    channelId1 = channelId;
                }
                if(!StringUtils.isEmpty(platformId)){
                    platformId1 = platformId;
                }
                String daysType = null;
                String startDate1 = null;
                String endDate1 = null;
                if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)){
                    startDate1 = DateUtil.getDateFormat(startDate);
                    endDate1 = DateUtil.getDateFormat(endDate);
                    int days = DateUtil.differentDaysByMillisecond(startDate, endDate);
                    if(days < 1){
                        daysType = "hours";
                    }else if( days >= 1 && days < 7){
                        daysType = "days";
                    }else if(days >= 7 && days < 30){
                        daysType = "weeks";
                    }else{
                        daysType = "months";
                    }
                }else{
                    daysType = type;
                }

                //活跃用户数量
                int activeNum = 0;
                //未登录用户数量
                int notLoginNum = 0;

                List<UserBehaviorVO> listBeh = platformWebService.listOpenAppCount(Integer.parseInt(platformId1),Integer.parseInt(channelId1), daysType, startDate1, endDate1);
                if(!type.equals("days") && !type.equals("weeks") && !type.equals("months")){

                    for(UserBehaviorVO userBehaviorVO : listBeh){
                        activeNum += userBehaviorVO.getIsOpenApp();
                    }
                    dataMap.put("activeNum", activeNum);
                }
                List<UserVO> listUser = platformWebService.listUserCount(Integer.parseInt(platformId1),Integer.parseInt(channelId1), daysType, startDate1, endDate1);
                if(!type.equals("days") && !type.equals("weeks") && !type.equals("months")){
                    for(UserVO userVO : listUser){
                        notLoginNum += userVO.getNotLoginNum();
                    }
                    dataMap.put("notLoginNum", notLoginNum);
                }
                if(activeType.equals("active")){
                    dataMap.put("list", listBeh);
                }else{
                    dataMap.put("list", listUser);
                }
            }
        });

        return dataMap;
    }
}
