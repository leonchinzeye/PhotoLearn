package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.data.entity.LearningSessionEntity;

import java.util.List;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionViewModel extends ViewModel {
    private MutableLiveData<List<LearningSessionEntity>> learningsessions;
    private LearningSessionRepository repository = new LearningSessionRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<List<LearningSessionEntity>> getLearningSessions() {
        if (learningsessions == null) {
            learningsessions = new MutableLiveData<>();
            loadLearningSessions();
        }
        return learningsessions;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadLearningSessions() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<LearningSessionEntity>() {
            @Override
            public void onSuccess(List<LearningSessionEntity> result) {
                learningsessions.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                learningsessions.setValue(null);
            }
        });
    }

    public boolean setLearningSession(LearningSessionEntity mLearningSession) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(mLearningSession.getSessionId()).setValue(mLearningSession);

        return true;
    }
}
