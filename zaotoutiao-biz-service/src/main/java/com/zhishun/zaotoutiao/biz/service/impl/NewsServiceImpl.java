/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.core.model.entity.StaticHtml;
import com.zhishun.zaotoutiao.core.model.entity.Channels;
import com.zhishun.zaotoutiao.core.model.entity.Content;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.dal.mapper.ChannelsMapper;
import com.zhishun.zaotoutiao.dal.mapper.ContentMapper;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import com.zhishun.zaotoutiao.dal.mapper.StaticHtmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: NewsServiceImpl, v0.1 2018年02月26日 19:17BugMan Exp $
 */

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private InfosMapper infosMapper;

    @Autowired
    private StaticHtmlMapper staticHtmlMapper;

    @Autowired
    private ChannelsMapper channelsMapper;

    @Autowired
    private ContentMapper contentMapper;


    /**
     * 根据类型级分类ID返回新闻列表信息
     * @param type
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<InfosVo> getInfosByType(String type, int channelId, int pageNo, int pageSize) {
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        Map map = Maps.newHashMap();
        map.put("type",type);
        map.put("channelId",channelId);
        map.put("endNo",endNo);
        map.put("startNo",startNo);
        List<InfosVo> voList= infosMapper.selectInfosByType(map);
        return voList;
    }

    /**
     * 获得新闻分类列表信息
     * @return
     */
    @Override
    public List<Channels> listChannels() {
        return channelsMapper.listChannels();
    }


    @Override
    public Content getNewsByInfoId(String infoId) {
        return contentMapper.selectByInfoId(infoId);
    }

    @Override
    public Page<InfosVo> listLookRecordPage(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int count = infosMapper.countLookRecord(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<InfosVo> list = infosMapper.listLookRecordPage(map);
        return PageBuilder.buildPage(pageRequest, list, count);
    }

    @Override
    public Infos getInfosByMap(Long infoId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("infoId", infoId);
        return infosMapper.getInfosByMap(map);
    }

    @Override
    public StaticHtml getStaticHtml(String name) {
        return staticHtmlMapper.getStaticHtml(name);
    }

    /**
     * 根据关键词搜索新闻
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<InfosVo> searchNewsByKeyword(String keyword, int pageNo, int pageSize) {
        Map<String,Object> map = Maps.newHashMap();
        keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        map.put("keyword",keyword);
        map.put("startNo",startNo);
        map.put("endNo",endNo);
        List<InfosVo> infosVoList = infosMapper.searchNewsByKeyword(map);
        return infosVoList;
    }

    /**
     * 获取收藏列表
     * @param infoType
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<InfosVo> getCollectList(String infoType, int userId, int pageNo, int pageSize) {
        Map<String,Object> map = Maps.newHashMap();
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        map.put("infoType",infoType);
        map.put("userId",userId);
        map.put("startNo",startNo);
        map.put("endNo",endNo);
        List<InfosVo> infosVoList = infosMapper.getCollectList(map);
        return infosVoList;

    }

    @Override
    public List<InfosVo> List24HoursInfos() {
        return infosMapper.List24HoursInfos();
    }


}
