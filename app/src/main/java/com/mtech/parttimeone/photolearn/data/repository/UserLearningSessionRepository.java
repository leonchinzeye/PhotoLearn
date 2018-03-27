package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.mapper.UserLearningSessionMapper;

/**
 * Created by karen on 25/3/2018.
 */

public class UserLearningSessionRepository extends FirebaseDatabaseRepository<UserLearningSessionEntity> {
    public UserLearningSessionRepository() { super (new UserLearningSessionMapper()); }

    @Override
    public String getRootNode() { return "user_learning_sessions"; }
}
