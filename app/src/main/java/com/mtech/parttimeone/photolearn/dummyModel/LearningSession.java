package com.mtech.parttimeone.photolearn.dummyModel;

import java.util.Date;

/**
 * Created by Zhikai on 14/3/2018.
 */

public class LearningSession {
    private String LearningSessionTitle;
    private String LearningSessionID;
    private String LearningSessionDate;
    private String LearningSessionCreator;

    public LearningSession(String learningSessionTitle, String learningSessionID, String learningSessionDate, String learningSessionCreator) {
        LearningSessionTitle = learningSessionTitle;
        LearningSessionID = learningSessionID;
        LearningSessionDate = learningSessionDate;
        LearningSessionCreator = learningSessionCreator;
    }

    public String getLearningSessionTitle() {
        return LearningSessionTitle;
    }

    public String getLearningSessionID() {
        return LearningSessionID;
    }

    public void setLearningSessionTitle(String learningSessionTitle) {
        LearningSessionTitle = learningSessionTitle;
    }

    public void setLearningSessionID(String learningSessionID) {
        LearningSessionID = learningSessionID;
    }

    public String getLearningSessionDate() {
        return LearningSessionDate;
    }

    public void setLearningSessionDate(String learningSessionDate) {
        LearningSessionDate = learningSessionDate;
    }

    public String getLearningSessionCreator() {
        return LearningSessionCreator;
    }

    public void setLearningSessionCreator(String learningSessionCreator) {
        LearningSessionCreator = learningSessionCreator;
    }
}
