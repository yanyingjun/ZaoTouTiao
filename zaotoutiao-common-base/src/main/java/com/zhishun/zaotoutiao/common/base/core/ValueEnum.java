package com.zhishun.zaotoutiao.common.base.core;

/**
 * 针对键为String值为任意Object类型的枚举接口父类
 * @author 闫迎军(YanYingJun)
 * @version $Id: ValueEnum, v0.1 2018年03月02日 15:51闫迎军(YanYingJun) Exp $
 */
public interface ValueEnum<V> {

    /**
     * 获取值
     *
     * @return Object
     */
    public V getValue();

    /**
     * 获取名称
     *
     * @return String
     */
    public String getName();
}
