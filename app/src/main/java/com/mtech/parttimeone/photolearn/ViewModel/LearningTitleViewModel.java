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
import com.mtech.parttimeone.photolearn.bo.LearningTitleBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningTitleEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningTitleMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningTitleRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserTitleRepository;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleViewModel extends ViewModel {

    /* For front end usage */
    private MutableLiveData<List<LearningTitleBO>> learningTitleBOs;
    private MutableLiveData<LearningTitleBO> learningTitleBO;

    /* Global mapper */
    private LearningTitleMapper mapper = new LearningTitleMapper();

    /* This points to the collection of learning titles */
    private LearningTitleRepository learningTitleRepository = new LearningTitleRepository();

    private DatabaseReference mLearningTitleRef;

    /**
     * This method loads just a single learning title
     *
     * @param sessionId
     * @param titleId
     * @return
     */
    public LiveData<LearningTitleBO> getLearningTitle(String sessionId, String titleId) {
        if (learningTitleBO == null) {
            learningTitleBO = new MutableLiveData<LearningTitleBO>();
        }
        loadLearningTitle(sessionId, titleId);
        return learningTitleBO;
    }

    /**
     * For loading all the titles belonging to a session
     *
     * @param sessionId
     * @return
     */
    public LiveData<List<LearningTitleBO>> getLearningTitles(String sessionId) {
        if (learningTitleBOs == null) {
            learningTitleBOs = new MutableLiveData<>();
        }
        loadLearningTitles(sessionId, null);
        return learningTitleBOs;
    }

    /**
     * This method loads all the titles that the user created belonging to {@code sessionId}
     *
     * @param userId
     * @param sessionId
     * @return list of learningtitleBO
     */
    public LiveData<List<LearningTitleBO>> getLearningTitles(String sessionId, String userId) {
        if (learningTitleBOs == null) {
            learningTitleBOs = new MutableLiveData<>();
        }
        loadLearningTitles(sessionId, userId);
        return learningTitleBOs;
    }

    /**
     * Creates a learning title as a participant
     *
     * @param learningTitleBO
     */
    public void createLearningTitle(LearningTitleBO learningTitleBO) {
        addLearningTitle(learningTitleBO);
    }

    /**
     * For participant to delete learning title
     *
     * @param titleBO
     */
    public void deleteLearningTitle(LearningTitleBO titleBO) {
        removeLearningTitle(titleBO.getSessionId(), titleBO.getUuid());
    }

    /**
     * For deletion of entire session
     *
     * @param sessionId
     */
    public void deleteFromSession(String sessionId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot entity : dataSnapshot.getChildren()) {
                        String titleId = entity.getKey();
                        LearningItemViewModel itemVM = new LearningItemViewModel();
                        itemVM.deleteFromTitle(titleId);
                    }
                }
                mLearningTitleRef.child(sessionId).removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    /**
     * For user to update the learning title
     *
     * @param learningTitleBO
     * @return
     */
    public LearningTitleBO updateLearningTitle(LearningTitleBO learningTitleBO) {
        LearningTitleEntity eTitle = mapper.mapFrom(learningTitleBO);

        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(learningTitleBO.getSessionId()).child(learningTitleBO.getUuid()).setValue(eTitle);

        return learningTitleBO;
    }

    private void loadLearningTitles(String sessionId, String userId) {
        if (StringUtils.isEmpty(sessionId) && StringUtils.isNotEmpty(userId)) {
            loadParticipantLearningTitles(userId);
            return;
        }

        if (StringUtils.isNotEmpty(sessionId) && StringUtils.isEmpty(userId)) {
            loadSessionLearningTitles(sessionId);
            return;
        }

        if (StringUtils.isNotEmpty(sessionId) && StringUtils.isNotEmpty(userId)) {
            loadSessionUserLearningTitles(sessionId, userId);
            return;
        }

        if (StringUtils.isEmpty(sessionId) && StringUtils.isEmpty(userId)) {
            learningTitleBOs = new MutableLiveData<List<LearningTitleBO>>();
            return;
        }
    }

    private void loadSessionLearningTitles(String sessionId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningTitleBO> listTitles = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LearningTitleEntity eTitle = snapshot.getValue(LearningTitleEntity.class);
                        LearningTitleBO titleBO = mapper.map(eTitle);
                        titleBO.setUuid(snapshot.getKey());
                        listTitles.add(titleBO);
                    }
                    learningTitleBOs.setValue(listTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void addLearningTitle(LearningTitleBO learningTitleBO) {
        LearningTitleEntity eTitle = mapper.mapFrom(learningTitleBO);
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());

        String uuid = mLearningTitleRef.child(learningTitleBO.getSessionId()).push().getKey();
        mLearningTitleRef.child(learningTitleBO.getSessionId()).child(uuid).setValue(eTitle);
        learningTitleBO.setUuid(uuid);
    }

    private void loadSessionUserLearningTitles(String sessionId, String userId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningTitleBO> listTitles = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LearningTitleEntity eTitle = snapshot.getValue(LearningTitleEntity.class);
                        if (StringUtils.equals(userId, eTitle.getCreatedBy())) {
                            LearningTitleBO titleBO = mapper.map(eTitle);
                            titleBO.setUuid(snapshot.getKey());
                            listTitles.add(titleBO);
                        }
                    }
                    learningTitleBOs.setValue(listTitles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        learningTitleRepository.removeListener();
    }

    private void loadLearningTitle(String sessionId, String titleId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningTitleEntity eLearningTitle = dataSnapshot.getValue(LearningTitleEntity.class);

                if (eLearningTitle != null) {
                    LearningTitleBO titleBO = mapper.map(eLearningTitle);
                    titleBO.setUuid(titleId);
                    learningTitleBO.setValue(titleBO);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    /**
     * generic loading of ALL the titles that the user created irregardless of the session
     *
     * @param userId
     */
    private void loadParticipantLearningTitles(String userId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningTitleBO> titles = new ArrayList<>();

                    for (DataSnapshot sessionTitles : dataSnapshot.getChildren()) {
                        if (sessionTitles.getValue() != null) {
                            for (DataSnapshot title : sessionTitles.getChildren()) {
                                LearningTitleEntity eTitle = title.getValue(LearningTitleEntity.class);
                                if (StringUtils.equals(eTitle.getCreatedBy(), userId)) {
                                    LearningTitleBO titleBO = mapper.map(eTitle);
                                    titleBO.setUuid(title.getKey());
                                    titles.add(titleBO);
                                }
                            }
                        }
                    }
                    learningTitleBOs.setValue(titles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void removeLearningTitle(String sessionId, String titleId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).child(titleId).removeValue();
    }
}
