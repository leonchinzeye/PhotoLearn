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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class QuizTitleViewModel extends ViewModel {
    private MutableLiveData<List<QuizTitleBO>> quizTitleBOs;
    private MutableLiveData<QuizTitleBO> quizTitleBO;

    /* Global mapper */
    private QuizTitleMapper mapper = new QuizTitleMapper();

    /* This points to the collection of Quiz titles */
    private QuizTitleRepository quizTitleRepository = new QuizTitleRepository();

    private DatabaseReference mQuizTitleRef;

    /**
     * This method loads just a single Quiz title
     *
     * @param sessionId
     * @param titleId
     * @return QuiztitleBO
     */
    public LiveData<QuizTitleBO> getQuizTitle(String sessionId, String titleId) {
        if (quizTitleBO == null) {
            quizTitleBO = new MutableLiveData<QuizTitleBO>();
        }
        loadQuizTitle(sessionId, titleId);

        return quizTitleBO;
    }

    /**
     * This method loads all Quiz titles belonging to a session
     *
     * @param sessionId
     * @return QuiztitleBO
     */
    public LiveData<List<QuizTitleBO>> getQuizTitles(String sessionId) {
        if (quizTitleBOs == null) {
            quizTitleBOs = new MutableLiveData<>();
        }
        loadQuizTitles(sessionId,null);

        return quizTitleBOs;
    }

    /**
     * This method loads all Quiz titles that the user created belonging to {@Code sessionId
     *
     * @param userId
     * @param sessionId
     * @return list of QuiztitleBO
     */
    public LiveData<List<QuizTitleBO>> getQuizTitles(String sessionId, String userId) {
        if (quizTitleBOs == null) {
            quizTitleBOs = new MutableLiveData<>();
        }
        loadQuizTitles(sessionId, userId);

        return quizTitleBOs;
    }

    @Override
    protected void onCleared() {
        quizTitleRepository.removeListener();
    }

    /**
     * Creates a Quiz title
     *
     * @param quizTitleBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public boolean createQuizTitle(QuizTitleBO quizTitleBO) throws Exception {
        setQuizTitle(quizTitleBO);
        return true;
    }

    /**
     * Deletes a Quiz title
     *
     * @param quizTitleBO
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteQuizTitle(QuizTitleBO quizTitleBO) {
        removeQuizTitle(quizTitleBO.getSessionId(), quizTitleBO.getUuid());
    }

    /**
     * Deletes all quiz titles associated with a session
     *
     * @param sessionId
     * @throws Exception if the Quiz session is not a unique session ID
     */
    public void deleteFromSession(String sessionId) {
        removeQuizTitle(sessionId);
    }

    /**
     * For trainer to update the quiz title
     *
     * @param quizTitleBO
     * @return
     */
    public QuizTitleBO updateQuizTitle(QuizTitleBO quizTitleBO) {
        QuizTitleEntity eQuizTitle = mapper.mapFrom(quizTitleBO);

        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(eQuizTitle.getSessionId()).child(quizTitleBO.getUuid()).setValue(eQuizTitle);

        return quizTitleBO;
    }

    private void loadQuizTitle(String sessionId, String titleId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).child(titleId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizTitleEntity eQuizTitle = dataSnapshot.getValue(QuizTitleEntity.class);

                if (eQuizTitle != null) {
                    QuizTitleBO titleBO = mapper.map(eQuizTitle);
                    titleBO.setUuid(dataSnapshot.getKey());
                    quizTitleBO.setValue(titleBO);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadQuizTitles(String sessionId, String userId) {
        if (StringUtils.isNotEmpty(sessionId) && StringUtils.isEmpty(userId)) {
            loadSessionQuizTitles(sessionId);
            return;
        }

        if (StringUtils.isNotEmpty(sessionId) && StringUtils.isNotEmpty(userId)) {
            loadSessionUserQuizTitles(sessionId, userId);
        }

        if (StringUtils.isEmpty(sessionId) && StringUtils.isEmpty(userId)) {
            quizTitleBOs = new MutableLiveData<List<QuizTitleBO>>();
            return;
        }
    }

    private void loadSessionQuizTitles(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizTitleBO> listQuizTitles = new ArrayList<>();
                    
                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizTitleEntity eQuizTitle = sessionSnapshot.getValue(QuizTitleEntity.class);
                        QuizTitleBO QuizTitleBO = mapper.map(eQuizTitle);
                        QuizTitleBO.setUuid(dataSnapshot.getKey());
                        listQuizTitles.add(QuizTitleBO);
                    }
                    quizTitleBOs.setValue(listQuizTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadSessionUserQuizTitles(String sessionId, String userId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizTitleBO> listQuizTitles = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        QuizTitleEntity eTitle = snapshot.getValue(QuizTitleEntity.class);
                        if (StringUtils.equals(userId, eTitle.getCreatedBy())) {
                            QuizTitleBO eQuizTitle = mapper.map(eTitle);
                            eQuizTitle.setUuid(snapshot.getKey());
                            listQuizTitles.add(eQuizTitle);
                        }
                    }
                    quizTitleBOs.setValue(listQuizTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    public boolean setQuizTitle(QuizTitleBO quizTitleBO) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        QuizTitleEntity eQuizTitle = new QuizTitleEntity();
        eQuizTitle = mapper.mapFrom(quizTitleBO);

        //get a unique key from firebase
        String key = mQuizTitleRef.child(eQuizTitle.getSessionId()).push().getKey();
        mQuizTitleRef.child(eQuizTitle.getSessionId()).child(key).setValue(eQuizTitle);

        return true;
    }

    public boolean removeQuizTitle(String sessionId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot titleSnapshot : dataSnapshot.getChildren()) {
                        String titleId = titleSnapshot.getKey();
                        QuizItemViewModel itemVM = new QuizItemViewModel();
//                        itemVM.deleteFromTitle(titleId);
                        }
                    }
                    mQuizTitleRef.child(sessionId).removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
        return true;
    }

    public boolean removeQuizTitle(String sessionId, String titleId) {
        mQuizTitleRef = FirebaseDatabase.getInstance().getReference(quizTitleRepository.getRootNode());
        mQuizTitleRef.child(sessionId).child(titleId).removeValue();

        return true;
    }
}
