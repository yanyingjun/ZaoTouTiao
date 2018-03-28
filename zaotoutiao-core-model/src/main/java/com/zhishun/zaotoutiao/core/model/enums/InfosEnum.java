package com.zhishun.zaotoutiao.core.model.enums;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PlatformEnum, v0.1 2018年03月02日 15:53闫迎军(YanYingJun) Exp $
 */
public enum InfosEnum implements ValueEnum<String>{
    /**
     * 安卓
     */
    NEWS("news", "新闻"),

    /**
     * IOS
     */
    VIDEO("video", "视频");

    InfosEnum(String value, String name){
        this.name = name;
        this.value = value;
    }

    private String value;

    private String name;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
