/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IMoneyRecordService;
import com.zhishun.zaotoutiao.core.model.entity.User;
import com.zhishun.zaotoutiao.core.model.vo.AllRankingVO;
import com.zhishun.zaotoutiao.dal.mapper.UserMapper;
import com.zhishun.zaotoutiao.dal.mapper.UserMoneyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
