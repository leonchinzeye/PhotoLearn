package com.mtech.parttimeone.photolearn.bo;

import com.mtech.parttimeone.photolearn.enumeration.QuizType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class QuizItemBO extends ItemBO {

    private QuizType type;

    private String detailedSolution;

    /* An array list of answer. MCQ will have just 1 answer while MSQ can have up to 4 */
    private List<String> answer = new ArrayList<>();

    private String itemDescription;

    public QuizType getType() {
        return type;
    }

    public void setType(QuizType type) {
        this.type = type;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
