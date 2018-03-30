package com.mtech.parttimeone.photolearn.ViewModel;

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
    private AccountMapper mapper = new AccountMapper();
    private AccountRepository repository = new AccountRepository();
    private DatabaseReference mDatabaseReference;
    private AccountBO account = new AccountBO();

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void getAccount(FirebaseUser user) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AccountEntity eAccount = dataSnapshot.getValue(AccountEntity.class);

                // means that it is a new user
                if (eAccount == null) {
                    eAccount = createAccount(user);
                }
                storeSessionAccountDetails(eAccount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
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

    public void signIn(FirebaseUser user) {
        getAccount(user);
    }

    private AccountEntity createAccount(FirebaseUser user) {
        AccountEntity accountModel = new AccountEntity();

        accountModel.setLastActive("TRAINER");
        accountModel.setUserId(user.getUid());
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

        AccountBO accountBO = mapper.map(accountModel);
        setAccount(accountBO);

        return accountModel;
    }
}
