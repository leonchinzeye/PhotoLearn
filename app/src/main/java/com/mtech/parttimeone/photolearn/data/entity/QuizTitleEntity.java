package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleEntity {
    private String sessionId;
    private String titleId;
    private String title;
    private String createdBy;

    public QuizTitleEntity() {

    }

    public QuizTitleEntity (String sessionId, String titleId, String title, String createdBy, List<QuizItemEntity> quizItemEntities) {
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
