/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.api.home.controller.third;

import com.zhishun.zaotoutiao.api.home.constant.request.UserModuleReq;
import com.zhishun.zaotoutiao.api.home.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserRegister, v0.1 2018年02月06日 20:47闫迎军(YanYingJun) Exp $
 */

@Controller
public class UserRegister extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @RequestMapping(value = UserModuleReq.USER_REGISTER)
    @ResponseBody
    public Map userRegister(){

        return null;
    }
}
