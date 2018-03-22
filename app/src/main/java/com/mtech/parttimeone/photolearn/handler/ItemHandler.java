package com.mtech.parttimeone.photolearn.handler;

import android.content.Context;

import com.mtech.parttimeone.photolearn.bo.ItemBO;
import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.enumeration.ItemType;

/**
 * @author Leon
 * @date 17/3/18
 */

public class ItemHandler {

    // Equivalent to adding an item
    public void createItem(ItemType type) {
        ItemBO item = null;
        switch (type) {
            case LEARNING:
                break;
            case QUIZ:
                break;
            default:
                break;
        }
    }


    public void updateItem(ItemType type) {

    }

    public void deleteItem(ItemType type) {

    }

    private LearningItemBO createLearningItem() {
        LearningItemBO learningItem = new LearningItemBO();

        // TODO
        // Assume that all the necessary information for the quiz item is set
        // in the context and you can populate the data in quiz item from
        // context

        return learningItem;
    }

    private QuizItemBO createQuizItem() {
        QuizItemBO quizItem = new QuizItemBO();

        // TODO
        // Assume that all the necessary information for the quiz item is set
        // in the context and you can populate the data in quiz item from
        // context

        return quizItem;
    }
}
