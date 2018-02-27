package com.zhishun.zaotoutiao.core.model.entity;

import java.util.Date;


public class Content {
    private Long id;

    private String infoid;

    private String content;

    private String htmlContent;

    private Date createDate;

    public Content(Long id, String infoid, String content, String htmlContent, Date createDate) {
        this.id = id;
        this.infoid = infoid;
        this.content = content;
        this.htmlContent = htmlContent;
        this.createDate = createDate;
    }

    public Content() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid == null ? null : infoid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}