/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2012-2016
 */
package com.zhishun.zaotoutiao.web.home.callback;


import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;


/**
 * 执行模板接口
 *
 */
public interface ControllerCallback {

    /**
     * 参数检查
     *
     * @throws ZhiShunException
     */
    void check() throws ZhiShunException;

    /**
     * 主体处理
     *
     * @throws Exception
     */
    void handle() throws Exception;

}