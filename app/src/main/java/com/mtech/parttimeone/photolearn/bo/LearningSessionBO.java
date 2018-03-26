package com.mtech.parttimeone.photolearn.bo;

import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class LearningSessionBO {
    private String courseCode;
    private String courseModule;
    private String courseDate;

    private List<LearningTitleBO> learningTitles;
    private List<QuizTitleBO> quizTitles;

    //Added By Zhikai, need the constructor for convenience. Can remove later if necessary

    public LearningSessionBO() {

    }

    public LearningSessionBO(String courseCode, String courseModule, String courseDate) {
        this.courseCode = courseCode;
        this.courseModule = courseModule;
        this.courseDate = courseDate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseModule() {
        return courseModule;
    }

    public void setCourseModule(String courseModule) {
        this.courseModule = courseModule;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }
}
