/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

/**
 * 金币记录表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
import java.io.Serializable;
import java.math.BigDecimal;

public class UserGoldRecord implements Serializable {

    private static final long serialVersionUID = 3407569561378284714L;
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID（自己阅读时，表示当前用户id）（徒弟进贡时，表示父类用户id）
     */
    private Long userId;

    /**
     * 类型（增加：1，减少：0）
     */
    private Byte type;

    /**
     * 增加或减少的金币
     */
    private Long gold;

    /**
     * 来源去向（0:注册登录, 1:新闻阅读, 2:徒弟阅读进贡, 3:签到, 4:开宝箱, 5:评论,6:收徒,7:金币转换零钱, 8:新手阅读，9首次收徒，10分享收徒,  11分享文章， 12，唤醒徒弟(师傅奖励) ，13，被唤醒(徒弟奖励)，14，晒收入）
     */
    private Integer source;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 徒弟id（徒弟进贡时使用，表示当前用户id）(被唤醒时表示师傅id)
     */
    private Long apprenticeId;

    /**
     *
     */
    private String infoId;

    /**
     * 分享ID
     */
    private Long shareId;

    /**
     * 构造方法
     */
    public UserGoldRecord(Long id, Long userId, Byte type, Long gold, Integer source, String createDate, Long apprenticeId, String infoId, Long shareId) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.gold = gold;
        this.source = source;
        this.createDate = createDate;
        this.apprenticeId = apprenticeId;
        this.infoId = infoId;
        this.shareId = shareId;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public Byte getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>gold</tt>.
     *
     * @return property value of gold
     */
    public Long getGold() {
        return gold;
    }

    /**
     * Setter method for property <tt>gold</tt>.
     *
     * @param gold value to be assigned to property gold
     */
    public void setGold(Long gold) {
        this.gold = gold;
    }

    /**
     * Getter method for property <tt>source</tt>.
     *
     * @return property value of source
     */
    public Integer getSource() {
        return source;
    }

    /**
     * Setter method for property <tt>source</tt>.
     *
     * @param source value to be assigned to property source
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * Getter method for property <tt>createDate</tt>.
     *
     * @return property value of createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for property <tt>createDate</tt>.
     *
     * @param createDate value to be assigned to property createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter method for property <tt>apprenticeId</tt>.
     *
     * @return property value of apprenticeId
     */
    public Long getApprenticeId() {
        return apprenticeId;
    }

    /**
     * Setter method for property <tt>apprenticeId</tt>.
     *
     * @param apprenticeId value to be assigned to property apprenticeId
     */
    public void setApprenticeId(Long apprenticeId) {
        this.apprenticeId = apprenticeId;
    }

    /**
     * Getter method for property <tt>infoId</tt>.
     *
     * @return property value of infoId
     */
    public String getInfoId() {
        return infoId;
    }

    /**
     * Setter method for property <tt>infoId</tt>.
     *
     * @param infoId value to be assigned to property infoId
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    /**
     * Getter method for property <tt>shareId</tt>.
     *
     * @return property value of shareId
     */
    public Long getShareId() {
        return shareId;
    }

    /**
     * Setter method for property <tt>shareId</tt>.
     *
     * @param shareId value to be assigned to property shareId
     */
    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }
}