/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;

import java.util.Map;

/**
 * @author BugMan
 * @version $Id: NavigationVO, v0.1 2018年03月23日 15:47BugMan Exp $
 */

public class NavigationVO {

    /**
     * Id
     */
    private Long id;

    /**
     * channelId
     */
    private String channelId;

    /**
     * 父channelId
     */
    private String parentId;

    /**
     * 类型
     */
    private Integer appType;
    /**
     * 导航名
     */
    private String name;

    /**
     * 阅读量
     */
    private Long readNum;

    /**
     * 下一级标签（前三）
     */
    private String channelsTop3;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getChannelsTop3() {
        return channelsTop3;
    }

    public void setChannelsTop3(String channelsTop3) {
        this.channelsTop3 = channelsTop3;
    }
}
