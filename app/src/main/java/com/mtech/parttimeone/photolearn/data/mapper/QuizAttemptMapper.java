package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptMapper extends FirebaseMapper<QuizAttemptEntity, QuizAttemptBO>  {
    @Override
    public QuizAttemptBO map(QuizAttemptEntity eQuizAttempt) {
        List<String> answer = new ArrayList<>();
        QuizAttemptBO quizAttemptBO = new QuizAttemptBO("", answer);

        if (StringUtils.isNotEmpty(eQuizAttempt.getQuizItemId())) {
            quizAttemptBO.setQuizItemId(eQuizAttempt.getQuizItemId());
        }

        if (StringUtils.isNotEmpty(eQuizAttempt.getAnswer().toString())) {
            quizAttemptBO.setAnswer(eQuizAttempt.getAnswer());
        }

        return quizAttemptBO;
    }

    @Override
    public QuizAttemptEntity mapFrom(QuizAttemptBO quizAttemptBO) {
        QuizAttemptEntity  eQuizAttempt = new QuizAttemptEntity();

        if (StringUtils.isNotEmpty(quizAttemptBO.getQuizItemId())) {
            eQuizAttempt.setQuizItemId(quizAttemptBO.getQuizItemId());
        }

        if (StringUtils.isNotEmpty(quizAttemptBO.getAnswer().toString())) {
            eQuizAttempt.setAnswer(quizAttemptBO.getAnswer());
        }

        return eQuizAttempt;
    }
}
