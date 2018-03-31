/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageBuilder;
import com.zhishun.zaotoutiao.common.base.pagination.PageNoAndSize;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;
import com.zhishun.zaotoutiao.dal.mapper.*;
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

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Autowired
    private UserReadRecordMapper userReadRecordMapper;


    /**
     * 根据类型级分类ID返回新闻列表信息
     * @param type
     * @param channelId
     * @param pageRequest
     * @return
     */
    @Override
    public List<InfosVO> getInfosByType(String type, int channelId,PageRequest pageRequest) {
        Map map = Maps.newHashMap();
        map.put("type",type);
        map.put("channelId",channelId);
        map.put("endNo",pageRequest.getOffset());
        map.put("startNo", pageRequest.getPageSize());
        List<InfosVO> voList= infosMapper.selectInfosByType(map);
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
    public Page<UserReadRecord> listLookRecordPage(Long userId, PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userId", userId);
        int count = userReadRecordMapper.countLookRecord(map);
        if(!StringUtils.isEmpty(pageRequest)){
            map.put("offset", pageRequest.getOffset());
            map.put("limit", pageRequest.getPageSize());
        }
        List<UserReadRecord> list = userReadRecordMapper.listLookRecordPage(map);
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
     * @param pageRequest
     * @return
     */
    @Override
    public List<InfosVO> searchNewsByKeyword(String keyword,PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        map.put("keyword",keyword);
        map.put("startNo",pageRequest.getOffset());
        map.put("endNo",pageRequest.getPageSize());
        List<InfosVO> infosVoList = infosMapper.searchNewsByKeyword(map);
        return infosVoList;
    }

    /**
     * 获取收藏列表
     * @param infosType
     * @param userId
     * @param pageRequest
     * @return
     */
    @Override
    public List<UserCollect> getCollectList(String infosType, int userId,PageRequest pageRequest) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("infosType",infosType);
        map.put("userId",userId);
        map.put("startNo",pageRequest.getOffset());
        map.put("endNo",pageRequest.getPageSize());
        List<UserCollect> infosVoList = userCollectMapper.getCollectList(map);
        return infosVoList;

    }

    @Override
    public List<InfosVO> List24HoursInfos() {
        return infosMapper.List24HoursInfos();
    }


}
