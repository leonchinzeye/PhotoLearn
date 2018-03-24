package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizItemEntity {
    private String titleId;
    private String quizItemId;
    private String quizTitle;
    private String photoURL;
    private String option;
    private List<String> isOptionAns = new ArrayList<>();
    private String solution;

    private void QuizItemEntity(String titleId, String quizItemId, String quizTitle, String photoURL, String option, List<String> isOptionAns, String solution) {
        this.titleId = titleId;
        this.quizItemId = quizItemId;
        this.quizTitle = quizTitle;
        this.photoURL = photoURL;
        this.option = option;
        this.isOptionAns = isOptionAns;
        this.solution = solution;
    }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getQuizItemId() { return quizItemId; }

    public void setQuizItemId(String quizItemId) { this.quizItemId = quizItemId; }

    public String getQuizTitle() { return quizTitle; }

    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    public List<String> getIsOptionAns() { return isOptionAns; }

    public void setIsOptionAns(List<String> isOptionAns) { this.isOptionAns = isOptionAns; }

    public String getSolution() { return solution; }

    public void setSolution(String solution) { this.solution = solution; }
}
