package com.zhishun.zaotoutiao.core.model.entity;

import java.util.Date;

public class UserCollect {
    private Long id;

    private Long userId;

    private String title;

    private String createDate;

    private String filter;

    private String url;

    private String author;

    private String rawUrl;

    private String channel;

    private String source;

    private String style;

    private String picUrl;

    private String publishDate;

    private String infosType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter == null ? null : filter.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl == null ? null : rawUrl.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
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
     * Getter method for property <tt>publishDate</tt>.
     *
     * @return property value of publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Setter method for property <tt>publishDate</tt>.
     *
     * @param publishDate value to be assigned to property publishDate
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Getter method for property <tt>infosType</tt>.
     *
     * @return property value of infosType
     */
    public String getInfosType() {
        return infosType;
    }

    /**
     * Setter method for property <tt>infosType</tt>.
     *
     * @param infosType value to be assigned to property infosType
     */
    public void setInfosType(String infosType) {
        this.infosType = infosType;
    }
}