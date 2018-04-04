/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.ranklist;

import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.biz.service.IUserReadService;
import com.zhishun.zaotoutiao.core.model.vo.AppTypeVO;
import com.zhishun.zaotoutiao.core.model.vo.InfoRankVO;
import com.zhishun.zaotoutiao.core.model.vo.LabelVO;
import com.zhishun.zaotoutiao.core.model.vo.NavigationVO;
import com.zhishun.zaotoutiao.web.home.constant.request.RankMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.RankMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private IChannelService channelService;

    /**
     * 导航类别列表
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_APP_TYPE_LIST_ERQ)
    @ResponseBody
    public List<AppTypeVO> getAppTypeList(){
        return channelService.getAppTypeList();
    }

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

    /**
     * 导航排行列表
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @RequestMapping(value = RankMsgReq.NAV_TANK_LIST_REQ)
    @ResponseBody
    public List<NavigationVO> navList(Integer dateNum, String date, @RequestParam(value = "appType",defaultValue = "1") Integer appType){
        return userReadService.getNavList(dateNum, date, appType);
    }

    /**
     * 一级标签排行列表
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    @RequestMapping(value = RankMsgReq.FIRST_TAB_TANK_LIST_REQ)
    @ResponseBody
    public List<NavigationVO> firstTabList(Integer dateNum, String date, @RequestParam(value = "appType",defaultValue = "1") Integer appType,String parentId){
        return userReadService.getFirstTabList(dateNum, date, appType,parentId);
    }

    /**
     * 二级标签排行列表
     * @param dateNum
     * @param date
     * @param appType
     * @param parentId
     * @return
     */
    @RequestMapping(value = RankMsgReq.SECOND_TAB_TANK_LIST_REQ)
    @ResponseBody
    public List<NavigationVO> secondTabList(Integer dateNum, String date, @RequestParam(value = "appType",defaultValue = "1") Integer appType, String parentId){
        return userReadService.getSecondTabList(dateNum, date, appType,parentId);
    }

    /**
     * 关键词排行（前100）
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_KEYWORDS_LIST_REQ)
    @ResponseBody
    public List<LabelVO> labelVOList(Integer dateNum, String date, @RequestParam(value = "appType",defaultValue = "1") Integer appType){
        return userReadService.getLabelVOList(dateNum,date,appType);
    }

    /**
     * 获得info排行前30
     * @param navChannelId
     * @param theClass
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_INFO_RANK_LIST_REQ)
    @ResponseBody
    public List<InfoRankVO> infoList(String navChannelId,@RequestParam(value = "theClass",defaultValue = "0")int theClass,Integer dateNum, String date){
        if(null == navChannelId || navChannelId.isEmpty()){
            return null;
        }
        return userReadService.getInfoRankVOList(navChannelId,theClass,dateNum,date);
    }

    /**
     * 获得info总排行前100
     * @param dateNum
     * @param date
     * @param appType
     * @return
     */
    @RequestMapping(value = RankMsgReq.GET_ALL_INFO_RANK_LIST_REQ)
    @ResponseBody
    public List<InfoRankVO> infoAllList(Integer dateNum,String date, @RequestParam(value = "appType",defaultValue = "1")Integer appType){
        return userReadService.getAllInfoRankVOList(dateNum,date,appType);
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
