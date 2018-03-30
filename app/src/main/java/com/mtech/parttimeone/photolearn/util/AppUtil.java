package com.mtech.parttimeone.photolearn.util;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.mtech.parttimeone.photolearn.application.GlobalPhotoLearn;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

/**
 * @author lechin
 * @date 3/30/18
 */

public class AppUtil {

    public static String getUserName(Fragment f) {
        GlobalPhotoLearn globalPhotoLearn = (GlobalPhotoLearn) f.getActivity().getApplicationContext();
        FirebaseAuth mAuth;
        String userName;
        mAuth = globalPhotoLearn.getmAuth();
        userName = mAuth.getCurrentUser().getUid();
        return userName;
    }

    public static UserType getMode(Fragment f) {
        GlobalPhotoLearn globalPhotoLearn = (GlobalPhotoLearn) f.getActivity().getApplicationContext();
        UserType ut;
        ut = globalPhotoLearn.getmUserType();
        return ut;
    }

    public static String getUserName(Activity a) {
        GlobalPhotoLearn globalPhotoLearn = (GlobalPhotoLearn) a.getApplicationContext();
        FirebaseAuth mAuth;
        String userName;
        mAuth = globalPhotoLearn.getmAuth();
        userName = mAuth.getCurrentUser().getUid();
        return userName;
    }

    public static UserType getMode(Activity a) {
        GlobalPhotoLearn globalPhotoLearn = (GlobalPhotoLearn) a.getApplicationContext();
        UserType ut;
        ut = globalPhotoLearn.getmUserType();
        return ut;
    }
}
