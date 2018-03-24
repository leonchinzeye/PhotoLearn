package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.QuizTitleMapper;
import com.mtech.parttimeone.photolearn.data.entity.QuizTitleEntity;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleRepository extends FirebaseDatabaseRepository<QuizTitleEntity> {
    public QuizTitleRepository() {
        super (new QuizTitleMapper());
    }

    @Override
    public String getRootNode() {
        return "quiz_titles";
    }
}
