/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.content;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.biz.service.IVideoService;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.ContentQueryVO;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.InfosMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ContentController, v0.1 2018年03月20日 22:24闫迎军(YanYingJun) Exp $
 */
@RestController
public class ContentController extends BaseController{

    @Autowired
    private IChannelService channelService;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IInfosService infosService;

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
    @RequestMapping(value = ZttWebMsgReq.ZTT_NEWS_ADD_VIEW)
    public ModelAndView contentAddView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_CONTENT_ADD_VIEW);
        return mv;
    }

    /**
     * 跳转到新增视频页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_VIDEO_ADD_VIEW)
    public ModelAndView addVideoView(){
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_VIDEO_ADD_VIEW);
        return mv;
    }

    /**
     * 跳转到视频修改页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_VIDEO_UPDATE_VIEW)
    public ModelAndView updateVideoView(Long id){
        InfosVO infosVO = infosService.getInfosById(id);
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_VIDEO_UPDATE_VIEW);
        mv.addObject("infosVO", infosVO);
        return mv;
    }

    /**
     * 跳转到新闻修改页面
     * @return
     */
    @RequestMapping(value = ZttWebMsgReq.ZTT_NEWS_UPDATE_VIEW)
    public ModelAndView updateNewsView(Long id){
        InfosVO infosVO = infosService.getInfosById(id);
        ModelAndView mv = new ModelAndView(ZttWebMsgView.ZTT_NEWS_UPDATE_VIEW);
        mv.addObject("infosVO", infosVO);
        return mv;
    }

    /**
     * 获取导航
     * @return
     */
    @RequestMapping(value = "/getChannels")
    public List<Channels> getNewsChannls(Integer appType){
        List<Channels> list = videoService.listVideoChannels(1, appType);
        return list;
    }

    /**
     * 获取子导航
     * @param parentId
     * @param appType
     * @return
     */
    @RequestMapping(value = "/subnavigation")
    public List<Channels> getSubnavigation(String parentId, Integer appType){
        List<Channels> list = channelService.getChannelsList(null, 1, appType, parentId);
        return list;
    }

    /**
     * 新增视频和新闻
     * @param infosVO
     * @return
     */
    @RequestMapping(value = InfosMsgReq.ADD_NEWS_VIDEO_REQ, method = RequestMethod.POST)
    public Map<Object,Object> addInfos(final InfosVO infosVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infosVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = infosService.addInfos(infosVO);
                if(num > 0){
                    dataMap.put("state", "success");
                    dataMap.put("msg", "新增成功");
                }else{
                    dataMap.put("state", "fail");
                    dataMap.put("msg", "新增失败");
                }
            }
        });

        return dataMap;
    }

    /**
     * 查询内容列表
     * @param contentQueryVO
     * @return
     */
    @RequestMapping(value = InfosMsgReq.LIST_INFOS_REQ, method = RequestMethod.POST)
    public Map<Object,Object> listInfos(final ContentQueryVO contentQueryVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(contentQueryVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                List<InfosVO> list = infosService.listInfos(contentQueryVO);
                dataMap.put("rows", list);
                dataMap.put("total", list.size());
            }
        });

        return dataMap;
    }

    /**
     * 删除新闻/视频
     * @param id
     * @return
     */
    @RequestMapping(value = InfosMsgReq.DEL_NEWS_VIDEO_REQ)
    public Map<Object,Object> delContent(final Long id){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotZero(id, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = infosService.delInfos(id);
                if(num > 0){
                    dataMap.put("state", "success");
                    dataMap.put("msg", "删除成功");
                }else{
                    dataMap.put("state", "fail");
                    dataMap.put("msg", "删除失败");
                }
            }
        });

        return dataMap;
    }

    /**
     * 修改新闻/视频
     * @return
     */
    @RequestMapping(value = InfosMsgReq.UPDATE_NEWS_VIDEO_REQ)
    public Map<Object,Object> updateContent(InfosVO infosVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(infosVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = infosService.updateInfos(infosVO);
                if(num > 0){
                    dataMap.put("state", "success");
                    dataMap.put("msg", "更新成功");
                }else{
                    dataMap.put("state", "fail");
                    dataMap.put("msg", "更新失败");
                }
            }
        });

        return dataMap;
    }


}
