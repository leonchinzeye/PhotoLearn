package com.mtech.parttimeone.photolearn.data.mapper;

import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.data.entity.AccountEntity;

public class AccountMapper extends FirebaseMapper<AccountEntity, AccountBO>{
    @Override
    public AccountBO map(AccountEntity AccountEntity) {
        AccountBO accountBO = new AccountBO();//("","","","");
        //accountEntity.setUserId(AccountBO.getUserId);
        accountBO.setAccountName(AccountEntity.getName());
        //accountEntity.setEmail(AccountBO.getEmail());
        //accountEntity.setLastActive(AccountBO.getLastActive());
        return accountBO;
    }
}
