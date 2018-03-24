package com.mtech.parttimeone.photolearn.handler;

import android.content.Context;

import com.mtech.parttimeone.photolearn.application.GlobalPhotoLearn;
import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.bo.ParticipantBO;
import com.mtech.parttimeone.photolearn.bo.TrainerBO;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

/**
 * @author lechin
 * @date 3/13/18
 *
 * This handler should be called whenever there's a new user who signs up
 * Or when there is a removal of an account in the system
 */

public class AccountHandler {

    private static AccountHandler handler = null;
   // private static final Logger logger = LogManager.getLogger(AccountHandler.class);
    //private static final Logger logger = LogManager.getLogger(AccountHandler.class);

    private AccountBO accountBO;

    private AccountHandler() {

    }

    public static AccountHandler getInstance() {
        if (handler == null) {
            handler = new AccountHandler();
        }
        return handler;
    }

    public AccountBO loadAccount() {

        // Check if cloud db has data change for account
        if (accountBO == null || hasDataChange()) {
            // TODO
            // build account BO

            createAccount();
        }

        return accountBO;
    }

    private boolean hasDataChange() {
        return false;
    }


    private void createAccount() {
        AccountBO account = new AccountBO();

        account.setAccountName("Jason Bourne");
        account.setAccountNumber(new BigInteger(Long.toString(System.currentTimeMillis())));

        populateParticipantDetails(account);
        populateTrainerDetails(account);

        this.accountBO = account;
    }

    private void populateTrainerDetails(AccountBO accountBO) {
        TrainerBO trainer = new TrainerBO();

        LearningSessionHandler sessionHandler = LearningSessionHandler.getInstance();

        accountBO.setTrainerBO(trainer);
    }

    private void populateParticipantDetails(AccountBO accountBO) {
        ParticipantBO participant = new ParticipantBO();

        accountBO.setParticipantBO(participant);
    }

}
