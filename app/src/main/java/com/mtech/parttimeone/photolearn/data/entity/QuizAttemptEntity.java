package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptEntity {
    private String userId;
    private String saveState;
    private List<QuizItemAttemptEntity> attemptEntityList;

    public QuizAttemptEntity() {

    }

    public QuizAttemptEntity(String userId, String saveState, List<QuizItemAttemptEntity> attemptEntityList) {
        this.userId = userId;
        this.saveState = saveState;
        this.attemptEntityList = attemptEntityList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSaveState() {
        return saveState;
    }

    public void setSaveState(String saveState) {
        this.saveState = saveState;
    }

    public List<QuizItemAttemptEntity> getAttemptEntityList() {
        return attemptEntityList;
    }

    public void setAttemptEntityList(List<QuizItemAttemptEntity> attemptEntityList) {
        this.attemptEntityList = attemptEntityList;
    }
}
