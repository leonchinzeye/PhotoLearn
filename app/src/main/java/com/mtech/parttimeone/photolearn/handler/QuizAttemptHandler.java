package com.mtech.parttimeone.photolearn.handler;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * @author Leon
 * @date 22/3/18
 */

public class QuizAttemptHandler {
    private static QuizAttemptHandler handler = null;
//    private static final Logger logger = LogManager.getLogger(QuizAttemptHandler.class);

    private QuizAttemptHandler() {

    }

    public static QuizAttemptHandler getInstance() {
        if (handler == null) {
            handler = new QuizAttemptHandler();
        }
        return handler;
    }

    public void createLearningSession() {

    }

    public void updateLearningSession() {

    }

    public void deleteLearningSession() {

    }
}
