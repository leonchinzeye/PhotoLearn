package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningSessionEntity;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionMapper extends FirebaseMapper<LearningSessionEntity, LearningSessionBO> {
    @Override
    public LearningSessionBO map(LearningSessionEntity LearningSessionEntity) {
        LearningSessionBO learningsessionBO = new LearningSessionBO("","","");
        //learningsessionBO.setSessionId(LearningSessionEntity.getSessionId());
        learningsessionBO.setCourseCode(LearningSessionEntity.getCourseTitle());
        learningsessionBO.setCourseModule(LearningSessionEntity.getModuleName());
        learningsessionBO.setCourseDate(LearningSessionEntity.getStartDate());
        //learningsessionBO.setCreatedDate(LearningSessionBO.getCreatedDate);
        return learningsessionBO;
    }
}
