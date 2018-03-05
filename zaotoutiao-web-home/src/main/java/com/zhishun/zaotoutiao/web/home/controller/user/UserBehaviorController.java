/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.google.common.collect.Maps;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIGlobalBinding;
import com.zhishun.zaotoutiao.biz.service.IPlatformWebService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.ValueEnumUtil;
import com.zhishun.zaotoutiao.core.model.entity.PlatformChannel;
import com.zhishun.zaotoutiao.core.model.entity.UserBehavior;
import com.zhishun.zaotoutiao.core.model.entity.UserPlatform;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.enums.PlatformEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserBehaviorVO;
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
        String type = "today";
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_USER_BEHAVIOR_VIEW);
        List<UserBehaviorVO> list = platformWebService.listBehaviorByType(0,0, type, null, null);
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

        mv.addObject("countDownLoadNum", countDownLoadNum);
        mv.addObject("countActivationNum", countActivationNum);
        mv.addObject("countRegisterNum", countRegisterNum);
        mv.addObject("countOperationNum", countOperationNum);
        mv.addObject("countActivationRateNum", countActivationRateNum);
        mv.addObject("countRegisterRateNum", countRegisterRateNum);


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
                List<UserBehaviorVO> list = platformWebService.listBehaviorByType(Integer.parseInt(platformId1),Integer.parseInt(channelId1), type, startDate, endDate);
                if(!type.equals("days") || !type.equals("weeks") || !type.equals("months")){
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
}
