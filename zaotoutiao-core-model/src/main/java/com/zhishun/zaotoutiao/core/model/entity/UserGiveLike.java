package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

public class UserGiveLike implements Serializable{

    private Long id;

    private Long userid;

    private Long commentsid;

    private Byte isLike;

    private String createdate;

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

    public Long getCommentsid() {
        return commentsid;
    }

    public void setCommentsid(Long commentsid) {
        this.commentsid = commentsid;
    }

    public Byte getIsLike() {
        return isLike;
    }

    public void setIsLike(Byte isLike) {
        this.isLike = isLike;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}