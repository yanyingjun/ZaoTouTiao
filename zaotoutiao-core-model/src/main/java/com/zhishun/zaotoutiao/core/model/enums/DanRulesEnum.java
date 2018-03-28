package com.zhishun.zaotoutiao.core.model.enums;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PlatformEnum, v0.1 2018年03月02日 15:53闫迎军(YanYingJun) Exp $
 */
public enum DanRulesEnum implements ValueEnum<String>{
    /**
     * 青铜
     */
    BRONZE("bronze", "青铜"),

    /**
     * 白银
     */
    SILVER("silver", "白银"),

    /**
     * 黄金
     */
    GOLD("gold", "黄金"),

    /**
     * 白金
     */
    PLATINUM("platinum", "白金"),

    /**
     * 钻石
     */
    DIAMONDS("diamonds", "钻石"),

    /**
     * 大师
     */
    MASTER("master", "大师"),

    /**
     * 王者
     */
    KING("king", "王者")
    ;

    DanRulesEnum(String value, String name){
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
