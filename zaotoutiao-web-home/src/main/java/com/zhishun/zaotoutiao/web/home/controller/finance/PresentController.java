/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.finance;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IUserService;
import com.zhishun.zaotoutiao.biz.service.IWithdrawalService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.entity.UserPlatform;
import com.zhishun.zaotoutiao.core.model.entity.UserWithdrawalState;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.UserWithdrawalStateVO;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PresentController, v0.1 2018年03月19日 18:00闫迎军(YanYingJun) Exp $
 */
@Controller
public class PresentController extends BaseController{


    @Autowired
    private IWithdrawalService withdrawalService;

    /**
     * 提现申请页面跳转
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_PRESENT_VIEW)
    public ModelAndView presentReq(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_APPLY_FOR_CASH_VIEW);
        return mv;
    }

    /**
     * 金币预警页面跳转
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_EARLY_WARNING_VIEW)
    public ModelAndView earlyWaring(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_EARLY_WARNING);
        return mv;
    }

    /**
     * 获取提现申请列表
     * @param keyWord
     * @param channelId
     * @param createDate
     * @param status
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_PRESENT_REQ)
    @ResponseBody
    public Map<Object,Object> listWithdrawals(final String keyWord, final String channelId, final String createDate, final Integer auditStatus){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                List<UserWithdrawalStateVO> list = withdrawalService.listWithdrawals(keyWord, channelId, createDate, auditStatus);
                dataMap.put("rows", list);
                dataMap.put("total", list.size());
            }
        });

        return dataMap;
    }

    /**
     * 更新用户状态
     * @param userId
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_UPDATE_USER_PRESENTSTATUS_REQ)
    @ResponseBody
    public Map<Object,Object> updateUser(final Long userId, final Integer presentStatus){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(userId, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                UserWithdrawalState userWithdrawalState = withdrawalService.getHasApplication(userId).get(0);
                userWithdrawalState.setStatus(presentStatus);
                int num = withdrawalService.updateWithdrawalState(userWithdrawalState);
                if(num > 0){
                    dataMap.put("result", "success");
                    dataMap.put("msg", "操作成功");
                }else{
                    dataMap.put("result", "fail");
                    dataMap.put("msg", "操作失败");
                }
            }
        });

        return dataMap;
    }












}
