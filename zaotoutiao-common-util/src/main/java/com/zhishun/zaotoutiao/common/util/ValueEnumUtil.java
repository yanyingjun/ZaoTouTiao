/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

import java.util.*;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ValueEnumUtil, v0.1 2018年03月02日 15:46闫迎军(YanYingJun) Exp $
 */
public class ValueEnumUtil {

    /**
     * 通过字典枚举获取数据字典集合
     *
     * @param enumClass
     *            枚举 class
     * @param ignoreValue
     *            忽略值
     *
     * @return List
     */
    public static List<Map<Object, Object>> getEnums(
            Class<? extends Enum<? extends ValueEnum<?>>> enumClass) {
        List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
        Enum<?>[] values = enumClass.getEnumConstants();
        Map<Object, Object> map = null;
        for (Enum<?> o : values) {
            ValueEnum<?> ve = (ValueEnum<?>) o;
            Object value = ve.getValue();

            map = new LinkedHashMap<Object, Object>();
            map.put("value", value);
            map.put("key", ve.getName());
            result.add(map);

        }
        return result;
    }



    /**
     * 返回枚举Map
     *     add by luan.xw  2015/7/29
     * @param enumClass
     * @return
     */
    public static Map<Object, Object> getMapEnums(Class<? extends Enum<? extends ValueEnum<?>>> enumClass){
        Map<Object, Object> map=new HashMap<Object, Object>();
        Enum<?>[] values = enumClass.getEnumConstants();
        for (Enum<?> o : values) {
            ValueEnum<?> ve = (ValueEnum<?>) o;
            Object value = ve.getValue();
            map.put(value,ve.getName());
        }
        return map;
    }
}
