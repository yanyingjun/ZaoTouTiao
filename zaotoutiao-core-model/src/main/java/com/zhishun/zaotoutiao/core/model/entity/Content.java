package com.zhishun.zaotoutiao.core.model.entity;

import java.util.Date;

public class Content {
    private Long id;

    private String infoId;

    private Date createDate;

    private String content;

    private String htmlContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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
     * Getter method for property <tt>htmlContent</tt>.
     *
     * @return property value of htmlContent
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * Setter method for property <tt>htmlContent</tt>.
     *
     * @param htmlContent value to be assigned to property htmlContent
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}