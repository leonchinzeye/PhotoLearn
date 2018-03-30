package com.mtech.parttimeone.photolearn.bo;

import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.math.BigInteger;

/**
 * @author Leon
 * @date 3/12/18
 */

public class AccountBO {
    private String userUid;
    private String accountName;
    private String email;
    private String lastActive;

    public AccountBO() {

    }

    public AccountBO(String userUid, String name, String email, String lastActive) {
        this.userUid = userUid;
        this.accountName = name;
        this.email = email;
        this.lastActive = lastActive;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }
}
