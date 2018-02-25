/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserController, v0.1 2018年02月24日 11:31闫迎军(YanYingJun) Exp $
 */
@RestController
public class UserController extends BaseController{


    @RequestMapping("/test")
    public String manage(final ModelMap modelMap,
                         HttpServletRequest request) {

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

                // 结果值存储
                modelMap.put("channelAllocationQuery", null);
            }

        });

        return "index";
    }

    /*@RequestMapping("test1")
    public ModelAndView helloHtml(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("test2")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }*/
}
