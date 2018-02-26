/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.Infos;

import java.io.Serializable;
import java.util.Date;

/**
 * @author BugMan
 * @version $Id: InfosVo, v0.1 2018年02月26日 13:37BugMan Exp $
 * 返回的视频新闻列表视图
 */


public class InfosVo extends Infos{

    private Date orderTime;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
