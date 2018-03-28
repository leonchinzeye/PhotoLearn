package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningTitleViewModel extends ViewModel {
    private MutableLiveData<List<LearningTitleBO>> learningTitleBOs;
    private List<LearningTitleBO> learningTitles;
    private LearningTitleBO learningTitle;
    private boolean hasLoadedLearningTitles = false;
    private boolean hasLoadedLearningTitle = false;

    /** This points to the collection of learning titles **/
    private LearningTitleRepository learningTitleRepository = new LearningTitleRepository();

    /** This points to the collection of user titles **/
    private UserTitleRepository userTitleRepository = new UserTitleRepository();

    private DatabaseReference mLearningTitleRef;
    private DatabaseReference mUserTitleRef;

    private void getLearningTitle(String sessionId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningTitleEntity eLearningTitle = dataSnapshot.getValue(LearningTitleEntity.class);

                if (eLearningTitle!= null) {
                    LearningTitleMapper mapper = new LearningTitleMapper();
                    learningTitle = mapper.map(eLearningTitle);
                    hasLoadedLearningTitle = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCleared() {
        learningTitleRepository.removeListener();
        userTitleRepository.removeListener();
    }

    /**
     * This method loads just all learning titles
     * @param userId
     * @param sessionId
     * @return list of learningtitleBO
     */
    public List<LearningTitleBO> loadLearningTitles(String sessionId, String userId) {
        if (sessionId!=""){
            loadAllLearningTitles(sessionId);
        } else {
            loadParticipantLearningTitles(userId);
        }

        if (hasLoadedLearningTitles) {
            hasLoadedLearningTitles = false;
            return this.learningTitles;
      }
      else {
          return null;
      }
    }

    /**
     * This method loads just a single learning title
     * @param userId
     * @param sessionId
     * @return learningtitleBO
     */
    public LearningTitleBO loadLearningTitle(String userId, String sessionId) {
        getLearningTitle(sessionId);

        if (hasLoadedLearningTitle) {
            hasLoadedLearningTitle = false;
            return this.learningTitle;
        } else {
            return null;
        }
    }

    /**
     * Creates a learning title as a participant
     * @param learningTitleBO
     * @param userId
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningTitle(LearningTitleBO learningTitleBO, String sessionId, String userId) throws Exception {
        //inserts learning_titles
        setLearningTitle(learningTitleBO, sessionId);
        //inserts user_titles -> participants
        setParticipantLearningTitle(learningTitleBO, sessionId, userId);
    }

    // This method is for trainers to delete a learning title
    public void deleteLearningTitle(String sessionId, String userId) {
        //deletes learning_titles
        //deletes user_titles -> participants
        removeLearningTitle(sessionId, userId);
    }

    // TODO
    // This is for trainers to update a learning session
    public LearningTitleBO updateLearningTitle(LearningTitleBO learningTitleBO, String sessionId , String userId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mLearningTitleRef.child(sessionId).setValue(learningTitleBO);

        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mUserTitleRef.child("participants").child(userId).child(sessionId).setValue(learningTitleBO);

        return learningTitleBO;
    }

    //this loads all titles based on sessionId
    private void loadAllLearningTitles(String sessionId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningTitleBO> listLearningTitles = new ArrayList<>();
                    LearningTitleMapper mapper = new LearningTitleMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningTitleEntity entity = sessionSnapshot.getValue(LearningTitleEntity.class);
                        LearningTitleBO learningTitleBO = mapper.map(entity);
                        listLearningTitles.add(learningTitleBO);
                    }

                    learningTitleBOs.setValue(listLearningTitles);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //this loads titles based on userId - required in participant edit mode
    private void loadParticipantLearningTitles(String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningTitleBO> listLearningTitles = new ArrayList<>();
                    LearningTitleMapper mapper = new LearningTitleMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningTitleEntity entity = sessionSnapshot.getValue(LearningTitleEntity.class);
                        LearningTitleBO learningTitleBO = mapper.map(entity);
                        listLearningTitles.add(learningTitleBO);
                    }

                    learningTitleBOs.setValue(listLearningTitles);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //creates learning titles filtered by session Id
    public boolean setLearningTitle(LearningTitleBO learningTitleBO, String sessionId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).setValue(learningTitleBO);

        return true;
    }

    //creates learning titles filtered by participant user Id and stores the session Id
    public boolean setParticipantLearningTitle(LearningTitleBO learningTitleBO, String sessionId, String userId) {
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).child(sessionId).setValue(learningTitleBO);

        return true;
    }

    public boolean isHasLeadedLearningTitles() {
        return hasLoadedLearningTitles;
    }

    public void setHasLoadedLearningTitles(boolean hasLoadedLearningTitles) {
        this.hasLoadedLearningTitle = hasLoadedLearningTitle;
    }

    public boolean isHasLoadedLearningTitle() {
        return hasLoadedLearningTitle;
    }

    public void setHasLoadedLearningTitle(boolean hasLoadedLearningTitle) {
        this.hasLoadedLearningTitle = hasLoadedLearningTitle;
    }

    public boolean removeLearningTitle(String sessionId, String userId) {
        mLearningTitleRef = FirebaseDatabase.getInstance().getReference(learningTitleRepository.getRootNode());
        mLearningTitleRef.child(sessionId).removeValue();

        //remove participant link
        mUserTitleRef = FirebaseDatabase.getInstance().getReference(userTitleRepository.getRootNode());
        mUserTitleRef.child("participants").child(userId).child(sessionId).removeValue();

        return true;
    }
}
