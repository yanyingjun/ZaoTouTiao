/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.vo;

import com.zhishun.zaotoutiao.core.model.entity.Channels;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: ChannelsVO, v0.1 2018年02月25日 15:39闫迎军(YanYingJun) Exp $
 */
public class ChannelsVO extends Channels{

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 类型的名称（文章或视频）
     */
    private String typeName;

    /**
     * 导航标签（如果已经是导航了为空）
     */
    private String ancestryTab;

    /**
     * 一级标签
     */
    private String parentTab;

    public String getParentTab() {
        return parentTab;
    }

    public void setParentTab(String parentTab) {
        this.parentTab = parentTab;
    }

    public String getAncestryTab() {
        return ancestryTab;
    }

    public void setAncestryTab(String ancestryTab) {
        this.ancestryTab = ancestryTab;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
