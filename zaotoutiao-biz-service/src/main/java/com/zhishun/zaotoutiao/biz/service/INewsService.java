package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.entity.*;
import com.zhishun.zaotoutiao.core.model.vo.InfosVO;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: INewsService, v0.1 2018年02月26日 19:17BugMan Exp $
 */

public interface INewsService {

    /**
     * 根据类型、channelId查找视频或新闻
     * @param type
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<InfosVO> getInfosByType(String type, int channelId, PageRequest pageRequest);

    /**
     * 获取新闻分类列表
     * @return
     */
    List<Channels> listChannels();

    /**
     * 根据infoId 获取新闻
     * @param infoId
     */
    Content getNewsByInfoId(String infoId);
    /**
     * 获取用户历史记录
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<UserReadRecord> listLookRecordPage(Long userId, PageRequest pageRequest);

    /**
     * 根据新闻ID获取新闻信息
     * @param infoId
     * @return
     */
    Infos getInfosByMap(Long infoId);

    /**
     * 获取新闻详情
     * @param name
     * @return
     */
    StaticHtml getStaticHtml(String name);

    /**
     * 根据关键词搜索新闻
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<InfosVO> searchNewsByKeyword(String keyword, int pageNo, int pageSize);

    /**
     * 获取收藏列表
     * @param infosType
     * @param userId
     * @param pageNo
     * @return
     */
    List<UserCollect> getCollectList(String infosType, int userId, PageRequest pageRequest);

    /**
     * 获取24小时热文
     * @return
     */
    List<InfosVO> List24HoursInfos();
}
