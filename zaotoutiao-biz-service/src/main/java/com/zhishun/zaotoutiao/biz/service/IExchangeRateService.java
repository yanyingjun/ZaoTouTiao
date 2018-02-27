package com.zhishun.zaotoutiao.biz.service;

import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: IExchangeRateService, v0.1 2018年02月26日 19:54闫迎军(YanYingJun) Exp $
 */
public interface IExchangeRateService {

    /**
     * 获取金币零钱汇率
     * @return
     */
    ExchangeRate getGoldToMoney();
}
