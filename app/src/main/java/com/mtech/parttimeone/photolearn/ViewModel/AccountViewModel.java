package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.bo.ParticipantBO;
import com.mtech.parttimeone.photolearn.bo.TrainerBO;
import com.mtech.parttimeone.photolearn.converter.AccountConverter;
import com.mtech.parttimeone.photolearn.data.model.AccountModel;
import com.mtech.parttimeone.photolearn.data.repository.AccountRepository;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.handler.LifeCycleHandler;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<List<AccountModel>> accounts;
    private AccountRepository repository = new AccountRepository();
    private DatabaseReference mDatabaseReference;
    private AccountModel account = null;

    public LiveData<List<AccountModel>> getAccounts() {
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
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<AccountModel>() {
            @Override
            public void onSuccess(List<AccountModel> result) {
                accounts.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                accounts.setValue(null);
            }
        });
    }

    private void getAccount(FirebaseUser user) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                account = dataSnapshot.getValue(AccountModel.class);

                // means that it is a new user
                if (account == null) {
                    account = createAccount(user);
                }
                storeSessionAccountDetails(account);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void storeSessionAccountDetails(AccountModel account) {
        LifeCycleHandler handler = LifeCycleHandler.getInstance();

        AccountConverter mapper = new AccountConverter();

        AccountBO accountBO = mapper.mapTo(account);
        accountBO.setTrainerBO(new TrainerBO());
        accountBO.setParticipantBO(new ParticipantBO());

        handler.setAccountBO(accountBO);
    }

    public void setAccount(AccountModel mAccount) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(mAccount.getUserUid()).setValue(mAccount);
    }

    public void signIn(FirebaseUser user) {
        getAccount(user);
    }

    private AccountModel createAccount(FirebaseUser user) {
        AccountModel accountModel = new AccountModel();

        accountModel.setLastActive("TRAINER");
        accountModel.setUserUid(user.getUid());

        if (StringUtils.isNotEmpty(user.getEmail())) {
            accountModel.setEmail(user.getEmail());
        } else {
            accountModel.setEmail("");
        }

        if (StringUtils.isNotEmpty(user.getDisplayName())) {
            accountModel.setName(user.getDisplayName());
        } else {
            accountModel.setName("");
        }

        setAccount(accountModel);
        return accountModel;
    }
}
