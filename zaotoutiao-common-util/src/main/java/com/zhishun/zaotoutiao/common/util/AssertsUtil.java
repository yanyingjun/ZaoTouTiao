/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2012-2016
 */
package com.zhishun.zaotoutiao.common.util;

import com.zhishun.zaotoutiao.core.model.enums.ErrorCodeEnum;
import com.zhishun.zaotoutiao.core.model.exception.ZhiShunException;
import jodd.util.StringUtil;

import java.util.List;

/**
 * 断言工具类
 * 
 * @author 侯俊昌（HouJunChang）
 * @version $Id: AssertsUtil.java, v 0.1 2016年3月7日 下午8:32:46 侯俊昌（HouJunChang） Exp $
 */
public class AssertsUtil {

    /**
     * 字符串为空校验
     * 
     * @param value 校验字符串
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isNotBlank(String value, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验objValue是否为空，为空则抛出异常
        if (StringUtil.isBlank(value)) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 对象为空校验
     * 
     * @param obj 校验对象
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isNotNull(Object obj, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验Object是否为null，为null则抛出异常
        if (null == obj) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 参数为true校验
     * 
     * @param value 校验布尔值
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isTrue(boolean value, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验value是否为true，为true则抛出异常
        if (value == false) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 参数为false校验
     * 
     * @param value 校验布尔值
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isFalse(boolean value, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验value是否为false，为false则抛出异常
        if (value == true) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 参数为0校验
     * 
     * @param value 校验参数
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isZero(int value, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验value是否为0，为0则抛出异常
        if (value > 0) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 参数为0校验
     * 
     * @param value 校验参数
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException 异常对象
     */
    public static void isNotZero(long value, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验value是否为0，为0则抛出异常
        if (value == 0) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }

    /**
     * 校验集合是否为空
     * 
     * @param list 校验集合
     * @param errorCodeEnum 错误枚举
     * @throws ZhiShunException
     */
    @SuppressWarnings("rawtypes")
    public static void isNotEmpty(List list, ErrorCodeEnum errorCodeEnum) throws ZhiShunException {

        // 校验List是否为null，为null则抛出异常
        if (list == null || list.isEmpty()) {
            throw new ZhiShunException(errorCodeEnum);
        }
    }


}