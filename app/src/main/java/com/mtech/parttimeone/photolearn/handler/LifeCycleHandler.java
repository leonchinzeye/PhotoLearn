package com.mtech.parttimeone.photolearn.handler;

import com.google.firebase.auth.FirebaseUser;
import com.mtech.parttimeone.photolearn.bo.AccountBO;

/**
 * @author Leon
 * @date 22/3/18
 */

public class LifeCycleHandler {

    private static LifeCycleHandler handler = null;
    private AccountBO accountBO;

    private LifeCycleHandler() {

    }

    public static LifeCycleHandler getInstance() {
        if (handler == null) {
            handler = new LifeCycleHandler();
        }
        return handler;
    }

    public AccountBO getAccountBO() {
        return accountBO;
    }

    public void setAccountBO(AccountBO accountBO) {
        this.accountBO = accountBO;
    }

    public void signIn(FirebaseUser user) {
    }
}
