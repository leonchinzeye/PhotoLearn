package com.mtech.parttimeone.photolearn.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptEntity {
    private String attemptId;
    private String quizItemId;
    private String userId;
    List<String> answer = new ArrayList<>();

    public QuizAttemptEntity(String attemptId, String quizItemId, String userId, List<String> answer) {
        this.attemptId = attemptId;
        this.quizItemId = quizItemId;
        this.userId = userId;
        this.answer = answer;
    }

    public String getAttemptId() { return attemptId; }

    public void setAttemptId(String attemptId) { this.attemptId = attemptId; }

    public String getQuizItemId() { return quizItemId; }

    public void setQuizItemId(String quizItemId) { this.quizItemId = quizItemId; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public List<String> getAnswer() { return answer; }

    public void setAnswer(List<String> answer) { this.answer = answer; }
}
