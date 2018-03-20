package com.mtech.parttimeone.photolearn.data.repository;

import com.mtech.parttimeone.photolearn.data.mapper.AccountMapper;
import com.mtech.parttimeone.photolearn.domain.Account;

public class AccountRepository extends FirebaseDatabaseRepository<Account> {
    public AccountRepository() {
        super (new AccountMapper());
    }

    @Override
    public String getRootNode() {
        return "accounts";
    }
}
