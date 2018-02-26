package com.zhishun.zaotoutiao.api.home.callback;


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