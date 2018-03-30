package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.AccountBO;
import com.mtech.parttimeone.photolearn.data.entity.AccountEntity;
import com.mtech.parttimeone.photolearn.data.mapper.AccountMapper;
import com.mtech.parttimeone.photolearn.data.repository.AccountRepository;
import com.mtech.parttimeone.photolearn.handler.LifeCycleHandler;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<List<AccountEntity>> accounts;
    private MutableLiveData<AccountBO> account;

    private AccountMapper mapper = new AccountMapper();
    private AccountRepository repository = new AccountRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<AccountBO> getAccount(FirebaseUser user) {
        if (account == null) {
            account = new MutableLiveData<>();
            loadAccount(user);
        }
        return account;
    }

    private void loadAccount(FirebaseUser user) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountEntity eAccount = dataSnapshot.getValue(AccountEntity.class);

                // means that it is a new user
                if (eAccount == null) {
                    eAccount = createAccount(user);
                }

                account.setValue(mapper.map(eAccount));

                storeSessionAccountDetails(eAccount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void storeSessionAccountDetails(AccountEntity account) {
        LifeCycleHandler handler = LifeCycleHandler.getInstance();

        AccountBO accountBO = mapper.map(account);
        //accountBO.setTrainerBO(new TrainerBO());
        //accountBO.setParticipantBO(new ParticipantBO());

        handler.setAccountBO(accountBO);
    }

    public void setAccount(AccountBO mAccount) {
        AccountEntity eAccount;
        eAccount = mapper.mapFrom(mAccount);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(eAccount.getUserId()).setValue(mAccount);
    }

    public void updateAccount(AccountBO mAccount) {
        AccountEntity eAccount;
        eAccount = mapper.mapFrom(mAccount);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(eAccount.getUserId()).child("lastActive").setValue(eAccount.getLastActive());
    }

    private AccountEntity createAccount(FirebaseUser user) {
        AccountEntity eAccount = new AccountEntity();

        eAccount.setLastActive("TRAINER");
        eAccount.setUserId(user.getUid());
        if (StringUtils.isNotEmpty(user.getEmail())) {
            eAccount.setEmail(user.getEmail());
        } else {
            eAccount.setEmail("");
        }

        if (StringUtils.isNotEmpty(user.getDisplayName())) {
            eAccount.setName(user.getDisplayName());
        } else {
            eAccount.setName("");
        }

        AccountBO accountBO = mapper.map(eAccount);
        setAccount(accountBO);

        return eAccount;
    }
}
