package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.LearningTitleMapper;
import com.mtech.parttimeone.photolearn.domain.LearningTitleEntity;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleRepository extends FirebaseDatabaseRepository<LearningTitleEntity> {
    public LearningTitleRepository() {
        super (new LearningTitleMapper());
    }

    @Override
    public String getRootNode() {
        return "learning_titles";
    }
}
