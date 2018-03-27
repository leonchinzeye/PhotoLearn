package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.TitleBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningTitleEntity;
import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleMapper extends FirebaseMapper<TitleBO, LearningTitleEntity> {
    @Override
    public LearningTitleEntity map(TitleBO TitleBO) {
        List<LearningItemEntity> learningitementity = new ArrayList();
        LearningTitleEntity learningtitleentity = new LearningTitleEntity("","","","",learningitementity);
        learningtitleentity.setSessionId(TitleBO.getSessionId());
        //learningtitleentity.setTitleId(LearningTitleBO.getTitleId());
        learningtitleentity.setTitle(TitleBO.getTitle());
        //learningtitleentity.setCreatedDate(TitleBO.getCreatedDate);
        //learningtitleentity.setLearningItemEntities(TitleBO.getItems());
        return learningtitleentity;
    }

    @Override
    public TitleBO mapFrom(LearningTitleEntity learningTitleEntity) {
        return null;
    }


}
