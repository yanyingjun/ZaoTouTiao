/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IInfosService;
import com.zhishun.zaotoutiao.core.model.entity.Infos;
import com.zhishun.zaotoutiao.dal.mapper.InfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: InfosServiceImpl, v0.1 2018年02月25日 16:55BugMan Exp $
 */

@Service
public class InfosServiceImpl implements IInfosService {

    @Autowired
    private InfosMapper infosMapper;

    @Override
    public List<Infos> getInfosByType(String type, int pageNo, int pageSize) {
        int startNo = (pageNo-1) * pageSize;
        int endNo = pageNo * pageSize;
        Map map = Maps.newHashMap();
        map.put("type",type);
        map.put("startNo",startNo);
        map.put("endNo",endNo);
        List<Infos> infosList= infosMapper.selectInfosByType(map);
        return infosList;

        //PageRequest pageRequest = new PageRequest(pageNo,pageSize);
        //int total = infosMapper.selectCountByType(type);
        //Page page= PageBean.buildPage(pageRequest,infosList,total);
        //return page;

    }
}
