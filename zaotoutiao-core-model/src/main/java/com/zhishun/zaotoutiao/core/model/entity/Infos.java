package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Infos implements Serializable {
    private Long id;

    private String infoid;

    private String infotype;

    private String alginfo;

    private Integer channelid;

    private Integer catinfoid;

    private String catinfoname;

    private String hasvideo;

    private Integer imgtype;

    private String producer;

    private Date publishtime;

    private String recid;

    private String source;

    private String thumbnails;

    private String title;

    private Date updatetime;

    private String videos;

    private String summary;

    private String tripleimgs;

    private String h5url;

    private String webUrl;

    private Integer hascontent;

    private Date createDate;

    private Byte ishot;

    private Date pushDate;

    private Byte directIn;

    private Date createTime;

    public Infos() {
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

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype == null ? null : infotype.trim();
    }

    public String getAlginfo() {
        return alginfo;
    }

    public void setAlginfo(String alginfo) {
        this.alginfo = alginfo == null ? null : alginfo.trim();
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Integer getCatinfoid() {
        return catinfoid;
    }

    public void setCatinfoid(Integer catinfoid) {
        this.catinfoid = catinfoid;
    }

    public String getCatinfoname() {
        return catinfoname;
    }

    public void setCatinfoname(String catinfoname) {
        this.catinfoname = catinfoname == null ? null : catinfoname.trim();
    }

    public String getHasvideo() {
        return hasvideo;
    }

    public void setHasvideo(String hasvideo) {
        this.hasvideo = hasvideo == null ? null : hasvideo.trim();
    }

    public Integer getImgtype() {
        return imgtype;
    }

    public void setImgtype(Integer imgtype) {
        this.imgtype = imgtype;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid == null ? null : recid.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails == null ? null : thumbnails.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos == null ? null : videos.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getTripleimgs() {
        return tripleimgs;
    }

    public void setTripleimgs(String tripleimgs) {
        this.tripleimgs = tripleimgs == null ? null : tripleimgs.trim();
    }

    public String getH5url() {
        return h5url;
    }

    public void setH5url(String h5url) {
        this.h5url = h5url == null ? null : h5url.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public Integer getHascontent() {
        return hascontent;
    }

    public void setHascontent(Integer hascontent) {
        this.hascontent = hascontent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Byte getIshot() {
        return ishot;
    }

    public void setIshot(Byte ishot) {
        this.ishot = ishot;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public Byte getDirectIn() {
        return directIn;
    }

    public void setDirectIn(Byte directIn) {
        this.directIn = directIn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}