package com.mtech.parttimeone.photolearn.bo;

import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class ParticipantBO {

    private List<LearningSessionBO> learningSessions;

    public List<LearningSessionBO> getLearningSessions() {
        return learningSessions;
    }

    public void setLearningSessions(List<LearningSessionBO> learningSessions) {
        this.learningSessions = learningSessions;
    }
}
