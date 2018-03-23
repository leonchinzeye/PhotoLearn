package com.mtech.parttimeone.photolearn.domain;

import com.mtech.parttimeone.photolearn.domain.LearningItemEntity;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleEntity {
    private String sessionId;
    private String titleId;
    private String title;
    private String createdDate;

    private List<LearningItemEntity> learningItemEntities = new ArrayList<>();

    public LearningTitleEntity(String sessionId, String titleId, String title, String createdDate, List<LearningItemEntity> learningItemEntities) {
        this.sessionId = sessionId;
        this.titleId = titleId;
        this.title = title;
        this.createdDate = createdDate;
        this.learningItemEntities = learningItemEntities;
    }

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public List<LearningItemEntity> getLearningItemsEntities() { return learningItemEntities; }

    public void setLearningItemEntities(List<LearningItemEntity> learningItemEntities) { this.learningItemEntities = learningItemEntities; }

}
