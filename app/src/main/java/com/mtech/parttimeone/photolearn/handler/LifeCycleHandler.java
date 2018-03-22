package com.mtech.parttimeone.photolearn.handler;

import com.mtech.parttimeone.photolearn.bo.AccountBO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Leon
 * @date 22/3/18
 */

public class LifeCycleHandler {

    private static LifeCycleHandler handler = null;
    private static final Logger logger = LogManager.getLogger(LifeCycleHandler.class);
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
}
