package com.zhishun.zaotoutiao.core.model.enums;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

/**
 * 渠道分类
 *
 */
public enum ChannelEnum implements ValueEnum<Integer>{

    /**
     * 视频
     */
    VIDEO(0, "视频"),

    /**
     * 新闻
     */
    NEWS(1, "新闻"),

    /**
     * 广告
     */
    AD(2, "广告");

    /**
     * 渠道编码
     */
    private Integer value;

    /**
     * 渠道名称
     */
    private String name;

    ChannelEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static ChannelEnum getChannelEnumByCode(String value) {
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.getValue().equals(value)) {
                return channelEnum;
            }
        }
        return null;
    }

}
