package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizItemEntity {
    private String titleId;
    private String itemId;
    private String quizTitle;
    private String photoURL;
    private String itemDesc;
    private List<String> answer = new ArrayList<>();
    private List<String> isOptionAns = new ArrayList<>();
    private String solution;

    private void QuizItemEntity(String titleId, String itemId, String quizTitle, String photoURL, String itemDesc, List<String> answer, List<String> isOptionAns, String solution) {
        this.titleId = titleId;
        this.itemId = itemId;
        this.quizTitle = quizTitle;
        this.photoURL = photoURL;
        this.itemDesc = itemDesc;
        this.answer = answer;
        this.isOptionAns = isOptionAns;
        this.solution = solution;
    }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getQuizTitle() { return quizTitle; }

    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public List<String> getAnswer() { return answer; }

    public void setAnswer(List<String> answer) { this.answer = answer; }

    public List<String> getIsOptionAns() { return isOptionAns; }

    public void setIsOptionAns(List<String> isOptionAns) { this.isOptionAns = isOptionAns; }

    public String getSolution() { return solution; }

    public void setSolution(String solution) { this.solution = solution; }
}
