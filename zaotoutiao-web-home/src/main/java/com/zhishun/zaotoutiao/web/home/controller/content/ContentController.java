/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.content;

import com.zhishun.zaotoutiao.web.home.constant.request.ContentMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ContentMsgView;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台新闻及视频相关接口
 * @author BugMan
 * @version $Id: ContentController, v0.1 2018年03月19日 11:15BugMan Exp $
 */
@Controller
public class ContentController extends BaseController {

    /**
     * 新增文章
     * @return
     */
    @RequestMapping(value = ContentMsgReq.ADD_NEWS_REQ)
    public String addNews(){
        return ContentMsgView.ADD_NEWS_VIEW;
    }

    /**
     * 新增视频
     * @return
     */
    @RequestMapping(value = ContentMsgReq.ADD_VIDEO_REQ)
    public String addVideo(){
        return ContentMsgView.ADD_VIDEO_VIEW;
    }

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
