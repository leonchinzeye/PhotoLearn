package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 14/3/18
 */

public class QuizAttemptBO {

    final static String TRUUE = "true";
    final static String FALSE = "false";

    private String quizItemId;
    private List<String> answer = new ArrayList<>();

    public void QuizAttemptBO() {

    }

    public QuizAttemptBO(String quizItemId, List<String> answer) {
        this.quizItemId = quizItemId;
        this.answer = answer;
    }
//
//    public QuizAttemptBO(){
//
//    }

    public String getQuizItemId() { return quizItemId; }

    public void setQuizItemId(String quizItemId) {this.quizItemId = quizItemId; }

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
                    answer.add(TRUUE);
                }else {
                    answer.add(FALSE);
                }
            }
        }else{
            answer.set(index,isAns);
        }
    }
}
