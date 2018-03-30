package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptEntity {
    private String attemptId;
    private String userId;
    private String itemId;
    List<String> answer = new ArrayList<>();

    public QuizAttemptEntity() {

    }

    public QuizAttemptEntity(String quizItemId, List<String> answer) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.itemId = itemId;
        this.answer = answer;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
