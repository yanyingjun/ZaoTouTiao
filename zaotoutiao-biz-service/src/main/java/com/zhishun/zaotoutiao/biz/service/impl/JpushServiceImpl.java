/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IJpushService;
import com.zhishun.zaotoutiao.core.model.entity.UserJpush;
import com.zhishun.zaotoutiao.dal.mapper.UserJpushMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: JpushServiceImpl, v0.1 2018年03月01日 12:22闫迎军(YanYingJun) Exp $
 */
@Service
public class JpushServiceImpl implements IJpushService{

    @Autowired
    private UserJpushMapper userJpushMapper;

    @Override
    public UserJpush getUserJpush(Long userId, String infoId) {
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        if(!StringUtils.isEmpty(infoId)){
            map.put("infoId", infoId);
        }
        return userJpushMapper.getUserJpush(map);
    }

    @Override
    public int isToday(String time) {
        return userJpushMapper.isToday(time);
    }
}
