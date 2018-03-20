package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.data.model.AccountModel;
import com.mtech.parttimeone.photolearn.domain.Account;

public class AccountMapper extends FirebaseMapper<AccountModel, Account>{
    @Override
    public Account map(AccountModel accountModel) {
        Account account = new Account(accountModel.getUserUid(),accountModel.getName(),accountModel.getEmail(),accountModel.getLastActive());

        return account;
    }
}
