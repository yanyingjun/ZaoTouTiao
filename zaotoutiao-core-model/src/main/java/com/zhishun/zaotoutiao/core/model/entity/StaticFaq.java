/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;

/**
 * 常见问题表实体
 * @author 闫迎军(YanYingJun)
 * @version $Id: aa, v0.1 2018年02月07日 14:20闫迎军(YanYingJun) Exp $
 */
public class StaticFaq implements Serializable {

    private static final long serialVersionUID = 535149582194574608L;
    /**
     * ID
     */
    private Long id;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 问题分组
     */
    private String groups;

    /**
     * 问题类型（article  video  skip）
     */
    private String type;

    /**
     * 视频地址链接
     */
    private String videoPath;

    /**
     * 跳转链接
     */
    private String skipUrl;

    /**
     * 排序位置
     */
    private Integer orderIndex;

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
     * Getter method for property <tt>question</tt>.
     *
     * @return property value of question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter method for property <tt>question</tt>.
     *
     * @param question value to be assigned to property question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter method for property <tt>answer</tt>.
     *
     * @return property value of answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter method for property <tt>answer</tt>.
     *
     * @param answer value to be assigned to property answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
     * Getter method for property <tt>groups</tt>.
     *
     * @return property value of groups
     */
    public String getGroups() {
        return groups;
    }

    /**
     * Setter method for property <tt>groups</tt>.
     *
     * @param groups value to be assigned to property groups
     */
    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>videoPath</tt>.
     *
     * @return property value of videoPath
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * Setter method for property <tt>videoPath</tt>.
     *
     * @param videoPath value to be assigned to property videoPath
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * Getter method for property <tt>skipUrl</tt>.
     *
     * @return property value of skipUrl
     */
    public String getSkipUrl() {
        return skipUrl;
    }

    /**
     * Setter method for property <tt>skipUrl</tt>.
     *
     * @param skipUrl value to be assigned to property skipUrl
     */
    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    /**
     * Getter method for property <tt>orderIndex</tt>.
     *
     * @return property value of orderIndex
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * Setter method for property <tt>orderIndex</tt>.
     *
     * @param orderIndex value to be assigned to property orderIndex
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}