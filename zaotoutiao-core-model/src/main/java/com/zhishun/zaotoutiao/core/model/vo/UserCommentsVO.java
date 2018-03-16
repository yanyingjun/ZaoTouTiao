/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.UserComments;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: UserCommentsVO, v0.1 2018年02月26日 18:24闫迎军(YanYingJun) Exp $
 */
public class UserCommentsVO extends UserComments{

    /**
     * 文章/视频标题
     */
    private String title;

    /**
     * 用户昵称
     */
    private String nickName;

    //自己是否点赞
    private boolean isMyLike;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Getter method for property <tt>isMyLike</tt>.
     *
     * @return property value of isMyLike
     */
    public boolean isMyLike() {
        return isMyLike;
    }

    /**
     * Setter method for property <tt>isMyLike</tt>.
     *
     * @param isMyLike value to be assigned to property isMyLike
     */
    public void setMyLike(boolean myLike) {
        isMyLike = myLike;
    }
}
