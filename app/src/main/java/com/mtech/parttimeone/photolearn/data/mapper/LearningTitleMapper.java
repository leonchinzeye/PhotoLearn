package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningTitleBO;
import com.mtech.parttimeone.photolearn.bo.TitleBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningTitleEntity;
import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleMapper extends FirebaseMapper<LearningTitleEntity, LearningTitleBO> {
    @Override
    public LearningTitleBO map(LearningTitleEntity eLearningTitle) {
        LearningTitleBO titleBO = new LearningTitleBO();

        if (StringUtils.isNotEmpty(eLearningTitle.getSessionId())) {
            titleBO.setSessionId(eLearningTitle.getSessionId());
        }

        if (StringUtils.isNotEmpty(eLearningTitle.getTitleId())) {
            titleBO.setTitleId(eLearningTitle.getTitleId());
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
    public LearningTitleEntity mapFrom(LearningTitleBO titleBO) {
        LearningTitleEntity eLearningTitle = new LearningTitleEntity();

        if (StringUtils.isNotEmpty(titleBO.getSessionId())) {
            eLearningTitle.setSessionId(titleBO.getSessionId());
        }

        if (StringUtils.isNotEmpty(titleBO.getTitleId())) {
            eLearningTitle.setTitleId(titleBO.getTitleId());
        }

        if (StringUtils.isNotEmpty(titleBO.getTitle())) {
            eLearningTitle.setTitle(titleBO.getTitle());
        }

        if (StringUtils.isNotEmpty(titleBO.getCreatedBy())) {
            eLearningTitle.setCreatedBy(titleBO.getCreatedBy());
        }


        return eLearningTitle;
    }
}
