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
    private MutableLiveData<QuizTitleBO> quizTitleBO;

    /** This points to the collection of Quiz titles **/
    private QuizTitleRepository quizTitleRepository = new QuizTitleRepository();

    /** This points to the collection of user titles **/
    private UserTitleRepository userTitleRepository = new UserTitleRepository();

    private DatabaseReference mQuizTitleRef;
    private DatabaseReference mUserTitleRef;

    /**
     * This method loads just a single Quiz title
     * @param sessionId
     * @return QuiztitleBO
     */
    private LiveData<QuizTitleBO> getQuizTitle(String sessionId) {
        if (quizTitleBO == null) {
            quizTitleBO = new MutableLiveData<QuizTitleBO>();
            loadQuizTitle(sessionId);
        }
        return quizTitleBO;
    }

    /**
     * This method loads just all Quiz titles
     * @param userId
     * @param sessionId
     * @return list of QuiztitleBO
     */
    public LiveData<List<QuizTitleBO>> getQuizTitles(String sessionId, String userId) {
        if (sessionId!=""){
            loadAllQuizTitles(sessionId);
        } else {
            loadTrainerQuizTitles(userId);
        }
        return quizTitleBOs;
    }

    @Override
    protected void onCleared() {
        quizTitleRepository.removeListener();
        userTitleRepository.removeListener();
    }

    public void loadQuizTitle(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizTitleEntity eQuizTitle = dataSnapshot.getValue(QuizTitleEntity.class);

                if (eQuizTitle!= null) {
                    QuizTitleMapper mapper = new QuizTitleMapper();
                    quizTitleBO.setValue(mapper.map(eQuizTitle));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    /**
     * Creates a Quiz title
     * @param quizTitleBO
     * @param userId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void createQuizTitle(QuizTitleBO quizTitleBO, String sessionId, String userId) throws Exception {
        //inserts Quiz_titles
        setQuizTitle(quizTitleBO, sessionId);
        //inserts user_titles -> trainers
        setTrainerQuizTitle(quizTitleBO, sessionId, userId);
    }

    // This method is for trainers to delete a Quiz title
    public void deleteQuizTitle(String sessionId, String userId) {
        //deletes Quiz_titles
        //deletes user_titles -> trainers
        removeQuizTitle(sessionId, userId);
    }

    // TODO
    // This is for trainers to update a Quiz session
    public QuizTitleBO updateQuizTitle(QuizTitleBO quizTitleBO, String sessionId , String userId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mQuizTitleRef.child(sessionId).setValue(quizTitleBO);

        //update trainer session data
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("trainers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        if(sessionSnapshot.hasChild(sessionId)) {
                            sessionSnapshot.child(sessionId).getRef().setValue(quizTitleBO);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });

        return quizTitleBO;
    }

    //this loads all titles based on sessionId
    private void loadAllQuizTitles(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addValueEventListener(new ValueEventListener() {
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
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    private void loadTrainerQuizTitles(String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("trainers").child(userId).addValueEventListener(new ValueEventListener() {
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
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    //creates Quiz titles filtered by session Id
    public boolean setQuizTitle(QuizTitleBO quizTitleBO, String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).setValue(quizTitleBO);

        return true;
    }

    //creates Quiz titles filtered by trainer user Id and stores the session Id
    public boolean setTrainerQuizTitle(QuizTitleBO quizTitleBO, String sessionId, String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("trainers").child(userId).child(sessionId).setValue(quizTitleBO);

        return true;
    }

    public boolean removeQuizTitle(String sessionId, String userId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).removeValue();

        //remove trainer links
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("trainers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot titleSnapshot : dataSnapshot.getChildren()) {
                        if(titleSnapshot.hasChild(sessionId)) {
                            titleSnapshot.child(sessionId).getRef().setValue(null);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
        return true;
    }
}
