package com.mtech.parttimeone.photolearn.handler;

import android.content.Context;

import com.mtech.parttimeone.photolearn.application.GlobalPhotoLearn;
import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.bo.ParticipantBO;
import com.mtech.parttimeone.photolearn.bo.TrainerBO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

/**
 * @author lechin
 * @date 3/13/18
 *
 * This handler should be called whenever there's a new user who signs up
 * Or when there is a removal of an account in the system
 */

public class AccountHandler {

    public static void createAccount(/* Account createdAccount */) {
        AccountBO accountBO = new AccountBO();

        // TODO
        // Map account details to AccountBO from object passed in via parameter

        LifeCycleHandler lifeCycle = LifeCycleHandler.getInstance();
        lifeCycle.setAccountBO(accountBO);
    }

    public static void updateAccount(/* Account updatedAccount */) {
        LifeCycleHandler lifeCycle = LifeCycleHandler.getInstance();

        AccountBO accountBO = lifeCycle.getAccountBO();

        // TODO
        // Update account details
    }

    public static void deleteAccount() {
        LifeCycleHandler lifeCycle = LifeCycleHandler.getInstance();
        lifeCycle.setAccountBO(null);
    }
}
