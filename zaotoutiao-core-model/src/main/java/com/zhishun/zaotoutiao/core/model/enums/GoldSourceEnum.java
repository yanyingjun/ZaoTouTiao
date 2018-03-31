package com.zhishun.zaotoutiao.core.model.enums;

import com.zhishun.zaotoutiao.common.base.core.ValueEnum;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: PlatformEnum, v0.1 2018年03月02日 15:53闫迎军(YanYingJun) Exp $
 */
public enum GoldSourceEnum implements ValueEnum<Integer>{
    /**
     * 注册登录
     */
    REGISTERED_LOGIN(0, "注册登录"),

    /**
     * 新闻阅读
     */
    NEWS_READING(1, "新闻阅读"),

    /**
     * 徒弟阅读进贡
     */
    APPRENTICE_READ_TRIBUTE(2, "徒弟阅读进贡"),

    /**
     * 签到
     */
    SIGN(3, "签到"),

    /**
     * 开宝箱
     */
    OPEN_TREASURE_CHEST(4, "开宝箱"),

    /**
     * 评论
     */
    COMMENT(5, "评论"),

    /**
     * 收徒
     */
    AN_APPRENTICE(6, "收徒"),

    /**
     * 金币转换零钱
     */
    CHANGE_OF_MONEY_BY_GOLD_COINS(7, "金币转换零钱"),

    /**
     * 新手阅读
     */
    NOVICE_READING(8, "新手阅读"),

    /**
     * 首次收徒
     */
    FOR_THE_FIRST_TIME_AN_APPRENTICE(9, "首次收徒"),

    /**
     * 分享收徒
     */
    SHARE_AN_APPRENTICE(10, "分享收徒"),

    /**
     * 分享文章
     */
    SHARING_ARTICLES(11, "分享文章"),

    /**
     * 唤醒徒弟(师傅奖励)
     */
    Wake_up_the_apprentice(12, "唤醒徒弟(师傅奖励)"),

    /**
     * 被唤醒
     */
    BE_AWAKENED(13, "被唤醒"),

    /**
     * 晒收入
     */
    EXPOSURE_TO_INCOME(14, "晒收入")

    ;

    GoldSourceEnum(Integer value, String name){
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
