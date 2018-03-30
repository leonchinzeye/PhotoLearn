package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.data.entity.AccountEntity;

import org.apache.commons.lang3.StringUtils;

public class AccountMapper extends FirebaseMapper<AccountEntity, AccountBO>{
    @Override
    public AccountBO map(AccountEntity eAccount) {
        AccountBO accountBO = new AccountBO();

        if (StringUtils.isNotEmpty(eAccount.getUserId())) {
            accountBO.setUserUid(eAccount.getUserId());
        }

        if (StringUtils.isNotEmpty(eAccount.getName())) {
            accountBO.setAccountName(eAccount.getName());
        }

        if (StringUtils.isNotEmpty(eAccount.getEmail())) {
            accountBO.setEmail(eAccount.getEmail());
        }

        if (StringUtils.isNotEmpty(eAccount.getLastActive())) {
            accountBO.setLastActive(eAccount.getLastActive());
        }

        return accountBO;
    }

    @Override
    public AccountEntity mapFrom(AccountBO accountBO) {
        AccountEntity eAccount = new AccountEntity();

        if (StringUtils.isNotEmpty(accountBO.getUserUid())) {
            eAccount.setUserId(accountBO.getUserUid());
        }

        if (StringUtils.isNotEmpty(accountBO.getAccountName())) {
            eAccount.setName(accountBO.getAccountName());
        }

        if (StringUtils.isNotEmpty(accountBO.getEmail())) {
            eAccount.setEmail(accountBO.getEmail());
        }

        if (StringUtils.isNotEmpty(accountBO.getLastActive())) {
            eAccount.setLastActive(accountBO.getLastActive());
        }

        return eAccount;
    }

}
