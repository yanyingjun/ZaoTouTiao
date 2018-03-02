/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: LoginController, v0.1 2018年03月02日 10:35闫迎军(YanYingJun) Exp $
 */
@Controller
public class LoginController extends BaseController{


    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return ZttWebMsgView.ZTT_MANAGE_LOGIN_VIEW;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_MANAGE_INDEX_REQ)
    public String login(final User user){
        return ZttWebMsgView.ZTT_MANAGE_INDEX_VIEW;
    }


    /**
     * 工作台
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_MANAGE_MAIN_REQ)
    public String main(){
        return ZttWebMsgView.ZTT_MANAGE_MAIN_VIEW;
    }




}
