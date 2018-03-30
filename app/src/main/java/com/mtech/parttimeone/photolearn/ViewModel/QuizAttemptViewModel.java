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
import com.mtech.parttimeone.photolearn.bo.QuizItemAttemptBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemAttemptEntity;
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
     * @param titleId
     * @return QuiztitleBO
     */
    public LiveData<List<QuizAttemptBO>> getQuizAttempts(String titleId) {
        if (quizAttemptBOs == null) {
            quizAttemptBOs = new MutableLiveData<>();
        }
        loadQuizAttempts(titleId,null);

        return quizAttemptBOs;
    }

    /**
     * This method loads all Quiz attempts that the user created belonging to {@Code sessionId
     *
     * @param userId
     * @param titleId
     * @return list of QuiztitleBO
     */
    public LiveData<List<QuizAttemptBO>> getQuizAttempts(String titleId, String userId) {
        if (quizAttemptBOs == null) {
            quizAttemptBOs = new MutableLiveData<>();
        }
        loadQuizAttempts(titleId, userId);

        return quizAttemptBOs;
    }

    @Override
    protected void onCleared() {
        quizAttemptRepository.removeListener();
    }

    /**
     * Creates a Quiz Attempt as a participant
     * @param quizAttemptBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizAttempt(QuizAttemptBO quizAttemptBO) throws Exception {
        setQuizAttempt(quizAttemptBO);
    }

    /**
     * Creates a list of Quiz Attempts as a participant
     * @param quizAttemptBOs
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizAttempt(List<QuizAttemptBO> quizAttemptBOs) throws Exception {
        for (int i = 0; i < quizAttemptBOs.size(); i++) {
            setQuizAttempt(quizAttemptBOs.get(i));
        }
    }

    /**
     * Deletes a Quiz attempt
     *
     * @param quizAttemptBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteQuizAttempts(QuizAttemptBO quizAttemptBO) {
        QuizItemAttemptBO quizItemAttemptBO = new QuizItemAttemptBO();
        for (int i = 0; i < quizAttemptBO.getAttemptBOList().size() ; i++) {
            quizItemAttemptBO = quizAttemptBO.getAttemptBOList().get(i);
            removeQuizAttempt(quizItemAttemptBO.getItemId(), quizItemAttemptBO.getAttemptId());
        }
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
        QuizItemAttemptEntity eQuizItemAttempt = new QuizItemAttemptEntity();

        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        for (int i = 0; i < eQuizAttempt.getAttemptEntityList().size() ; i++) {
            eQuizItemAttempt = eQuizAttempt.getAttemptEntityList().get(i);
            mQuizAttemptRef.child(eQuizAttempt.getUserId()).child(eQuizItemAttempt.getItemId()).child(eQuizItemAttempt.getAttemptId()).setValue(quizAttemptBO);
        }
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

                    QuizItemAttemptEntity eItemAttempt = new QuizItemAttemptEntity();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        QuizAttemptEntity eAttempt = snapshot.getValue(QuizAttemptEntity.class);
                        for (int i = 0; i < eAttempt.getAttemptEntityList().size(); i++) {
                            eItemAttempt = eAttempt.getAttemptEntityList().get(i);
                            if (StringUtils.equals(itemId, eItemAttempt.getItemId())) {
                                QuizAttemptBO eQuizAttempt = mapper.map(eAttempt);
                                eItemAttempt.setItemId(snapshot.getKey());
                                listQuizTitles.add(eQuizAttempt);
                            }
                        }
                    }
                    quizAttemptBOs.setValue(listQuizTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }


    //creates Quiz Attempts filtered by session Id
    public boolean setQuizAttempt(QuizAttemptBO quizAttemptBO) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        QuizAttemptEntity eQuizAttempt = new QuizAttemptEntity();
        QuizItemAttemptEntity eItemAttempt = new QuizItemAttemptEntity();
        eQuizAttempt = mapper.mapFrom(quizAttemptBO);

        //get a unique key from firebase
        String key = mQuizAttemptRef.child(eQuizAttempt.getUserId()).push().getKey();
        for (int i = 0; i < eQuizAttempt.getAttemptEntityList().size(); i++) {
            eItemAttempt = eQuizAttempt.getAttemptEntityList().get(i);
            mQuizAttemptRef.child(eQuizAttempt.getUserId()).child(eItemAttempt.getItemId()).child(key).setValue(eQuizAttempt);
        }
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
