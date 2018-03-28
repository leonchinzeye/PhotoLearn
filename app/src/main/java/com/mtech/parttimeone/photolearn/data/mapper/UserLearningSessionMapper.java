package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.bo.TrainerBO;
import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionMapper extends FirebaseMapper<UserLearningSessionEntity, LearningSessionBO> {
    @Override
    public LearningSessionBO map(UserLearningSessionEntity UserLearningSessionEntity) {
        LearningSessionBO mtrainerBO = new LearningSessionBO();
        //trainerBO.setSessionList(UserLearningSessionEntity.getSessionList());
        return mtrainerBO;
    }

    @Override
    public UserLearningSessionEntity mapFrom(LearningSessionBO learningSessionBO) {
        UserLearningSessionEntity eUserLearningSession = new UserLearningSessionEntity();

        if (StringUtils.isNotEmpty(learningSessionBO.getSessionId())) {
        }
        return null;
    }
}
