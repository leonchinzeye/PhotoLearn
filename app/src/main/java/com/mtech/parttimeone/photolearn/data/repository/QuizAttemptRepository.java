package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.QuizAttemptMapper;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptRepository  extends FirebaseDatabaseRepository<QuizAttemptEntity> {
    public QuizAttemptRepository() {
        super (new QuizAttemptMapper());
    }

    @Override
    public String getRootNode() {
        return "user_quiz_attempts";
    }
}
