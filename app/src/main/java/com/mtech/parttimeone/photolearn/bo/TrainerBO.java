package com.mtech.parttimeone.photolearn.bo;

import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public class TrainerBO extends AccountBO{
    private List<LearningSessionBO> learningSessions = new ArrayList<>();

    public TrainerBO() {

    }

    public TrainerBO(String userUid, String name, String email, String currentUser) {
        super (userUid, name, email, currentUser);
    }

    public List<LearningSessionBO> getLearningSessions() {
        return learningSessions;
    }

    public void setLearningSessions(List<LearningSessionBO> learningSessions) {
        this.learningSessions = learningSessions;
    }
}
