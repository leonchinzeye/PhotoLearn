package com.mtech.parttimeone.photolearn.dummyModel.utils;

import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Leon
 * @date 26/3/18
 */

public class DummyUtils {

    public static List<LearningSessionBO> populateDummyLearningBOs() {
        List<LearningSessionBO> learningSessionBOS = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LearningSessionBO learningSession = populateDummyLearningSession();
            learningSessionBOS.add(learningSession);
        }
        return learningSessionBOS;
    }

    public static LearningSessionBO populateDummyLearningSession() {
        LearningSessionBO learningSessionBO = new LearningSessionBO();

        learningSessionBO.setCourseDate(randomDate());
        learningSessionBO.setCourseCode(RandomStringUtil.generateString());
        learningSessionBO.setCourseModule(RandomStringUtil.generateString());

        return learningSessionBO;
    }

    public static String randomDate() {

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return (gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
