/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IGoldRecordService;
import com.zhishun.zaotoutiao.core.model.entity.UserGoldRecord;
import com.zhishun.zaotoutiao.dal.mapper.UserGoldRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: GoldRecordServiceImpl, v0.1 2018年02月28日 19:29闫迎军(YanYingJun) Exp $
 */

@Service
public class GoldRecordServiceImpl implements IGoldRecordService{

    @Autowired
    private UserGoldRecordMapper userGoldRecordMapper;

    @Override
    public int getOpenGoldToday(Long userId, int source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
        String zeroDay = sdf.format(new Date());
        Map map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("source", source);
        map.put("zeroDay", zeroDay);
        return userGoldRecordMapper.getOpenGoldToday(map);
    }

    @Override
    public Map leadTime(Long userId) {
        return userGoldRecordMapper.leadTime(userId);
    }

    @Override
    public Map leadTimeTwo(Long userId) {
        return userGoldRecordMapper.leadTimeTwo(userId);
    }

    @Override
    public List<UserGoldRecord> listUserGoldRecord(Long userId) {
        return userGoldRecordMapper.listUserGoldRecord(userId);
    }
}
