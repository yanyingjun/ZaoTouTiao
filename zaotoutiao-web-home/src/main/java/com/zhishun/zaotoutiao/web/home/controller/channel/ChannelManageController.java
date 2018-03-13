/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.channel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IChannelService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.common.util.AssertsUtil;
import com.zhishun.zaotoutiao.common.util.DateUtil;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.core.model.vo.ChannelsVO;
import com.zhishun.zaotoutiao.dal.mapper.ChannelsMapper;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.constant.request.ChannelMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.request.ZttWebMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ChannelMsgView;
import com.zhishun.zaotoutiao.web.home.constant.view.ZttWebMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelManageController, v0.1 2018年02月25日 15:31闫迎军(YanYingJun) Exp $
 */

@RestController
public class ChannelManageController extends BaseController{

    @Autowired
    private IChannelService channelService;

    /**
     * 获取导航列表
     * @return
     */
    //@RequestMapping(value = ChannelMsgReq.CHANNEL_MANAGE_REQ, method = RequestMethod.GET)
    public Map<Object,Object> listChannelsPage(final String name, final Integer status, final Integer page, final Integer limit){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                PageRequest pageRequest = new PageRequest();
                pageRequest.setPageNo(page);
                pageRequest.setPageSize(limit);
                ChannelsVO channelsVO = new ChannelsVO();
                channelsVO.setName(name);
                channelsVO.setStatus(status);
                Page<Channels> channelsPage = channelService.listChannelsPage(channelsVO, pageRequest);
                dataMap.put("data", channelsPage.getRows());
                dataMap.put("count", channelsPage.getTotal());
            }
        });

        return dataMap;
    }

    /**
     * 获取导航列表
     * @param name
     * @param status
     * @param appType
     * @param parentId
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_MANAGE_REQ)
    @ResponseBody
    public List<ChannelsVO> getChannelsList(final String name, final Integer status, final Integer appType, @RequestParam(value="parentId",defaultValue = "0") final Long parentId){

        List<Channels> channelsList = channelService.getChannelsList(name,status,appType,parentId);
        List<ChannelsVO> channelsVOList = new ArrayList<ChannelsVO>();
        for(Channels channels:channelsList){
            ChannelsVO channelsVO = new ChannelsVO();
            int st = channels.getStatus();
            if(st == 0){
                channelsVO.setStatusName("下架");
            }else if(st == 1){
                channelsVO.setStatusName("激活");

            }
            channelsVO.setStatus(st);
            int ty = channels.getAppType();
            if(ty == 1){
                channelsVO.setTypeName("文章");
            }else if(ty == 0){
                channelsVO.setTypeName("视频");
            }
            channelsVO.setAppType(ty);
            channelsVO.setChannelId(channels.getChannelId());
            channelsVO.setChannelOrder(channels.getChannelOrder());
            channelsVO.setId(channels.getId());
            channelsVO.setUpdateDate(channels.getUpdateDate());
            channelsVO.setName(channels.getName());
            channelsVOList.add(channelsVO);
        }
        return channelsVOList;
    }

    /**
     * 更新导航状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.UPDATE_CHANNEL_STATUS)
    @ResponseBody
    public int updateChannel(final Long id,final int status){
        Channels channels = new Channels();
        channels.setId(id);
        channels.setStatus(status);
        return channelService.updateChannels(channels);
    }


    /**
     * 根据id删除导航
     * @param id
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.DELETE_CHANNEL)
    @ResponseBody
    public int deleteChannel(final Long id){
        return channelService.deleteChannelById(id);
    }

    /**
     * 导航排序
     * @param id
     * @param channelOrderChangeNum
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_ORDER)
    @ResponseBody
    public int doChannelOrder(final Long id, final int channelOrderChangeNum){
        return channelService.channelsOrder(id,channelOrderChangeNum);
    }

    /**
     * 更新导航名称
     * @param editId
     * @param editChannelName
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.UPDATE_CHANNEL_NAME)
    @ResponseBody
    public int doEditChannel(final Long editId,final String editChannelName){
        Channels channels = new Channels();
        channels.setId(editId);
        channels.setName(editChannelName);
        return channelService.updateChannels(channels);
    }


    /**
     * 新增导航
     * @param channels
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.ADD_CHANNEL_REQ)
    public int addTheChannel(Channels channels){
        return channelService.addTheChannel(channels);
    }


    /**
     * 获取一级标签列表
     * @param name
     * @param parentId
     * @param appType
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.FIRST_TAB_LIST_REQ)
    @ResponseBody
    public List<ChannelsVO> getFirstTabList(final String name, final Long parentId, final Integer appType){
        List<ChannelsVO> allChannelsVOList = channelService.getTabs(name,parentId,appType);
        List<ChannelsVO> channelsVOList = Lists.newArrayList();
        for(ChannelsVO channelsVO : allChannelsVOList){
            if(StringUtils.isEmpty(channelsVO.getAncestryTab())){
                channelsVOList.add(channelsVO);
            }
        }
        return channelsVOList;
    }

    /**
     * 获取二级标签列表
     * @param name
     * @param parentId
     * @param appType
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.SECOND_TAB_LIST_REQ)
    @ResponseBody
    public List<ChannelsVO> getSecondTabList(final String name, final Long parentId, final Integer appType){
        List<ChannelsVO> allChannelsVOList = channelService.getTabs(name,parentId,appType);
        List<ChannelsVO> channelsVOList = Lists.newArrayList();
        for(ChannelsVO channelsVO : allChannelsVOList){
            if(!StringUtils.isEmpty(channelsVO.getAncestryTab())){
                channelsVOList.add(channelsVO);
            }
        }
        return channelsVOList;
    }


    /**
     * 更新标签
     * @param editId
     * @param editChannelName
     * @param parentIdd
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.UPDATE_TAB_REQ)
    @ResponseBody
    public int updateTab(final Long editId, final String editChannelName, final Long parentIdd){
        Channels channels = new Channels();
        channels.setId(editId);
        channels.setName(editChannelName);
        channels.setParentId(parentIdd);
        channels.setUpdateDate(DateUtil.toString(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
        return channelService.updateChannels(channels);
    }










    /**
     * 新增导航
     * @param channelsVO
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_ADD_REQ, method = RequestMethod.POST)
    public Map<Object,Object> addChannel(final ChannelsVO channelsVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(channelsVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = channelService.addChannel(channelsVO);
                if(num > 0){
                    dataMap.put("code", 1);
                    dataMap.put("msg","新增成功");
                }else{
                    dataMap.put("code", 0);
                    dataMap.put("msg","新增失败");
                }
            }
        });

        return dataMap;
    }

    /**
     * 修改导航
     * @param channelsVO
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.CHANNEL_UPDATE_REQ, method = RequestMethod.POST)
    public Map<Object,Object> updateChannel(final ChannelsVO channelsVO){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {
                AssertsUtil.isNotNull(channelsVO, ErrorCodeEnum.SYSTEM_ANOMALY);
            }

            @Override
            public void handle() throws Exception {
                int num = channelService.updateChannel(channelsVO);
                if(num > 0){
                    dataMap.put("code", 1);
                    dataMap.put("msg","修改成功");
                }else{
                    dataMap.put("code", 0);
                    dataMap.put("msg","修改失败");
                }
            }
        });

        return dataMap;
    }


    /**
     * 获取一级标签列表
     * @return
     */
    @RequestMapping(value = ChannelMsgReq.FIRST_LEVEL_LABEL, method = RequestMethod.GET)
    public Map<Object,Object> firstLabelPage(final String name, final Integer status, final Integer page, final Integer limit){

        final Map<Object,Object> dataMap = Maps.newHashMap();
        this.excute(dataMap, null, new ControllerCallback() {
            @Override
            public void check() throws ZhiShunException {

            }

            @Override
            public void handle() throws Exception {
                PageRequest pageRequest = new PageRequest();
                pageRequest.setPageNo(page);
                pageRequest.setPageSize(limit);
                ChannelsVO channelsVO = new ChannelsVO();
                channelsVO.setName(name);
                channelsVO.setStatus(status);
                Page<Channels> channelsPage = channelService.listChannelsPage(channelsVO, pageRequest);
                dataMap.put("data", channelsPage.getRows());
                dataMap.put("count", channelsPage.getTotal());
            }
        });

        return dataMap;
    }


}
