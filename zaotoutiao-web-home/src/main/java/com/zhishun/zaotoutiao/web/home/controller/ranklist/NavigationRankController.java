/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.ranklist;

import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;
import com.zhishun.zaotoutiao.web.home.constant.request.RankMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.RankMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author BugMan
 * @version $Id: NavigationRankController, v0.1 2018年03月23日 10:44BugMan Exp $
 */

@Controller
public class NavigationRankController extends BaseController{

    @Autowired
    private IUserReadService userReadService;

    /**
     * 进入导航排行 页面
     * @return
     */
    @RequestMapping(value = RankMsgReq.NAV_RANK_REQ)
    @ResponseBody
    public String navRank(){
        return RankMsgView.NAV_RANK_VIEW;
    }

    /**
     * 一级标签排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.FIRST_TANK_REQ)
    @ResponseBody
    public String firstTabRank(){
        return RankMsgView.FIRST_TANK_VIEW;
    }

    /**
     * 二级标签排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.SECOND_TANK_REQ)
    @ResponseBody
    public String secondTabRank(){
        return RankMsgView.SECOND_TANK_VIEW;
    }

    /**
     * 关键词排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.KEYWORD_TANK_REQ)
    @ResponseBody
    public String keywordRank(){
        return RankMsgView.KEYWORD_TANK_VIEW;
    }

    /**
     * 文章排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.ARTICLE_TANK_REQ)
    @ResponseBody
    public String articleRank(){
        return RankMsgView.ARTICLE_TANK_VIEW;
    }

    @RequestMapping(value = RankMsgReq.NAV_TANK_LIST_REQ)
    @ResponseBody
    public List<NavigationVO> navList(Integer dateNum, String date, String infoType){
        return userReadService.getNavList(dateNum, date, infoType);
    }

}
