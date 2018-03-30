package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 30/3/2018.
 */

public class QuizItemAttemptBO {
    final static String TRUE = "true";
    final static String FALSE = "false";

    private String attemptId;
    private String itemId;
    List<String> answer = new ArrayList<>();

    public QuizItemAttemptBO() {

    }

    public QuizItemAttemptBO(String attemptId, String itemId, List<String> answer) {
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

    public Boolean isAns(int index){
        if (answer.isEmpty()){
            return false;
        }else {
            String value = answer.get(index);
            Boolean isAns = Boolean.valueOf(value);
            return isAns;
        }
    }

    public void addAns(boolean value,int index,int size){
        String isAns = String.valueOf(value);
        if (answer.isEmpty()){
            for (int i = 0;i<size;i++){
                if (i==index){
                    answer.add(TRUE);
                }else {
                    answer.add(FALSE);
                }
            }
        }else{
            answer.set(index,isAns);
        }
    }
}
