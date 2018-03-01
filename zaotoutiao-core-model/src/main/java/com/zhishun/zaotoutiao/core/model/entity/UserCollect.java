package com.zhishun.zaotoutiao.core.model.entity;

public class UserCollect {
    private Long id;

    private Long userId;

    private String infosId;

    private String createDate;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>infosId</tt>.
     *
     * @return property value of infosId
     */
    public String getInfosId() {
        return infosId;
    }

    /**
     * Setter method for property <tt>infosId</tt>.
     *
     * @param infosId value to be assigned to property infosId
     */
    public void setInfosId(String infosId) {
        this.infosId = infosId;
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
}