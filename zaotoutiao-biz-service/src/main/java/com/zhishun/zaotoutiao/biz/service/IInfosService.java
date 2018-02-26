package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.Infos;

import java.util.List;

/**
 * @author BugMan
 * @version $Id: IInfosService, v0.1 2018年02月25日 16:40BugMan Exp $
 */

public interface IInfosService {

    /**
     * 根据类型查找视频或新闻
     * @param type
     * @return
     */
    List<Infos> getInfosByType(String type, int pageNo, int pageSize);
}
