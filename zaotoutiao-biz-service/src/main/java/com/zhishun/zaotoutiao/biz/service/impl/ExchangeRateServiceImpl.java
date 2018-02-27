/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.biz.service.impl;

import com.zhishun.zaotoutiao.biz.service.IExchangeRateService;
import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;
import com.zhishun.zaotoutiao.dal.mapper.ExchangeRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ExchangeRateServiceImpl, v0.1 2018年02月26日 19:55闫迎军(YanYingJun) Exp $
 */
@Service
public class ExchangeRateServiceImpl implements IExchangeRateService{

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Override
    public ExchangeRate getGoldToMoney() {
        return exchangeRateMapper.getExchangeRate();
    }
}
