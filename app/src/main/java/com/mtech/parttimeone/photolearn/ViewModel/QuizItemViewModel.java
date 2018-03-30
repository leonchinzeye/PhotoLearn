package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.QuizItemMapper;
import com.mtech.parttimeone.photolearn.data.repository.QuizItemRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 29/3/2018.
 */

public class QuizItemViewModel extends ViewModel {
    private MutableLiveData<List<QuizItemBO>> quizItemBOs;
    private MutableLiveData<QuizItemBO> quizItemBO;

    /* Global mapper */
    QuizItemMapper mapper = new QuizItemMapper();

    /* This points to the collection of Quiz items */
    private QuizItemRepository quizItemRepository = new QuizItemRepository();

    private DatabaseReference mQuizItemRef;

    /**
     * This method loads just a single Quiz title
     *
     * @param titleId
     * @return QuiztitleBO
     */
    private LiveData<QuizItemBO> getQuizItem(String titleId, String itemId) {
        if (quizItemBO == null) {
            quizItemBO = new MutableLiveData<QuizItemBO>();
        }
        loadQuizItem(titleId, itemId);

        return quizItemBO;
    }

    /**
     * This method loads all Quiz items
     *
     * @param titleId
     * @return QuiztitleBO
     */
    public LiveData<List<QuizItemBO>> getQuizItems(String titleId) {
        if (quizItemBOs == null) {
            quizItemBOs = new MutableLiveData<>();
        }
        loadQuizItems(titleId);

        return quizItemBOs;
    }

    @Override
    protected void onCleared() {
        quizItemRepository.removeListener();
    }

    /**
     * Creates a quiz item
     *
     * @param quizItemBO
     * @throws Exception if the quiz session is not a unique session ID
     */
    public boolean createQuizItem(QuizItemBO quizItemBO) throws Exception {
        setQuizItem(quizItemBO);
        return true;
    }

    /**
     * Deletes a Quiz item
     *
     * @param quizItemBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteQuizItem(QuizItemBO quizItemBO) {
        removeQuizItem(quizItemBO.getTitleId(), quizItemBO.getUuid());
    }

    /**
     * Deletes all Quiz items associated with a title
     *
     * @param titleId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteFromTitle(String titleId) {
        removeQuizItem(titleId);
    }

    /**
     * For trainer to update the quiz item
     *
     * @param quizItemBO
     * @return
     */
    public QuizItemBO updateQuizItem(QuizItemBO quizItemBO) {
        QuizItemEntity eQuizItem = new QuizItemEntity();
        eQuizItem = mapper.mapFrom(quizItemBO);

        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(eQuizItem.getTitleId()).child(quizItemBO.getUuid()).setValue(eQuizItem);

        return quizItemBO;
    }

    private void loadQuizItem(String titleId, String itemId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizItemEntity eQuizItem = dataSnapshot.getValue(QuizItemEntity.class);

                if (eQuizItem != null) {
                    QuizItemBO itemBO = mapper.map(eQuizItem);
                    itemBO.setUuid(itemId);
                    quizItemBO.setValue(itemBO);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadQuizItems(String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizItemBO> listQuizItems = new ArrayList<>();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizItemEntity eQuizItem = sessionSnapshot.getValue(QuizItemEntity.class);
                        QuizItemBO quizItemBO = mapper.map(eQuizItem);
                        quizItemBO.setUuid(dataSnapshot.getKey());
                        listQuizItems.add(quizItemBO);
                    }
                    quizItemBOs.setValue(listQuizItems);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    public boolean setQuizItem(QuizItemBO quizItemBO) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        QuizItemEntity eQuizItem = new QuizItemEntity();
        eQuizItem = mapper.mapFrom(quizItemBO);

        //get a unique key from firebase
        String key = mQuizItemRef.child(eQuizItem.getTitleId()).push().getKey();
        mQuizItemRef.child(eQuizItem.getTitleId()).child(key).setValue(quizItemBO);

        return true;
    }

    public boolean removeQuizItem(String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot titleSnapshot : dataSnapshot.getChildren()) {
                        String titleId = titleSnapshot.getKey();
                        QuizAttemptViewModel attemptVM = new QuizAttemptViewModel();
//                        attemptVM.deleteFromTitle(titleId);
                    }
                }
                mQuizItemRef.child(titleId).removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
        return true;
    }

    public boolean removeQuizItem(String titleId, String itemId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).child(itemId).removeValue();

        return true;
    }
}