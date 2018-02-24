package com.zhishun.zaotoutiao.common.client.constant.request;

/**
 * 亿奇乐接口方法名称 - 请求常量
 *
 * @author 朱思雷
 * @version $Id: MethodMsgReq, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月07日 15:53
 */
public class YqlMethodMsgReq {

    /**
     * 获取接口授权码
     */
    public static final String GET_ACCESS_TOKEN="auth/getAccessToken";

    /**
     * 获取用户登录授权码
     */
    public static final String GET_USER_TOKEN = "user/getUserToken";

    /**
     * 同步用户数据
     */
    public static final String SYNC_CREDIT = "user/syncCredit";

    /**
     * 订单同步
     */
    public static final String CREATE_ORDER = "user/createOrder";

    /**
     * 逾期订单同步
     */
    public static final String SYNC_OVERDUE_ORDERS = "user/syncOverdueOrders";

    /**
     * 同步订单还款状态
     */
    public static final String SYNC_ORDER_REPAYMENT = "merchant/syncOrderRepayment";

    /**
     * 放款申请
     */
    public static final String REQPAYMENT = "merchant/reqPayment";

    /**
     * 放款查询
     */
    public static final String QUERY_PAYMENT = "merchant/queryPayment";

    /**
     * 放款回调
     */
    public static final String YQL_LOAN_CALLBACK = "/yql/loan/callback";
}
