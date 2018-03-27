package com.mtech.parttimeone.photolearn.handler;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.mtech.parttimeone.photolearn.ViewModel.LearningSessionViewModel;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;

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

    public void createLearningSession(LearningSessionBO newSession, FragmentActivity activity) {
    }

    public void updateLearningSession(LearningSessionBO oldSession, LearningSessionBO newSession) {

    }

    public void deleteLearningSession() {

    }
}
