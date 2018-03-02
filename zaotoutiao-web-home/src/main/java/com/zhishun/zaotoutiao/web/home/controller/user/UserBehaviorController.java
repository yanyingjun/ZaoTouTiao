/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.common.util.ValueEnumUtil;
import com.zhishun.zaotoutiao.core.model.enums.PlatformEnum;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserBehaviorController, v0.1 2018年03月02日 15:34闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserBehaviorController extends BaseController{

    @RequestMapping(value = ZttWebMsgReq.ZTT_USER_BEHAVIOR_REQ)
    public ModelAndView behavior(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_USER_BEHAVIOR_VIEW);
        //获取平台列表
        Object obj = ValueEnumUtil.getEnums(PlatformEnum.class);
        mv.addObject(obj);
        return mv;
    }
}
