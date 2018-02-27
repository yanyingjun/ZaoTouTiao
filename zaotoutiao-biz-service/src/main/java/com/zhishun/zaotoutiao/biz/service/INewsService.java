package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.common.base.pagination.Page;
import com.zhishun.zaotoutiao.common.base.pagination.PageRequest;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: INewsService, v0.1 2018年02月26日 19:17BugMan Exp $
 */

public interface INewsService {

    /**
     * 根据类型查找视频或新闻
     * @param type
     * @param channelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<InfosVo> getInfosByType(String type, int channelId, int pageNo, int pageSize);

    /**
     * 获取用户历史记录
     * @param userId
     * @param pageRequest
     * @return
     */
    Page<InfosVo> listLookRecordPage(Long userId, PageRequest pageRequest);
}
