package com.zhishun.zaotoutiao.core.model.enums;

/**
 * 三方渠道枚举类
 *
 */
public enum ChannelEnum {

    /**
     * 亿奇乐
     */
    YI_QI_LE("1", "亿奇乐"),

    /**
     * 考拉
     */
    KAO_LA("2", "考拉"),

    /**
     * 微贷
     */
    WEI_DAI("3", "微贷"),

    /**
     * 国美
     */
    GUO_MEI("4", "国美");

    /**
     * 渠道编码
     */
    private String code;

    /**
     * 渠道名称
     */
    private String name;

    ChannelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static ChannelEnum getChannelEnumByCode(String code) {
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.getCode().equals(code)) {
                return channelEnum;
            }
        }
        return null;
    }

}
