/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IGoldRecordService;
import com.zhishun.zaotoutiao.biz.service.IMoneyRecordService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
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
 * @version $Id: UserMoneyController, v0.1 2018年03月05日 18:57闫迎军(YanYingJun) Exp $
 */
@Controller
public class UserMoneyController extends BaseController{


    @Autowired
    private IMoneyRecordService moneyRecordService;

    @Autowired
    private IGoldRecordService goldRecordService;

    /**
     * 跳转到零钱统计页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_USER_MONEY_REQ)
    public ModelAndView behavior(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_USER_MONEY_VIEW);
        return mv;
    }

    /**
     * 获得用户零钱数据
     * @param isNewOrOld
     * @param channelId
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_MONEY_LIST_REQ)
    @ResponseBody
    public Map<Object,Object> listUserMoneyNumber(final String isNewOrOld, final String source, final String channelId, final String type, final String startDate, final String endDate){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String channelId1 = "0";
                String isNewOrOld1 = "0";
                String source1 = "";
                if(!StringUtils.isEmpty(channelId)){
                    channelId1 = channelId;
                }
                if(!StringUtils.isEmpty(isNewOrOld)){
                    isNewOrOld1 = isNewOrOld;
                }
                if(!StringUtils.isEmpty(source)){
                    source1 = source;
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
                //获取总提现金额和满三十人数
                Map map_A = goldRecordService.countUserGoldAndMoney(null, null, null);
                int userCount = Integer.parseInt(map_A.get("userCount").toString());
                BigDecimal moneyNum = new BigDecimal(map_A.get("moneyNum").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);

                //获取零钱产出总量
                BigDecimal moneyAll = new BigDecimal(0);
                List<UserMoneyRecordVO> listAll = moneyRecordService.listMoneyCountBySource(isNewOrOld ,null, null, null);
                for(UserMoneyRecordVO userMoneyRecordVO : listAll){
                    moneyAll = moneyAll.add(userMoneyRecordVO.getSumMoney());
                }
                //获取时间段内零钱产出总量
                BigDecimal moneyTimeAll = new BigDecimal(0);
                List<UserMoneyRecordVO> list = moneyRecordService.listMoneyCountBySource(isNewOrOld, daysType, startDate1, endDate1);
                for(UserMoneyRecordVO vo : list){
                    moneyTimeAll = moneyTimeAll.add(vo.getSumMoney());
                }
                dataMap.put("moneyTimeAll", moneyTimeAll);
                dataMap.put("list", list);

                List<UserMoneyRecordVO> listUR = moneyRecordService.listMoneyCount(Integer.parseInt(channelId1), source1, daysType, isNewOrOld1, startDate1, endDate1);
                dataMap.put("listUR", listUR);
                dataMap.put("moneyAll", moneyAll);
                dataMap.put("listAll", listAll);
                dataMap.put("userCount", userCount);
                dataMap.put("moneyNum", moneyNum);
            }
        });

        return dataMap;
    }

    /**
     * 按日按周按月获得用户零钱数据
     * @param isNewOrOld
     * @param channelId
     * @param type
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_MONEY_LISTGROUP_REQ)
    @ResponseBody
    public Map<Object,Object> listUserMoneyNumberTwo(final String isNewOrOld, final String source, final String channelId, final String type){
        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotBlank(type, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                String channelId1 = "0";
                String isNewOrOld1 = "0";
                String source1 = "";
                if(!StringUtils.isEmpty(channelId)){
                    channelId1 = channelId;
                }
                if(!StringUtils.isEmpty(isNewOrOld)){
                    isNewOrOld1 = isNewOrOld;
                }
                if(!StringUtils.isEmpty(source)){
                    source1 = source;
                }

                List<UserMoneyRecordVO> listUR = moneyRecordService.listMoneyCount(Integer.parseInt(channelId1), source1, type, isNewOrOld1, null, null);
                dataMap.put("listUR", listUR);
            }
        });

        return dataMap;
    }
}
