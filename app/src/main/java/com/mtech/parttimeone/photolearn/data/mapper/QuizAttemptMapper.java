package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptMapper extends FirebaseMapper<QuizAttemptBO, QuizAttemptEntity>  {
    @Override
    public QuizAttemptEntity map(QuizAttemptBO QuizAttemptBO) {
        List<String> quizattemptanswer = new ArrayList();
        QuizAttemptEntity quizattemptentity = new QuizAttemptEntity("","","",quizattemptanswer);
        //quizattemptentity.setAttemptId(QuizAttemptBO.getAttemptId);
        //quizattemptentity.setQuizItemId(QuizAttemptBO.getQuizItemId);
        //quizattemptentity.setUserId(QuizAttemptBO.getUserId);
        quizattemptentity.setAnswer(QuizAttemptBO.getAnswer());
        return quizattemptentity;
    }
}
