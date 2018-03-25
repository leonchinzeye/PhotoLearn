package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionEntity {
    //includes sessions the trainer has created
    private List<UserTypeEntity> trainerList = new ArrayList<>();

    //includes enrolled sessions by the participant
    private List<UserTypeEntity> participantList = new ArrayList<>();

    public UserLearningSessionEntity(List<UserTypeEntity> trainerList, List<UserTypeEntity> participantList) {
        this.trainerList = trainerList;
        this.participantList = participantList;
    }

    public List<UserTypeEntity> getTrainerList() { return trainerList; }

    public void setTrainerList(List<UserTypeEntity> trainerList) { this.trainerList = trainerList; }

    public List<UserTypeEntity> getParticipantList() { return participantList; }

    public void setParticipantList(List<UserTypeEntity> participantList) { this.participantList = participantList; }
}
