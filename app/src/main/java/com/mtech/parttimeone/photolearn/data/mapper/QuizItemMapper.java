package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemEntity;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by karen on 29/3/2018.
 */

public class QuizItemMapper extends FirebaseMapper<QuizItemEntity, QuizItemBO> {
    @Override
    public QuizItemBO map(QuizItemEntity eQuizItem) {
        QuizItemBO QuizItemBO = new QuizItemBO();

        if (StringUtils.isNotEmpty(eQuizItem.getTitleId())) {
            QuizItemBO.setTitleId(eQuizItem.getTitleId());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getQuizTitle())) {
            QuizItemBO.setItemtitle(eQuizItem.getQuizTitle());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getPhotoURL())) {
            QuizItemBO.setPhotoURL(eQuizItem.getPhotoURL());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getItemDesc())) {
            QuizItemBO.setItemDesc(eQuizItem.getItemDesc());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getItemId())) {
            QuizItemBO.setItemId(eQuizItem.getItemId());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getAnswer().toString())) {
            QuizItemBO.setAnswer(eQuizItem.getAnswer());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getIsOptionAns().toString())) {
            QuizItemBO.setAnswerOption(eQuizItem.getIsOptionAns());
        }

        if (StringUtils.isNotEmpty(eQuizItem.getSolution())) {
            QuizItemBO.setDetailedSolution(eQuizItem.getSolution());
        }

        return QuizItemBO;
    }

    @Override
    public QuizItemEntity mapFrom(QuizItemBO QuizItemBO) {
        QuizItemEntity eQuizItem = new QuizItemEntity();

        if (StringUtils.isNotEmpty(QuizItemBO.getTitleId())) {
            eQuizItem.setTitleId(QuizItemBO.getTitleId());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getItemtitle())) {
            eQuizItem.setQuizTitle(QuizItemBO.getItemtitle());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getPhotoURL())) {
            eQuizItem.setPhotoURL(QuizItemBO.getPhotoURL());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getItemDesc())) {
            eQuizItem.setItemDesc(QuizItemBO.getItemDesc());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getItemId())) {
            eQuizItem.setItemId(QuizItemBO.getItemId());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getAnswer().toString())) {
            eQuizItem.setAnswer(QuizItemBO.getAnswer());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getAnswerOption().toString())) {
            eQuizItem.setIsOptionAns(QuizItemBO.getAnswerOption());
        }

        if (StringUtils.isNotEmpty(QuizItemBO.getDetailedSolution())) {
            eQuizItem.setSolution(QuizItemBO.getDetailedSolution());
        }

        return eQuizItem;
    }
}
