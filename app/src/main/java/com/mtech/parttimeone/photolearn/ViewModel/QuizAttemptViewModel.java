package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.data.mapper.QuizAttemptMapper;
import com.mtech.parttimeone.photolearn.data.repository.QuizAttemptRepository;
import com.mtech.parttimeone.photolearn.data.entity.QuizAttemptEntity;
import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizAttemptViewModel extends ViewModel {
    private MutableLiveData<List<QuizAttemptBO>> quizAttemptBOs;
    private List<QuizAttemptBO> quizAttempts;
    private QuizAttemptBO quizAttempt;
    private boolean hasLoadedQuizAttempts = false;
    private boolean hasLoadedQuizAttempt = false;

    /** This points to the collection of Quiz Attempts **/
    private QuizAttemptRepository quizAttemptRepository = new QuizAttemptRepository();

    private DatabaseReference mQuizAttemptRef;

    private void getQuizAttempt(String quizItemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(quizItemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizAttemptEntity eQuizAttempt = dataSnapshot.getValue(QuizAttemptEntity.class);

                if (eQuizAttempt!= null) {
                    QuizAttemptMapper mapper = new QuizAttemptMapper();
                    quizAttempt = mapper.map(eQuizAttempt);
                    hasLoadedQuizAttempt = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCleared() {
        quizAttemptRepository.removeListener();
    }

    /**
     * This method loads just all Quiz Attempts
     * @param userId
     * @param quizItemid
     * @return list of QuizAttemptBO
     */
    public List<QuizAttemptBO> loadQuizAttempts(String quizItemid, String userId) {
        loadAllQuizAttempts(quizItemid, userId);

        if (hasLoadedQuizAttempts) {
            hasLoadedQuizAttempts = false;
            return this.quizAttempts;
        }
        else {
            return null;
        }
    }

    /**
     * This method loads just a single Quiz Attempt
     * @param userId
     * @param quizItemId
     * @return QuizAttemptBO
     */
    public QuizAttemptBO loadQuizAttempt(String quizItemId, String userId) {
        getQuizAttempt(quizItemId, userId);

        if (hasLoadedQuizAttempt) {
            hasLoadedQuizAttempt = false;
            return this.quizAttempt;
        } else {
            return null;
        }
    }

    /**
     * Creates a Quiz Attempt as a participant
     * @param QuizAttemptBO
     * @param userId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizAttempt(QuizAttemptBO QuizAttemptBO, String quizItemId, String userId) throws Exception {
        //inserts Quiz_Attempts
        setQuizAttempt(QuizAttemptBO, quizItemId, userId);
    }

    // This method is for trainers to delete a Quiz Attempt
    public void deleteQuizAttempt(String quizItemId, String userId) {
        //deletes Quiz_Attempts
        removeQuizAttempt(quizItemId, userId);
    }

    // TODO
    // This is for trainers to update a Quiz session
    public QuizAttemptBO updateQuizAttempt(QuizAttemptBO QuizAttemptBO, String quizItemId , String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mQuizAttemptRef.child(userId).child(quizItemId).setValue(QuizAttemptBO);

        return QuizAttemptBO;
    }

    //this loads all Attempts based on quizItemId
    private void loadAllQuizAttempts(String quizItemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(quizItemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizAttemptBO> listQuizAttempts = new ArrayList<>();
                    QuizAttemptMapper mapper = new QuizAttemptMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizAttemptEntity entity = sessionSnapshot.getValue(QuizAttemptEntity.class);
                        QuizAttemptBO QuizAttemptBO = mapper.map(entity);
                        listQuizAttempts.add(QuizAttemptBO);
                    }

                    quizAttemptBOs.setValue(listQuizAttempts);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //creates Quiz Attempts filtered by session Id
    public boolean setQuizAttempt(QuizAttemptBO QuizAttemptBO, String quizItemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(quizItemId).setValue(QuizAttemptBO);

        return true;
    }

    public boolean isHasLeadedQuizAttempts() {
        return hasLoadedQuizAttempts;
    }

    public void setHasLoadedQuizAttempts(boolean hasLoadedQuizAttempts) {
        this.hasLoadedQuizAttempt = hasLoadedQuizAttempt;
    }

    public boolean isHasLoadedQuizAttempt() {
        return hasLoadedQuizAttempt;
    }

    public void setHasLoadedQuizAttempt(boolean hasLoadedQuizAttempt) {
        this.hasLoadedQuizAttempt = hasLoadedQuizAttempt;
    }

    public boolean removeQuizAttempt(String quizItemId, String userId) {
        mQuizAttemptRef = FirebaseDatabase.getInstance().getReference(quizAttemptRepository.getRootNode());
        mQuizAttemptRef.child(userId).child(quizItemId).removeValue();

        return true;
    }
}
