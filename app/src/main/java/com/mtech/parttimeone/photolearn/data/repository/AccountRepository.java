package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.AccountMapper;
import com.mtech.parttimeone.photolearn.data.model.AccountModel;

public class AccountRepository extends FirebaseDatabaseRepository<AccountModel> {
    public AccountRepository() {
        super (new AccountMapper());
    }

    @Override
    public String getRootNode() {
        return "accounts";
    }

    public String getAccount() {
        return null;
    }
}
