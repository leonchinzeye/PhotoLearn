package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.entity.QuizItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.QuizItemMapper;

/**
 * Created by karen on 29/3/2018.
 */

public class QuizItemRepository extends FirebaseDatabaseRepository<QuizItemEntity> {
    public QuizItemRepository() {
        super (new QuizItemMapper());
    }

    @Override
    public String getRootNode() {
        return "quiz_items";
    }
}
