package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.TitleBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemEntity;
import com.mtech.parttimeone.photolearn.data.entity.QuizTitleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleMapper extends FirebaseMapper<TitleBO, QuizTitleEntity> {
    @Override
    public QuizTitleEntity map(TitleBO TitleBO) {
        List<QuizItemEntity> quizitementity = new ArrayList();
        QuizTitleEntity quiztitleentity = new QuizTitleEntity("","","","",quizitementity);
        quiztitleentity.setSessionId(TitleBO.getSessionId());
        //quiztitleentity.setTitleId(TitleBO.getTitleId());
        quiztitleentity.setTitle(TitleBO.getTitle());
        //quiztitleentity.setCreatedDate(QuizTitleBO.getCreatedDate);
        //quiztitleentity.setQuizItemEntities(TitleBO.getItems());
        return quiztitleentity;
    }

    @Override
    public TitleBO mapFrom(QuizTitleEntity quizTitleEntity) {
        return null;
    }
}
