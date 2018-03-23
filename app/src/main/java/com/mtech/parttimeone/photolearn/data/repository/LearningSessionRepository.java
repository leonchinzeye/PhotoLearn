package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.LearningSessionMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.domain.LearningSessionEntity;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionRepository extends FirebaseDatabaseRepository<LearningSessionEntity> {
    public LearningSessionRepository() {
        super (new LearningSessionMapper());
    }

    @Override
    public String getRootNode() {
        return "learning_sessions";
    }
}
