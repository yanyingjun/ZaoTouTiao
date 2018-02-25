/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserController, v0.1 2018年02月24日 11:31闫迎军(YanYingJun) Exp $
 */
@Controller
public class UserController extends BaseController{


    /**
     * 登录页面视图
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_MANAGE_LOGIN_REQ, method = RequestMethod.GET)
    public String manage(final ModelMap modelMap,
                         HttpServletRequest request) {

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ZttWebMsgView.ZTT_MANAGE_LOGIN_VIEW;
    }


    @RequestMapping("test2")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("common/index");
        return mv;
    }
}
