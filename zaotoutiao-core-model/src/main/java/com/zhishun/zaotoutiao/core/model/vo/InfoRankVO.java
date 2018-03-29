/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.core.model.vo;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 排行文章信息
 * @author BugMan
 * @version $Id: infoRankVO, v0.1 2018年03月27日 14:12BugMan Exp $
 */
public class InfoRankVO {

    /**
     * id
     */
    private Long id;

    /**
     * 类型
     */
    private String infoType;

    /**
     * 导航
     */
    private String channelName;

    /**
     * 一级标签
     */
    private String firstTabName;

    /**
     * 二级标签
     */
    private String secondTabName;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键词
     */
    private String label;

    /**
     * 缩略图
     */
    private String thumbnails;

    /**
     * 来源、作者
     */
    private String source;

    /**
     * 更新时间
     */
    @JSONField(format="yyyy年MM月dd日 HH:mm:ss")
    private String updateTime;

    /**
     * 阅读数
     */
    private Long readNum;

    /**
     * 转发、分享数
     */
    private Long shareNum;

    /**
     * 收藏数
     */
    private Long collectNum;

    /**
     * 评论数
     */
    private Long commentNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getFirstTabName() {
        return firstTabName;
    }

    public void setFirstTabName(String firstTabName) {
        this.firstTabName = firstTabName;
    }

    public String getSecondTabName() {
        return secondTabName;
    }

    public void setSecondTabName(String secondTabName) {
        this.secondTabName = secondTabName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getShareNum() {
        return shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public Long getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Long collectNum) {
        this.collectNum = collectNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }
}
