/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.content;

import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ContentController, v0.1 2018年03月20日 22:24闫迎军(YanYingJun) Exp $
 */
@Controller
public class ContentController extends BaseController{


    /**
     * 跳转到内容列表页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_CONTENT_LIST_VIEW)
    public ModelAndView contentListView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_CONTENT_LIST_VIEW);
        return mv;
    }

    /**
     * 跳转到新增新闻页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_CONTENT_ADD_VIEW)
    public ModelAndView contentAddView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_CONTENT_ADD_VIEW);
        return mv;
    }
}
