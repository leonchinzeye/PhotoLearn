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

    final static String TRUUE = "true";
    final static String FALSE = "false";

    private QuizType type;

    private String detailedSolution;

    /* An array list of answer. MCQ will have just 1 answer while MSQ can have up to 4 */
    private List<String> answer = new ArrayList<>();

    private List<String >options = new ArrayList<>();

    private QuizAttemptBO quizAttemptBO = new QuizAttemptBO("",new ArrayList<>());

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

    public void setQuizAttemptBO(QuizAttemptBO quizAttemptBO){
        this.quizAttemptBO = quizAttemptBO;
    }

    public QuizAttemptBO getQuizAttemptBO(){
        return quizAttemptBO;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public List<String>getOptions(){
        return this.options;
    }

    public void setOptions(List<String>options){
        this.options = options;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

//    public void addAns(boolean value,int index){
//        String isAns = String.valueOf(value);
//        if (answer.isEmpty()){
//            for (int i = 0;i<answer.size();i++){
//                if (i==index){
//                    answer.add(TRUUE);
//                }else {
//                    answer.add(FALSE);
//                }
//            }
//        }else{
//            answer.set(index,isAns);
//        }
//    }

    public void updateAns(int index){
        boolean value = Boolean.valueOf(answer.get(index));
        String isAns = String.valueOf(!value);
        answer.set(index,isAns);
    }

    public void addOption(String option){
        options.add(option);
        answer.add(FALSE);
    }

    public void updateOption(String option,int index){
        options.set(index,option);
    }

    public void deleteOptionAndAnswer(int i){
        options.remove(i);
        answer.remove(i);
    }
}
