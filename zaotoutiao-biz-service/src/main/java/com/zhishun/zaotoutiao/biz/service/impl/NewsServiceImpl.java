/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.INewsService;
import com.zhishun.zaotoutiao.core.model.vo.InfosVo;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
