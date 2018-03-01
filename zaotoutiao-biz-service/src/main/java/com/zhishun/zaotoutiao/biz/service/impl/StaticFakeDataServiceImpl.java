/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IStaticFakeDataService;
import com.zhishun.zaotoutiao.dal.mapper.StaticFakeDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: StaticFakeDataServiceImpl, v0.1 2018年02月28日 21:20闫迎军(YanYingJun) Exp $
 */
@Service
public class StaticFakeDataServiceImpl implements IStaticFakeDataService{

    @Autowired
    private StaticFakeDataMapper staticFakeDataMapper;

    @Override
    public List<Map> weekRankings() {
        return staticFakeDataMapper.weekRankings();
    }
}
