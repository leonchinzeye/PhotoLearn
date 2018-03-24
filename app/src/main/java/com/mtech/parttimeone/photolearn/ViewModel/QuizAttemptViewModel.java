package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.data.repository.QuizAttemptRepository;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;

import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptViewModel extends ViewModel {
    private MutableLiveData<List<QuizAttemptEntity>> quizattempts;
    private QuizAttemptRepository repository = new QuizAttemptRepository();
    private DatabaseReference mDatabaseReference;

    public LiveData<List<QuizAttemptEntity>> getQuizAttempts() {
        if (quizattempts == null) {
            quizattempts = new MutableLiveData<>();
            loadQuizAttempts();
        }
        return quizattempts;
    }

    @Override
    protected void onCleared() {
        repository.removeListener();
    }

    private void loadQuizAttempts() {
        repository.addListener(new FirebaseDatabaseRepository.FirebaseDatabaseRepositoryCallback<QuizAttemptEntity>() {
            @Override
            public void onSuccess(List<QuizAttemptEntity> result) {
                quizattempts.setValue(result);
            }

            @Override
            public void onError(Exception e) {
                quizattempts.setValue(null);
            }
        });
    }

    public boolean setQuizAttempt(QuizAttemptEntity mQuizAttempt) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(repository.getRootNode());
        mDatabaseReference.child(mQuizAttempt.getUserId()).setValue(mQuizAttempt);

        return true;
    }
}
