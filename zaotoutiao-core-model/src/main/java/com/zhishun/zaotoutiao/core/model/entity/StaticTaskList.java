package com.zhishun.zaotoutiao.core.model.entity;

import java.io.Serializable;
import java.util.Date;

public class StaticTaskList implements Serializable {
    private Long id;

    private String question;

    private String answer;

    private String type;

    private Date createDate;

    private String button;

    private String url;

    private String reward;


    private String rewardNum;

    public StaticTaskList() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button == null ? null : button.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * Getter method for property <tt>reward</tt>.
     *
     * @return property value of reward
     */
    public String getReward() {
        return reward;
    }

    /**
     * Setter method for property <tt>reward</tt>.
     *
     * @param reward value to be assigned to property reward
     */
    public void setReward(String reward) {
        this.reward = reward;
    }

    /**
     * Getter method for property <tt>rewardNum</tt>.
     *
     * @return property value of rewardNum
     */
    public String getRewardNum() {
        return rewardNum;
    }

    /**
     * Setter method for property <tt>rewardNum</tt>.
     *
     * @param rewardNum value to be assigned to property rewardNum
     */
    public void setRewardNum(String rewardNum) {
        this.rewardNum = rewardNum;
    }
}