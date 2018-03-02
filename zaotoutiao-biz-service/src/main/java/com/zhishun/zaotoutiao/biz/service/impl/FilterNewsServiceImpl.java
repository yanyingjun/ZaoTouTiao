/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IFilterNewsService;
import com.zhishun.zaotoutiao.core.model.entity.UserFilterNews;
import com.zhishun.zaotoutiao.dal.mapper.UserFilterNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: FilterNewsServiceImpl, v0.1 2018年03月01日 13:33闫迎军(YanYingJun) Exp $
 */
@Service
public class FilterNewsServiceImpl implements IFilterNewsService{

    @Autowired
    private UserFilterNewsMapper userFilterNewsMapper;

    @Override
    public int countUserFilterNewsByParam(Long userId, String infoId, String type) {
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("infoId", infoId);
        map.put("type", type);
        return userFilterNewsMapper.countUserFilterNewsByParam(map);
    }

    @Override
    public int addUserFilterNews(Long userId, String infoId, String type, String content) {
        UserFilterNews userFilterNews = new UserFilterNews();
        userFilterNews.setUserId(userId);
        userFilterNews.setInfoId(infoId);
        userFilterNews.setType(type);
        userFilterNews.setContent(content);
        return userFilterNewsMapper.insertSelective(userFilterNews);
    }
}
