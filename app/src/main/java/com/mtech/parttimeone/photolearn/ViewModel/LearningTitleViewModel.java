package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.data.repository.LearningTitleRepository;
import com.mtech.parttimeone.photolearn.domain.LearningTitleEntity;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleViewModel extends ViewModel {
    private MutableLiveData<List<LearningTitleEntity>> learningtitles;
    private LearningTitleRepository repository = new LearningTitleRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<List<LearningTitleEntity>> getLearningTitles() {
        if (learningtitles == null) {
            learningtitles = new MutableLiveData<>();
            loadLearningTitles();
        }
        return learningtitles;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadLearningTitles() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<LearningTitleEntity>() {
            @Override
            public void onSuccess(List<LearningTitleEntity> result) {
                learningtitles.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                learningtitles.setValue(null);
            }
        });
    }

    public boolean setLearningTitle(LearningTitleEntity mLearningTitle) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.setValue(mLearningTitle);

        return true;
    }
}
