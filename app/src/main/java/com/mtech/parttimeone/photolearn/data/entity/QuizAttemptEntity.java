package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptEntity {
    String quizItemId;
    List<String> answer = new ArrayList<>();

    public QuizAttemptEntity() {

    }

    public QuizAttemptEntity(String quizItemId, List<String> answer) {
        this.quizItemId = quizItemId;
        this.answer = answer;
    }

    public String getQuizItemId() { return quizItemId; }

    public void setQuizItemId(String quizItemId) { this.quizItemId = quizItemId; }

    public List<String> getAnswer() { return answer; }

    public void setAnswer(List<String> answer) { this.answer = answer; }
}
