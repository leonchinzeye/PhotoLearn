package com.mtech.parttimeone.photolearn.bo;

import com.mtech.parttimeone.photolearn.enumeration.QuizType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class QuizItemBO extends ItemBO {
    //private QuizType type; //don't need this, can determine from answer
    private String itemId;
    private String detailedSolution;

    /* An array list of answer. MCQ will have just 1 answer while MSQ can have up to 4 */
    private List<String> answer = new ArrayList<>(); //option
    private List<String> answerOption = new ArrayList<>(); //isOptionAns

    public QuizItemBO() {

    }

    public QuizItemBO(String itemId, String itemtitle, String titleId, String photoURL, String itemDesc, List<String> answer, List<String> answerOption, String detailedSolution) {
        super(itemtitle, titleId, photoURL, itemDesc);
        this.itemId = itemId;
        this.answer = answer;
        this.answerOption = answerOption;
        this.detailedSolution = detailedSolution;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDetailedSolution() {
        return detailedSolution;
    }

    public void setDetailedSolution(String detailedSolution) {
        this.detailedSolution = detailedSolution;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public List<String> getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(List<String> answerOption) {
        this.answerOption = answerOption;
    }
}
