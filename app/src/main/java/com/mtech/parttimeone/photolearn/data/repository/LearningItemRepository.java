package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningItemMapper;

/**
 * Created by karen on 29/3/2018.
 */

public class LearningItemRepository extends FirebaseDatabaseRepository<LearningItemEntity> {
    public LearningItemRepository() {
        super (new LearningItemMapper());
    }

    @Override
    public String getRootNode() {
        return "learning_items";
    }
}
