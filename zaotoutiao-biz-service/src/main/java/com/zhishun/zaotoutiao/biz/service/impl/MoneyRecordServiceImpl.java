/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.google.common.collect.Maps;
import com.zhishun.zaotoutiao.biz.service.IMoneyRecordService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.vo.AllRankingVO;
import com.zhishun.zaotoutiao.core.model.vo.UserMoneyRecordVO;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMoneyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: MoneyRecordServiceImpl, v0.1 2018年02月28日 21:25闫迎军(YanYingJun) Exp $
 */
@Service
public class MoneyRecordServiceImpl implements IMoneyRecordService{

    @Autowired
    private UserMoneyRecordMapper userMoneyRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<AllRankingVO> allRankings() {
        List<AllRankingVO> list = userMoneyRecordMapper.allRankings();
        for(AllRankingVO vo : list){
            int sum = userMapper.getApprenticeSum(vo.getUserId());
            vo.setApprenticeSum(sum);
            User user = userMapper.selectByPrimaryKey(vo.getUserId());
            vo.setName(user.getNickName());
        }
        return list;
    }

    @Override
    public List<UserMoneyRecordVO> listMoneyCount(int channelId, String source, String type, String isNewAndOld, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(channelId) && channelId != 0){
            map.put("channelId", channelId);
        }
        if(!StringUtils.isEmpty(source)){
            map.put("source", source);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(isNewAndOld) && !isNewAndOld.equals("wholeUser")){
            map.put("isNewOrOld", isNewAndOld);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userMoneyRecordMapper.listMoneyCount(map);
    }

    @Override
    public List<UserMoneyRecordVO> listMoneyCountBySource(String isNewAndOld, String type, String startDate, String endDate) {
        Map map = Maps.newHashMap();
        if(!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if(!StringUtils.isEmpty(isNewAndOld) && !isNewAndOld.equals("wholeUser")){
            map.put("isNewOrOld", isNewAndOld);
        }
        if(!StringUtils.isEmpty(startDate)){
            map.put("startDate", startDate);
        }
        if(!StringUtils.isEmpty(endDate)){
            map.put("endDate", endDate);
        }
        return userMoneyRecordMapper.listMoneyCountBySource(map);
    }
}
