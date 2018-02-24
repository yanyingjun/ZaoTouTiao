package com.zhishun.zaotoutiao.common.client.constant.request;

/**
 * 微贷接口方法名称 - 请求常量
 *
 * @author 孟小东
 * @version $Id;WeiDaiMethodMsgReq,V0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年 11月 03日 14:49 孟小东 Exp $
 */
public class WeiDaiMethodMsgReq {

    /**
     * 微贷接口url前缀
     */
    public static final String PREFIX = "assetplatform/shared/api";

    /**
     * 委托开户接口
     */
    public static final String ENTRUST_ACCOUNT = "/openAccountAgent";

    /**
     * 开户查询接口
     */
    public static final String ACCOUNT_INQUIRY = "/queryAccount";

    /**
     * 发标接口
     */
    public static final String ISSUING = "/tender";

    /**
     * 发标查询接口
     */
    public static final String ISSUING_THE_QUERY = "/queryTender";
}
