package com.mtech.parttimeone.photolearn.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserTitleEntity {
    //includes quiz titles created by the trainer
    private List<UserTypeEntity> trainerList = new ArrayList<>();

    //includes only learning titles the participant has created
    private List<UserTypeEntity> participantList = new ArrayList<>();

    public UserTitleEntity(List<UserTypeEntity> trainerList, List<UserTypeEntity> participantList) {
        this.trainerList = trainerList;
        this.participantList = participantList;
    }

    public List<UserTypeEntity> getTrainerList() { return trainerList; }

    public void setTrainerList(List<UserTypeEntity> trainerList) { this.trainerList = trainerList; }

    public List<UserTypeEntity> getParticipantList() { return participantList; }

    public void setParticipantList(List<UserTypeEntity> participantList) { this.participantList = participantList; }
}
