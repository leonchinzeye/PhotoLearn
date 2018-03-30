package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 30/3/2018.
 */

public class QuizItemAttemptEntity {
    private String attemptId;
    private String itemId;
    List<String> answer = new ArrayList<>();

    public QuizItemAttemptEntity() {

    }

    public QuizItemAttemptEntity(String attemptId, String itemId, List<String> answer) {
        this.attemptId = attemptId;
        this.itemId = itemId;
        this.answer = answer;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
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
