package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.FirebaseDatabase;
import com.mtech.parttimeone.photolearn.data.repository.AccountRepository;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

    public class AccountViewModel extends ViewModel {
    private MutableLiveData<List<AccountBO>> accounts;
    private AccountRepository repository = new AccountRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<List<AccountBO>> getAccounts() {
        if (accounts == null) {
            accounts = new MutableLiveData<>();
            loadAccounts();
        }
        return accounts;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadAccounts() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<AccountBO>() {
            @Override
            public void onSuccess(List<AccountBO> result) {
                accounts.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                accounts.setValue(null);
            }
        });
    }

    public boolean setAccount(AccountEntity mAccountEntity) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(mAccount.getUserUid()).setValue(mAccountEntity);

        return true;
    }
}
