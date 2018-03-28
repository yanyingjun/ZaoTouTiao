package com.zhishun.zaotoutiao.core.model.enums;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PlatformEnum, v0.1 2018年03月02日 15:53闫迎军(YanYingJun) Exp $
 */
public enum PlatformEnum implements ValueEnum<Integer>{
    /**
     * 安卓
     */
    ANDROID(1, "android"),

    /**
     * IOS
     */
    IOS(2, "ios");

    PlatformEnum(Integer value, String name){
        this.name = name;
        this.value = value;
    }

    private Integer value;

    private String name;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
