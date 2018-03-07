/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.web.home.controller.user;

import com.zhishun.zaotoutiao.biz.service.IExchangeRateService;
import com.zhishun.zaotoutiao.core.model.entity.ExchangeRate;
import com.zhishun.zaotoutiao.dal.mapper.ExchangeRateMapper;
import com.zhishun.zaotoutiao.web.home.constant.request.ExchangeMsgReq;
import com.zhishun.zaotoutiao.web.home.constant.view.ExchangeMsgView;
import com.zhishun.zaotoutiao.web.home.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * @author BugMan
 * @version $Id: ExchangeController, v0.1 2018年03月05日 14:08BugMan Exp $
 */
@Controller
public class ExchangeController extends BaseController{

    @Autowired
    private IExchangeRateService iExchangeRateService;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;


    /**
     * 获取当前汇率及修改页面
     * @return
     */
    @RequestMapping(value = ExchangeMsgReq.EXCHANGE_RATE_UPDATED)
    public ModelAndView exchangeRate(){
        ModelAndView mv = new ModelAndView(ExchangeMsgView.EXCHANGE_RATE_UPDATED_VIEW);
        BigDecimal goldToMoney = iExchangeRateService.getGoldToMoney().getGoldToMoney();
        String str = goldToMoney.toString();
        mv.addObject("goldToMoney", str);
        return mv;
    }

    /**
     * 修改汇率
     * @param exchange
     * @return
     */
    @RequestMapping(value = ExchangeMsgReq.TO_CHANGE_EXCHANGE,method = RequestMethod.POST)
    @ResponseBody
    public int changeExchange(String exchange){
        if(null !=exchange && !"".equals(exchange)) {
            BigDecimal ex = new BigDecimal(exchange);
            if (0 < ex.doubleValue() && 0.025 > ex.doubleValue()) {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setGoldToMoney(ex);
                exchangeRate.setId(1);
                int res = exchangeRateMapper.updateByPrimaryKeySelective(exchangeRate);
                return res;
            }
        }
        return 0;
    }
}
