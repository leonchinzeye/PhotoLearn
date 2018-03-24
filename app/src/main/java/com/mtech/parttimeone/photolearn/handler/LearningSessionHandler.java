package com.mtech.parttimeone.photolearn.handler;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * @author Leon
 * @date 17/3/18
 */

public class LearningSessionHandler {
    private static LearningSessionHandler handler = null;
//    private static final Logger logger = LogManager.getLogger(LearningSessionHandler.class);
  //  private static final Logger logger = LogManager.getLogger(LearningSessionHandler.class);

    private LearningSessionHandler() {

    }

    public static LearningSessionHandler getInstance() {
        if (handler == null) {
            handler = new LearningSessionHandler();
        }
        return handler;
    }

}
