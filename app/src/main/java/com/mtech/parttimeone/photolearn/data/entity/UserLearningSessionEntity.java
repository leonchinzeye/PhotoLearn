package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionEntity {
    //includes sessions the trainer has created
    private List<LearningSessionEntity> sessionList = new ArrayList<>();

    public UserLearningSessionEntity() {

    }

    public UserLearningSessionEntity(List<LearningSessionEntity> sessionList) {
        this.sessionList = sessionList;
    }

    public List<LearningSessionEntity> getSessionList() { return sessionList; }

    public void setSessionList(List<LearningSessionEntity> sessionList) { this.sessionList = sessionList; }
}
