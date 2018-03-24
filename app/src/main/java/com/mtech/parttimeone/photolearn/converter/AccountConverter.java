package com.mtech.parttimeone.photolearn.converter;

import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.data.model.AccountModel;

/**
 * @author lechin
 * @date 3/24/18
 */

public class AccountConverter implements IConverter<AccountModel, AccountBO> {

    @Override
    public AccountModel mapFrom(AccountBO accountBO) {
        AccountModel accountModel = new AccountModel();

        accountModel.setUserUid(accountBO.getUid());
        accountModel.setName(accountBO.getAccountName());

        return accountModel;
    }

    @Override
    public AccountBO mapTo(AccountModel accountModel) {
        AccountBO accountBO = new AccountBO();

        accountBO.setAccountName(accountModel.getName());
        accountBO.setUid(accountModel.getUserUid());

        return accountBO;
    }
}
