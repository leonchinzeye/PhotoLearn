package com.mtech.parttimeone.photolearn.Adapter;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by changling on 20/3/18.
 */

public class QuizItemObj {
    private String quiz_title;
    private String quiz_desc;
    public ArrayList<OptionItem> options;
    private String explanation;

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String title) {
        this.quiz_title = title;
    }

    public QuizItemObj(){
        options = new ArrayList<OptionItem>();
        OptionItem item = new OptionItem();
        options.add(item);
    }

    public String getQuiz_desc() {
        return quiz_desc;
    }

    public void setQuiz_desc(String desc){
        this.quiz_desc = desc;
    }

    public String getExplanation(){
        return explanation;
    }

    public void setExplanation(String ans_explanation){
        this.explanation = ans_explanation;
    }

    public boolean isAns(int index){
        OptionItem item = this.options.get(index);
        return item.getAns();
    }



}
