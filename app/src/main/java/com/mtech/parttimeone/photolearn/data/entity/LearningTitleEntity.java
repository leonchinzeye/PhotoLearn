package com.mtech.parttimeone.photolearn.data.entity;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleEntity {
    private String sessionId;
    private String titleId;
    private String title;
    private String createdBy;

    public LearningTitleEntity() {

    }

    public LearningTitleEntity(String sessionId, String titleId, String title, String createdDate) {
        this.sessionId = sessionId;
        this.titleId = titleId;
        this.title = title;
        this.createdBy = createdBy;
    }

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}
