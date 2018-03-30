package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.entity.AccountEntity;
import com.mtech.parttimeone.photolearn.data.mapper.AccountMapper;

public class AccountRepository extends FirebaseDatabaseRepository<AccountEntity> {
    public AccountRepository() {
        super(new AccountMapper());
    }

    @Override
    public String getRootNode() {
        return "accounts";
    }

    public String getAccount() {
        return null;
    }
}
