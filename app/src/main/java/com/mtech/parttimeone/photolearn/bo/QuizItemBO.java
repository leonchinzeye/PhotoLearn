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

    //private QuizType type; //don't need this, can determine from answer
    private String itemId;
    private String detailedSolution; //No need this, we just use the photo desc.

    /* An array list of answer. MCQ will have just 1 answer while MSQ can have up to 4 */
    private List<String> answer = new ArrayList<>(); //option
    private List<String> answerOption = new ArrayList<>(); //isOptionAns

    private QuizAttemptBO quizAttemptBO = new QuizAttemptBO("",new ArrayList<>());

   // private String itemDescription;

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

//    public String getItemDescription() {
//        return itemDescription;
//    }

    public List<String> getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(List<String> answerOption) {
        this.answerOption = answerOption;
    }

    public Boolean isAns(int index){
        if (answerOption.isEmpty()){
            return false;
        }else {
            String value = answerOption.get(index);
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
        answerOption.set(index,isAns);
    }

    public void addOption(String option){
        answer.add(option);
        answerOption.add(FALSE);
    }

    public void updateOption(String option,int index){
        answer.set(index,option);
    }

    public void deleteOptionAndAnswer(int i){
        answer.remove(i);
        answerOption.remove(i);
    }

    public Boolean isAnsCorrect(){
        // isAns option should be true.
        return answerOption.equals(quizAttemptBO.getAnswer());//containsAll(quizAttemptBO.getAnswer());

    }

}
