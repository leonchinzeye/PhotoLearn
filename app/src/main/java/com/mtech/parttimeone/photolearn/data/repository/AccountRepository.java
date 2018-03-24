package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.AccountMapper;
import com.mtech.parttimeone.photolearn.bo.AccountBO;

public class AccountRepository extends FirebaseDatabaseRepository<AccountBO> {
    public AccountRepository() {
        super (new AccountMapper());
    }

    @Override
    public String getRootNode() {
        return "accounts";
    }
}
