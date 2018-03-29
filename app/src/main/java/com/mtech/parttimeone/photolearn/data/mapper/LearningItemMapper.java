package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by karen on 29/3/2018.
 */

public class LearningItemMapper extends FirebaseMapper<LearningItemEntity, LearningItemBO>  {
    @Override
    public LearningItemBO map(LearningItemEntity eLearningItem) {
        LearningItemBO learningItemBO = new LearningItemBO();

        if (StringUtils.isNotEmpty(eLearningItem.getTitleId())) {
            learningItemBO.setTitleId(eLearningItem.getTitleId());
        }

        if (StringUtils.isNotEmpty(eLearningItem.getUserId())) {
            learningItemBO.setUserId(eLearningItem.getUserId());
        }

        if (StringUtils.isNotEmpty(eLearningItem.getTitle())) {
            learningItemBO.setItemtitle(eLearningItem.getTitle());
        }

        if (StringUtils.isNotEmpty(eLearningItem.getPhotoURL())) {
            learningItemBO.setPhotoURL(eLearningItem.getPhotoURL());
        }

        if (StringUtils.isNotEmpty(eLearningItem.getItemDesc())) {
            learningItemBO.setItemDesc(eLearningItem.getItemDesc());
        }

        if (StringUtils.isNotEmpty(eLearningItem.getGPS())) {
            learningItemBO.setGPS(eLearningItem.getGPS());
        }

        return learningItemBO;
    }

    @Override
    public LearningItemEntity mapFrom(LearningItemBO learningItemBO) {
        LearningItemEntity eLearningItem = new LearningItemEntity();

        if (StringUtils.isNotEmpty(learningItemBO.getTitleId())) {
            eLearningItem.setTitleId(learningItemBO.getTitleId());
        }

        if (StringUtils.isNotEmpty(learningItemBO.getUserId())) {
            eLearningItem.setUserId(learningItemBO.getUserId());
        }

        if (StringUtils.isNotEmpty(learningItemBO.getItemtitle())) {
            eLearningItem.setTitle(learningItemBO.getItemtitle());
        }

        if (StringUtils.isNotEmpty(learningItemBO.getPhotoURL())) {
            eLearningItem.setPhotoURL(learningItemBO.getPhotoURL());
        }

        if (StringUtils.isNotEmpty(learningItemBO.getItemDesc())) {
            eLearningItem.setItemDesc(learningItemBO.getItemDesc());
        }

        if (StringUtils.isNotEmpty(learningItemBO.getGPS())) {
            eLearningItem.setGPS(learningItemBO.getGPS());
        }
        return eLearningItem;
    }
}
