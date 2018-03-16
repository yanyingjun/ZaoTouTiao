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
}