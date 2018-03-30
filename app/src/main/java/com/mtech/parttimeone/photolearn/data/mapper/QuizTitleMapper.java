package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizTitleEntity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleMapper extends FirebaseMapper<QuizTitleEntity, QuizTitleBO> {
    @Override
    public QuizTitleBO map(QuizTitleEntity eLearningTitle) {
        QuizTitleBO titleBO = new QuizTitleBO();

        if (StringUtils.isNotEmpty(eLearningTitle.getSessionId())) {
            titleBO.setSessionId(eLearningTitle.getSessionId());
        }

        if (StringUtils.isNotEmpty(eLearningTitle.getTitle())) {
            titleBO.setTitle(eLearningTitle.getTitle());
        }

        if (StringUtils.isNotEmpty(eLearningTitle.getCreatedBy())) {
            titleBO.setCreatedBy(eLearningTitle.getCreatedBy());
        }

        return titleBO;
    }

    @Override
    public QuizTitleEntity mapFrom(QuizTitleBO titleBO) {
        QuizTitleEntity eQuizTitle = new QuizTitleEntity();

        if (StringUtils.isNotEmpty(titleBO.getSessionId())) {
            eQuizTitle.setSessionId(titleBO.getSessionId());
        }

        if (StringUtils.isNotEmpty(titleBO.getTitle())) {
            eQuizTitle.setTitle(titleBO.getTitle());
        }

        if (StringUtils.isNotEmpty(titleBO.getCreatedBy())) {
            eQuizTitle.setCreatedBy(titleBO.getCreatedBy());
        }


        return eQuizTitle;
    }
}
