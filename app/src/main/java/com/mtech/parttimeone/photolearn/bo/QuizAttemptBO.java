package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 14/3/18
 */

public class QuizAttemptBO {
    private String quizItemId;
    private List<String> answer = new ArrayList<>();

    public void QuizAttemptBO() {

    }

    public QuizAttemptBO(String quizItemId, List<String> answer) {
        this.quizItemId = quizItemId;
        this.answer = answer;
    }

    public String getQuizItemId() { return quizItemId; }

    public void setQuizItemId(String quizItemId) {this.quizItemId = quizItemId; }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
