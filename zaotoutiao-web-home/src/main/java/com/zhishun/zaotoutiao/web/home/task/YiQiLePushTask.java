package com.zhishun.zaotoutiao.web.home.task;

import org.springframework.stereotype.Component;

/**
 * 亿奇乐定时任务推送业务
 *
 */
@Component
public class YiQiLePushTask {

    /**
     * 亿奇乐业务-接口
     */
    /*@Autowired
    private YiQiLeBizService yiQiLeBizService;

    *//**
     * 同步订单还款状态
     *//*
    @Scheduled(cron = "0 0 1 * * ?")
    public void syncOrderRepayment() {
        yiQiLeBizService.syncOrderRepayment();
    }

    *//**
     * 逾期订单同步dd
     *//*
    @Scheduled(cron = "0 0 2 * * ?")
    public void syncOverdueOrders() {
        yiQiLeBizService.syncOverdueOrders();
    }*/

}
