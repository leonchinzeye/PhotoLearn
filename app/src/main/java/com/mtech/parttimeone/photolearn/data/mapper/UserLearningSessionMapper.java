package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.entity.UserTypeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionMapper extends FirebaseMapper<UserLearningSessionEntity, UserLearningSessionEntity> {
    @Override
    public UserLearningSessionEntity map(UserLearningSessionEntity UserLearningSessionEntity) {
        List<UserTypeEntity> trainerlist = new ArrayList();
        List<UserTypeEntity> participantlist = new ArrayList();
        UserLearningSessionEntity userlearningsession = new UserLearningSessionEntity(trainerlist, participantlist);
        return userlearningsession;
    }
}
