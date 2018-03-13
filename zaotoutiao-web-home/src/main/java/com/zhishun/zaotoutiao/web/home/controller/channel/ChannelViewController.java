/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.channel;

import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ChannelMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ChannelMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelViewController, v0.1 2018年02月26日 16:31闫迎军(YanYingJun) Exp $
 */

@Controller
public class ChannelViewController extends BaseController{

    /**
     * 登录页面视图
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.TO_CHANNEL_MANAGE_REQ, method = RequestMethod.GET)
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

        return ChannelMsgView.CHANNEL_MANAGE_VIEW;
    }


    /**
     * 新增导航
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_ADD_REQ)
    public String addChannelView(final ModelMap modelMap,
                         HttpServletRequest request) {

        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ChannelMsgView.ADD_CHANNEL_VIEW;
    }

    /**
     * 一级标签
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.FIRST_TABS_REQ)
    public String firstTabs(final ModelMap modelMap,
                           HttpServletRequest request){
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ChannelMsgView.FIRST_TABS_VIEW;
    }

    /**
     * 二级标签
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.SECOND_TABS_REQ)
    public String secondTabs(final ModelMap modelMap,
                            HttpServletRequest request){
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ChannelMsgView.SECOND_TABS_VIEW;
    }


    /**
     * 新增一级标签
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.ADD_FIRST_TAB_REQ)
    public String addFirstTab(final ModelMap modelMap,
                             HttpServletRequest request){
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ChannelMsgView.ADD_FIRST_TAB_VIEW;
    }


    /**
     * 新增二级标签
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.ADD_SECOND_TAB_REQ)
    public String addSecondTab(final ModelMap modelMap,
                              HttpServletRequest request){
        this.excute(modelMap, request, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }
            @Override
            public void handle() throws Exception {

            }
        });

        return ChannelMsgView.ADD_SECOND_TAB_VIEW;
    }
}
