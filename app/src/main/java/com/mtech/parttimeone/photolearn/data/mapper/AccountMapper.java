package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.domain.AccountEntity;

public class AccountMapper extends FirebaseMapper<AccountBO, AccountEntity>{
    @Override
    public AccountEntity map(AccountBO AccountBO) {
        AccountEntity accountEntity = new AccountEntity("","","");
        accountEntity.setName(AccountBO.getAccountName());
        //accountEntity.setEmail(AccountBO.getEmail());
        //accountEntity.setLastActive(AccountBO.getLastActive());
        return accountEntity;
    }
}
