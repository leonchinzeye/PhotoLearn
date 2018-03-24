package com.mtech.parttimeone.photolearn.handler;

import com.mtech.parttimeone.photolearn.bo.ItemBO;
import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.enumeration.ItemType;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * @author Leon
 * @date 17/3/18
 */

public class ItemHandler {

    private static ItemHandler handler = null;
   // private static final Logger logger = LogManager.getLogger(ItemHandler.class);
  //  private static final Logger logger = LogManager.getLogger(ItemHandler.class);

    private ItemHandler() {

    }

    public static ItemHandler getInstance() {
        if (handler == null) {
            handler = new ItemHandler();
        }
        return handler;
    }

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

    private LearningItemBO createLearningItem(Context context) {

        //Changed by ZK
        LearningItemBO learningItem = new LearningItemBO("DEFAULT","DEFAULT","DEFAULT","DEFAULT");

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
