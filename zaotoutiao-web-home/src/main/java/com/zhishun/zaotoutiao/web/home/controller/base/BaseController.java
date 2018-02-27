/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.web.home.controller.base;

import com.zhishun.zaotoutiao.common.util.LoggerUtils;
import com.zhishun.zaotoutiao.core.model.constant.CommonConstant;
import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import com.zhishun.zaotoutiao.web.home.callback.ControllerCallback;
import com.zhishun.zaotoutiao.web.home.result.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: BaseController, v0.1 2018年02月06日 20:25闫迎军(YanYingJun) Exp $
 */

@Controller
public abstract class BaseController {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 转换成Map
     *
     * @param messageResult 执行信息结果
     * @return Map
     */
    public static Map<String, String> toMap(MessageResult messageResult) {

        // 初始化Map对象
        Map<String, String> dataMap = new HashMap<String, String>(4);

        // 封装参数
        dataMap.put("appName", messageResult.getAppName());
        dataMap.put("success", messageResult.isSuccess() + "");
        dataMap.put("msg", messageResult.getErrorMessage() + "<" + messageResult.getErrorCode() + ">");
        dataMap.put("status", messageResult.getStatus());

        return dataMap;
    }

    /**
     * 执行模板（返回模型ModelMap）
     *
     * @param modelMap
     * @param request  服务请求
     * @param callback 模板接口
     */
    public void excute(ModelMap modelMap, HttpServletRequest request, ControllerCallback callback) {

        // 清楚Map值
        modelMap.clear();

        // 初始化结果
        MessageResult messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, true,
                CommonConstant.SUCCESS, ErrorCodeEnum.SUCCESS.getErrorCode(),
                ErrorCodeEnum.SUCCESS.getErrorMessage());

        try {

            // 第一步：参数校验
            callback.check();

            // 第三步：主体处理
            callback.handle();

        } catch (ZhiShunException e) {

            // 设置结果值
            messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, false,
                    CommonConstant.ERROR, e.getErrorCodeEnum().getErrorCode(), e.getErrorCodeEnum()
                    .getErrorMessage());

            // 打印日志
            LoggerUtils.error(LOGGER, e.getErrorCodeEnum().getErrorMessage());

        } catch (Exception e) {

            // 设置结果值
            messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, false,
                    CommonConstant.ERROR, ErrorCodeEnum.SYSTEM_ANOMALY.getErrorCode(),
                    ErrorCodeEnum.SYSTEM_ANOMALY.getErrorMessage());

            // 打印日志
            LoggerUtils.error(LOGGER, ErrorCodeEnum.SYSTEM_ANOMALY.getErrorMessage() + "，错误详情：" + e.getMessage());

        } finally {

            // 结果存储
            modelMap.put("result", toMap(messageResult));
        }

    }

    /**
     * 执行模板（返回集合Map）
     *
     * @param dataMap       相应请求
     * @param errorCodeEnum 错误枚举
     * @param callback      模板接口
     */
    public void excute(Map<Object, Object> dataMap, ErrorCodeEnum errorCodeEnum, ControllerCallback callback) {

        // 清楚Map值
        dataMap.clear();

        // 初始化结果
        MessageResult messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, true,
                CommonConstant.SUCCESS, ErrorCodeEnum.SUCCESS.getErrorCode(),
                ErrorCodeEnum.SUCCESS.getErrorMessage());

        if (null != errorCodeEnum) {
            messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, true,
                    CommonConstant.SUCCESS, errorCodeEnum.getErrorCode(),
                    errorCodeEnum.getErrorMessage());
        }

        try {

            // 第一步：参数校验
            callback.check();

            // 第三步：主体处理
            callback.handle();

        } catch (ZhiShunException e) {

            // 设置结果值
            if(e.getErrorCodeEnum() != null) {
                messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, false,
                        CommonConstant.ERROR, e.getErrorCodeEnum().getErrorCode(), e.getErrorCodeEnum()
                        .getErrorMessage());

                // 打印日志
                LoggerUtils.error(LOGGER, e.getErrorCodeEnum().getErrorMessage());
            } else {
                messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, false,
                        CommonConstant.ERROR, CommonConstant.COMMON_ERROR_CODE, e.getErrMsg());

                // 打印日志
                LoggerUtils.error(LOGGER, e.getErrMsg());
            }

        } catch (Exception e) {

            // 设置结果值
            messageResult = new MessageResult(CommonConstant.SYSTEM_NAME, false,
                    CommonConstant.ERROR, ErrorCodeEnum.SYSTEM_ANOMALY.getErrorCode(),
                    ErrorCodeEnum.SYSTEM_ANOMALY.getErrorMessage());

            // 打印日志
            LoggerUtils.error(LOGGER,  ErrorCodeEnum.SYSTEM_ANOMALY.getErrorMessage() + "，错误详情：" + e.getMessage());
        } finally {

            // 结果存储
            dataMap.put("result", toMap(messageResult));
        }

    }
}
