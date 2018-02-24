package com.zhishun.zaotoutiao.common.client.constant.request;

 /**
 * 考拉接口方法名称 - 请求常量
 *
 * @author 曹柏青
 * @version $Id: KaoLaMethodMsgReq, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年10月25日 11:27 曹柏青 Exp $
 */
public class KaoLaMethodMsgReq {

    /**
     * 考拉接口url前缀
     */
    public static final String PREFIX = "/api/";

    /**
     * 申请放款接口
     */
    public static final String LOAN_APPLY = "/loan/apply";

    /**
     * 查询单笔订单接口
     */
    public static final String LOAN_GET_ORDER_STAT = "/loan/getOrderStat/";

    /**
     * 正常还款接口
     */
    public static final String REPAY_NORMAL = "/repay/normal/";

     /**
      * 查询还款接口
      */
     public static final String REPAY_GET_RESULT = "/loan/getRepayResult/";
}
