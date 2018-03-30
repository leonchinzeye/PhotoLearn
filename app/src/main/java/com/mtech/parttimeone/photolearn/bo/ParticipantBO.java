package com.mtech.parttimeone.photolearn.bo;

import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class ParticipantBO extends AccountBO{
    private List<LearningSessionBO> learningSessions;

    public ParticipantBO() {

    }

    public ParticipantBO(String userUid, String name, String email, String currentUser) {
        super (userUid, name, email, currentUser);
    }

    public List<LearningSessionBO> getLearningSessions() {
        return learningSessions;
    }

    public void setLearningSessions(List<LearningSessionBO> learningSessions) {
        this.learningSessions = learningSessions;
    }
}
