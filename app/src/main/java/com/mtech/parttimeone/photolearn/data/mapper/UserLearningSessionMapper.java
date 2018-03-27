package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.bo.TrainerBO;
import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionMapper extends FirebaseMapper<UserLearningSessionEntity, TrainerBO> {
    @Override
    public TrainerBO map(UserLearningSessionEntity UserLearningSessionEntity) {
        TrainerBO mtrainerBO = new TrainerBO();
        //trainerBO.setSessionList(UserLearningSessionEntity.getSessionList());
        return mtrainerBO;
    }
}
