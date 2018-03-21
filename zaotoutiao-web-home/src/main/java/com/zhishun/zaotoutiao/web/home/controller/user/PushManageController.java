/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PushManageController, v0.1 2018年03月16日 17:14闫迎军(YanYingJun) Exp $
 */
@Controller
public class PushManageController extends BaseController{

    /**
     * 跳转到推送列表页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_PUSH_MANAGE_VIEW)
    public ModelAndView pushListView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_PUSH_LIST_VIEW);
        return mv;
    }

    /**
     * 跳转到添加新闻页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_ADD_NEWS_VIEW)
    public ModelAndView addPushNewsView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_PUSH_ADD_NEWS_VIEW);
        return mv;
    }
}
