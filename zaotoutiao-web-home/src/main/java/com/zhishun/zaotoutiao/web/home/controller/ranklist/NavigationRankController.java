/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.ranklist;

import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.core.model.thirdVo.App;
import com.zhishun.zaotoutiao.core.model.thirdVo.InformationVO;
import com.zhishun.zaotoutiao.core.model.vo.InfoRankVO;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;
import com.zhishun.zaotoutiao.web.home.constant.request.RankMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.RankMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private IInfosService infosService;

    /**
     * 进入导航排行 页面
     * @return
     */
    @RequestMapping(value = RankMsgReq.NAV_RANK_REQ)
    public String navRank(){
        return RankMsgView.NAV_RANK_VIEW;
    }

    /**
     * 一级标签排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.FIRST_TANK_REQ)
    public String firstTabRank(){
        return RankMsgView.FIRST_TANK_VIEW;
    }

    /**
     * 二级标签排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.SECOND_TANK_REQ)
    public String secondTabRank(){
        return RankMsgView.SECOND_TANK_VIEW;
    }

    /**
     * 关键词排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.KEYWORD_TANK_REQ)
    public String keywordRank(){
        return RankMsgView.KEYWORD_TANK_VIEW;
    }

    /**
     * 文章排行榜
     * @return
     */
    @RequestMapping(value = RankMsgReq.ARTICLE_TANK_REQ)
    public String articleRank(){
        return RankMsgView.ARTICLE_TANK_VIEW;
    }

    @RequestMapping(value = RankMsgReq.NAV_TANK_LIST_REQ)
    @ResponseBody
    public List<NavigationVO> navList(Integer dateNum, String date, @RequestParam(value = "appType",defaultValue = "1") Integer appType){
        //if(null == dateNum || StringUtils.isEmpty(date)){
        //    date = new Date().toString();
        //}
        return userReadService.getNavList(dateNum, date, appType);
    }

    /**
     * 获得info排行前30
     * @param navId
     * @param theClass
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_INFO_RANK_LIST_REQ)
    @ResponseBody
    public List<InfoRankVO> infoList(Long navId,@RequestParam(value = "theClass",defaultValue = "0")int theClass,Integer dateNum, String date){
        if(null == navId){
            return null;
        }
        return userReadService.getInfoRankVOList(navId,theClass,dateNum,date);
    }

    /**
     * 根据id获得infos 新闻内容或视频url
     * @param id
     * @param infoType
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_INFO_CONTENT_BY_ID_REQ,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getNewsContent(Long id,String infoType){
        return infosService.getConOrUrlById(id,infoType);
    }

}
