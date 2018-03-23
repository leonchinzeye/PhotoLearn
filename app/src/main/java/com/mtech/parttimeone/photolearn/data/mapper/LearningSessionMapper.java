package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.domain.LearningSessionEntity;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionMapper extends FirebaseMapper<LearningSessionBO, LearningSessionEntity> {
    @Override
    public LearningSessionEntity map(LearningSessionBO LearningSessionBO) {
        LearningSessionEntity learningsession = new LearningSessionEntity("","","", "", "");
        //learningsession.setSessionId(LearningSessionBO.getsessionId());
        learningsession.setCourseTitle(LearningSessionBO.getCourseCode());
        learningsession.setModuleName(LearningSessionBO.getCourseModule());
        learningsession.setStartDate(LearningSessionBO.getCourseDate());
        //learningsession.setCreatedDate(LearningSessionBO.getCreatedDate);
        return learningsession;
    }
}
