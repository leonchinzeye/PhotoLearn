package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class TrainerBO {

    private List<LearningSessionBO> learningSessions = new ArrayList<>();

    public List<LearningSessionBO> getLearningSessions() {
        return learningSessions;
    }

    public void setLearningSessions(List<LearningSessionBO> learningSessions) {
        this.learningSessions = learningSessions;
    }
}
