package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemAttemptEntity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptMapper extends FirebaseMapper<QuizAttemptEntity, QuizAttemptBO>  {
    @Override
    public QuizAttemptBO map(QuizAttemptEntity eQuizAttempt) {
        QuizAttemptBO quizAttemptBO = new QuizAttemptBO("","",null);

        if (StringUtils.isNotEmpty(eQuizAttempt.getUserId())) {
            quizAttemptBO.setUserId(eQuizAttempt.getUserId());
        }

        if (StringUtils.isNotEmpty(eQuizAttempt.getSaveState())) {
            quizAttemptBO.setSaveState(eQuizAttempt.getSaveState());
        }

        /*
        if (StringUtils.isNotEmpty(eQuizAttempt.getAttemptEntityList())) {
            quizAttemptBO.setAttemptBOList(eQuizAttempt.setAttemptEntityList());
        }
        */

        return quizAttemptBO;
    }

    @Override
    public QuizAttemptEntity mapFrom(QuizAttemptBO quizAttemptBO) {
        QuizAttemptEntity  eQuizAttempt = new QuizAttemptEntity();

        if (StringUtils.isNotEmpty(quizAttemptBO.getUserId())) {
            eQuizAttempt.setUserId(quizAttemptBO.getUserId());
        }

        if (StringUtils.isNotEmpty(quizAttemptBO.getSaveState())) {
            eQuizAttempt.setSaveState(quizAttemptBO.getSaveState());
        }

        return eQuizAttempt;
    }
}
