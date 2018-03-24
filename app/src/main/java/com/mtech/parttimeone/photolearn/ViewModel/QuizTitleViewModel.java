package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.data.repository.QuizTitleRepository;
import com.mtech.parttimeone.photolearn.data.entity.QuizTitleEntity;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleViewModel extends ViewModel {
    private MutableLiveData<List<QuizTitleEntity>> quiztitles;
    private QuizTitleRepository repository = new QuizTitleRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<List<QuizTitleEntity>> getQuizTitles() {
        if (quiztitles == null) {
            quiztitles = new MutableLiveData<>();
            loadQuizTitles();
        }
        return quiztitles;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadQuizTitles() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<QuizTitleEntity>() {
            @Override
            public void onSuccess(List<QuizTitleEntity> result) {
                quiztitles.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                quiztitles.setValue(null);
            }
        });
    }

    public boolean setQuizTitle(QuizTitleEntity mQuizTitle) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(mQuizTitle.getSessionId()).setValue(mQuizTitle);

        return true;
    }
}
