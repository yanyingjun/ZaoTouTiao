/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserReadRecord;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserReadRecordVO, v0.1 2018年03月30日 19:05闫迎军(YanYingJun) Exp $
 */
public class UserReadRecordVO extends UserReadRecord{

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞人数
     */
    private Integer likes;

    /**
     * 是否点赞
     */
    private Integer isLike;

    /**
     * 地址
     */
    private String address;

    /**
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter method for property <tt>likes</tt>.
     *
     * @return property value of likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * Setter method for property <tt>likes</tt>.
     *
     * @param likes value to be assigned to property likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * Getter method for property <tt>isLike</tt>.
     *
     * @return property value of isLike
     */
    public Integer getIsLike() {
        return isLike;
    }

    /**
     * Setter method for property <tt>isLike</tt>.
     *
     * @param isLike value to be assigned to property isLike
     */
    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    /**
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address value to be assigned to property address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
