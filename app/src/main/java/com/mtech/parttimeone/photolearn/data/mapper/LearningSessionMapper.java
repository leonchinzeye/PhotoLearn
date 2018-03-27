package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningSessionEntity;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionMapper extends FirebaseMapper<LearningSessionEntity, LearningSessionBO> {
    @Override
    public LearningSessionBO map(LearningSessionEntity eLearningSession) {
        LearningSessionBO learningSessionBO = new LearningSessionBO();

        if (StringUtils.isNotEmpty(eLearningSession.getCourseTitle())) {
            learningSessionBO.setCourseCode(eLearningSession.getCourseTitle());
        }

        if (StringUtils.isNotEmpty(eLearningSession.getModuleName())) {
            learningSessionBO.setCourseModule(eLearningSession.getModuleName());
        }

        if (StringUtils.isNotEmpty(eLearningSession.getStartDate())) {
            learningSessionBO.setCourseDate(eLearningSession.getStartDate());
        }

        if (StringUtils.isNotEmpty(eLearningSession.getSessionId())) {
            learningSessionBO.setSessionId(eLearningSession.getSessionId());
        }

        return learningSessionBO;
    }

    @Override
    public LearningSessionEntity mapFrom(LearningSessionBO learningSessionBO) {
        LearningSessionEntity eLearningSession = new LearningSessionEntity();

        if (StringUtils.isNotEmpty(learningSessionBO.getSessionId())) {
            eLearningSession.setSessionId(learningSessionBO.getSessionId());
        }

        if (StringUtils.isNotEmpty(learningSessionBO.getCourseModule())) {
            eLearningSession.setModuleName(learningSessionBO.getCourseModule());
        }

        if (StringUtils.isNotEmpty(learningSessionBO.getCourseDate())) {
            eLearningSession.setStartDate(learningSessionBO.getCourseDate());
        }

        if (StringUtils.isNotEmpty(learningSessionBO.getCourseCode())) {
            eLearningSession.setCourseTitle(learningSessionBO.getCourseCode());
        }


        return eLearningSession;
    }
}
