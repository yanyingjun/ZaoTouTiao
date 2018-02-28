package com.zhishun.zaotoutiao.core.model.entity;

import java.util.Date;

public class UserCollect {
    private Long id;

    private Long userid;

    private String infosid;

    private Date createDate;

    public UserCollect(Long id, Long userid, String infosid, Date createDate) {
        this.id = id;
        this.userid = userid;
        this.infosid = infosid;
        this.createDate = createDate;
    }

    public UserCollect() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getInfosid() {
        return infosid;
    }

    public void setInfosid(String infosid) {
        this.infosid = infosid == null ? null : infosid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}