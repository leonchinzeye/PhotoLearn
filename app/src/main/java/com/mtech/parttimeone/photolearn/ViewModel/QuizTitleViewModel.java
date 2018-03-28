package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizTitleEntity;
import com.mtech.parttimeone.photolearn.data.mapper.QuizTitleMapper;
import com.mtech.parttimeone.photolearn.data.repository.QuizTitleRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserTitleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleViewModel extends ViewModel {
    private MutableLiveData<List<QuizTitleBO>> quizTitleBOs;
    private List<QuizTitleBO> quizTitles;
    private QuizTitleBO quizTitle;
    private boolean hasLoadedQuizTitles = false;
    private boolean hasLoadedQuizTitle = false;

    /** This points to the collection of Quiz titles **/
    private QuizTitleRepository quizTitleRepository = new QuizTitleRepository();

    /** This points to the collection of user titles **/
    private UserTitleRepository userTitleRepository = new UserTitleRepository();

    private DatabaseReference mQuizTitleRef;
    private DatabaseReference mUserTitleRef;

    private void getQuizTitle(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizTitleEntity eQuizTitle = dataSnapshot.getValue(QuizTitleEntity.class);

                if (eQuizTitle!= null) {
                    QuizTitleMapper mapper = new QuizTitleMapper();
                    quizTitle = mapper.map(eQuizTitle);
                    hasLoadedQuizTitle = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCleared() {
        quizTitleRepository.removeListener();
        userTitleRepository.removeListener();
    }

    /**
     * This method loads just all Quiz titles
     * @param userId
     * @param sessionId
     * @return list of QuiztitleBO
     */
    public List<QuizTitleBO> loadQuizTitles(String sessionId, String userId) {
        if (sessionId!=""){
            loadAllQuizTitles(sessionId);
        } else {
            loadParticipantQuizTitles(userId);
        }

        if (hasLoadedQuizTitles) {
            hasLoadedQuizTitles = false;
            return this.quizTitles;
        }
        else {
            return null;
        }
    }

    /**
     * This method loads just a single Quiz title
     * @param userId
     * @param sessionId
     * @return QuiztitleBO
     */
    public QuizTitleBO loadQuizTitle(String userId, String sessionId) {
        getQuizTitle(sessionId);

        if (hasLoadedQuizTitle) {
            hasLoadedQuizTitle = false;
            return this.quizTitle;
        } else {
            return null;
        }
    }

    /**
     * Creates a Quiz title as a participant
     * @param QuizTitleBO
     * @param userId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizTitle(QuizTitleBO QuizTitleBO, String sessionId, String userId) throws Exception {
        //inserts Quiz_titles
        setQuizTitle(QuizTitleBO, sessionId);
        //inserts user_titles -> participants
        setParticipantQuizTitle(QuizTitleBO, sessionId, userId);
    }

    // This method is for trainers to delete a Quiz title
    public void deleteQuizTitle(String sessionId, String userId) {
        //deletes Quiz_titles
        //deletes user_titles -> participants
        removeQuizTitle(sessionId, userId);
    }

    // TODO
    // This is for trainers to update a Quiz session
    public QuizTitleBO updateQuizTitle(QuizTitleBO QuizTitleBO, String sessionId , String userId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mQuizTitleRef.child(sessionId).setValue(QuizTitleBO);

        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mUserTitleRef.child("participants").child(userId).child(sessionId).setValue(QuizTitleBO);

        return QuizTitleBO;
    }

    //this loads all titles based on sessionId
    private void loadAllQuizTitles(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizTitleBO> listQuizTitles = new ArrayList<>();
                    QuizTitleMapper mapper = new QuizTitleMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizTitleEntity entity = sessionSnapshot.getValue(QuizTitleEntity.class);
                        QuizTitleBO QuizTitleBO = mapper.map(entity);
                        listQuizTitles.add(QuizTitleBO);
                    }

                    quizTitleBOs.setValue(listQuizTitles);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //this loads titles based on userId - required in participant edit mode
    private void loadParticipantQuizTitles(String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizTitleBO> listQuizTitles = new ArrayList<>();
                    QuizTitleMapper mapper = new QuizTitleMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizTitleEntity entity = sessionSnapshot.getValue(QuizTitleEntity.class);
                        QuizTitleBO QuizTitleBO = mapper.map(entity);
                        listQuizTitles.add(QuizTitleBO);
                    }

                    quizTitleBOs.setValue(listQuizTitles);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //creates Quiz titles filtered by session Id
    public boolean setQuizTitle(QuizTitleBO QuizTitleBO, String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).setValue(QuizTitleBO);

        return true;
    }

    //creates Quiz titles filtered by participant user Id and stores the session Id
    public boolean setParticipantQuizTitle(QuizTitleBO QuizTitleBO, String sessionId, String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).child(sessionId).setValue(QuizTitleBO);

        return true;
    }

    public boolean isHasLeadedQuizTitles() {
        return hasLoadedQuizTitles;
    }

    public void setHasLoadedQuizTitles(boolean hasLoadedQuizTitles) {
        this.hasLoadedQuizTitle = hasLoadedQuizTitle;
    }

    public boolean isHasLoadedQuizTitle() {
        return hasLoadedQuizTitle;
    }

    public void setHasLoadedQuizTitle(boolean hasLoadedQuizTitle) {
        this.hasLoadedQuizTitle = hasLoadedQuizTitle;
    }

    public boolean removeQuizTitle(String sessionId, String userId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).removeValue();

        //remove participant link
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).child(sessionId).removeValue();

        return true;
    }
}
