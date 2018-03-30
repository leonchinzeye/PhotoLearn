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
import com.mtech.parttimeone.photolearn.data.mapper.QuizAttemptMapper;
import com.mtech.parttimeone.photolearn.data.repository.QuizAttemptRepository;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;
import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptViewModel extends ViewModel {
    private MutableLiveData<List<QuizAttemptBO>> quizAttemptBOs;
    private MutableLiveData<QuizAttemptBO> quizAttemptBO;

    /* Global mapper */
    QuizAttemptMapper mapper = new QuizAttemptMapper();

    /** This points to the collection of Quiz Attempts **/
    private QuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository();

    private DatabaseReference mQuizAttemptRef;

    /**
     * This method loads just a single Quiz attempt
     *
     * @param itemId
     * @param titleId
     * @return QuiztitleBO
     */
    public LiveData<QuizAttemptBO> getQuizAttempt(String itemId, String titleId) {
        if (quizAttemptBO == null) {
            quizAttemptBO = new MutableLiveData<QuizAttemptBO>();
        }
        loadQuizAttempt(itemId, titleId);

        return quizAttemptBO;
    }

    /**
     * This method loads all Quiz attempts belonging to a title
     *
     * @param sessionId
     * @return QuiztitleBO
     */
    public LiveData<List<QuizAttemptBO>> getQuizAttempts(String sessionId) {
        if (quizAttemptBOs == null) {
            quizAttemptBOs = new MutableLiveData<>();
        }
        loadQuizAttempts(sessionId,null);

        return quizAttemptBOs;
    }

    /**
     * This method loads all Quiz attempts that the user created belonging to {@Code sessionId
     *
     * @param userId
     * @param sessionId
     * @return list of QuiztitleBO
     */
    public LiveData<List<QuizAttemptBO>> getQuizAttempts(String sessionId, String userId) {
        if (quizAttemptBOs == null) {
            quizAttemptBOs = new MutableLiveData<>();
        }
        loadQuizAttempts(sessionId, userId);

        return quizAttemptBOs;
    }

    @Override
    protected void onCleared() {
        quizAttemptRepository.removeListener();
    }

    /**
     * Creates a Quiz Attempt as a participant
     * @param QuizAttemptBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizAttempt(QuizAttemptBO quizAttemptBO) throws Exception {
        QuizAttemptEntity eQuizAttempt = new QuizAttemptEntity();
        eQuizAttempt = mapper.mapFrom(quizAttemptBO);

        setQuizAttempt(eQuizAttempt);
    }

    /**
     * Deletes a Quiz attempt
     *
     * @param quizAttemptBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteQuizAttempt(QuizAttemptBO quizAttemptBO) {
        removeQuizAttempt(quizAttemptBO.getItemId(), quizAttemptBO.getAttemptId());
    }

    /**
     * Deletes all quiz attempts associated with a item
     *
     * @param itemId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteFromSession(String itemId) {
        removeQuizTitle(itemId);
    }

    /**
     * For participant to update the quiz attempt
     *
     * @param quizAttemptBO
     * @return
     */
    public QuizAttemptBO updateQuizAttempt(QuizAttemptBO quizAttemptBO) {
        QuizAttemptEntity eQuizAttempt = mapper.mapFrom(quizAttemptBO);

        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(eQuizAttempt.getUserId()).child(eQuizAttempt.getItemId()).setValue(quizAttemptBO);

        return quizAttemptBO;
    }

    private void loadQuizAttempt(String itemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizAttemptEntity eQuizAttempt = dataSnapshot.getValue(QuizAttemptEntity.class);

                if (eQuizAttempt != null) {
                    QuizAttemptBO attemptBO = mapper.map(eQuizAttempt);
                    quizAttemptBO.setValue(attemptBO);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }


    private void loadQuizAttempts(String itemId, String userId) {
        if (StringUtils.isNotEmpty(itemId) && StringUtils.isNotEmpty(userId)) {
            loadItemUserQuizTitles(itemId, userId);
        }
    }

    private void loadItemUserQuizTitles(String itemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizAttemptBO> listQuizTitles = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        QuizAttemptEntity eAttempt = snapshot.getValue(QuizAttemptEntity.class);
                        if (StringUtils.equals(itemId, eAttempt.getItemId())) {
                            QuizAttemptBO eQuizAttempt = mapper.map(eAttempt);
                            eQuizAttempt.setItemId(snapshot.getKey());
                            listQuizTitles.add(eQuizAttempt);
                        }
                    }
                    quizAttemptBOs.setValue(listQuizTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }


    //creates Quiz Attempts filtered by session Id
    public boolean setQuizAttempt(QuizAttemptEntity eQuizAttempt) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        //get a unique key from firebase
        String key = mQuizAttemptRef.child(eQuizAttempt.getUserId()).push().getKey();
        //set titleId to generated key
        eQuizAttempt.setItemId(key);
        mQuizAttemptRef.child(eQuizAttempt.getUserId()).child(eQuizAttempt.getItemId()).setValue(eQuizAttempt);

        return true;
    }

    public boolean removeQuizTitle(String itemId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                        if (itemSnapshot.hasChild(itemId)) {
                            itemSnapshot.child(itemId).getRef().setValue(null);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
        return true;
    }

    public boolean removeQuizAttempt(String quizItemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(quizItemId).removeValue();

        return true;
    }
}
