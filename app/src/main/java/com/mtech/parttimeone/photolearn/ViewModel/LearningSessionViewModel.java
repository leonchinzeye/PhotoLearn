package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningSessionMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserLearningSessionRepository;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionViewModel extends ViewModel {

    private static final String KEY_PARTICIPANTS = "participants";
    private static final String KEY_TRAINERS = "trainers";

    /** This points to the collection of learning sessions **/
    private LearningSessionRepository learningSessionRepository = new LearningSessionRepository();

    /**
     * This points to the collection of user learning sessions
     **/
    private UserLearningSessionRepository userLearningSessionRepository = new UserLearningSessionRepository();



    private DatabaseReference mLearningSession;
    private DatabaseReference mUserLearningSession;

    private MutableLiveData<List<LearningSessionBO>> trainerLearningSessionBOs;
    private MutableLiveData<List<LearningSessionBO>> participantLearningSessionBOs;
    private MutableLiveData<LearningSessionBO> learningSessionBO;

    private LearningSessionBO learningSession;
    private boolean hasLoadedLearningSessions = false;
    private boolean hasLoadedLearningSession = false;

    @Override
    protected void onCleared() {
        learningSessionRepository.removeListener();
        userLearningSessionRepository.removeListener();
    }

    public LiveData<LearningSessionBO> getLearningSession(String sessionId) {
        if (learningSessionBO == null) {
            learningSessionBO = new MutableLiveData<LearningSessionBO>();
            loadLearningSession(sessionId);
        }
        return learningSessionBO;
    }

    private void loadLearningSession(String sessionId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    LearningSessionMapper mapper = new LearningSessionMapper();
                    LearningSessionEntity entity = dataSnapshot.getValue(LearningSessionEntity.class);
                    LearningSessionBO learningSession = mapper.map(entity);
                    learningSessionBO.setValue(learningSession);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public LiveData<List<LearningSessionBO>> getLearningSessions(String userId, UserType type) {
        switch (type) {
            case TRAINER:
                if (trainerLearningSessionBOs == null) {
                    trainerLearningSessionBOs = new MutableLiveData<List<LearningSessionBO>>();
                    loadTrainerLearningSessions(userId);
                }
                return trainerLearningSessionBOs;
            case PARTICIPANT:
                if (participantLearningSessionBOs == null) {
                    participantLearningSessionBOs = new MutableLiveData<List<LearningSessionBO>>();
                    loadParticipantLearningSessions(userId);
                }
                return participantLearningSessionBOs;
            default:
                return null;
        }
    }

    private void loadTrainerLearningSessions(String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINERS).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningSessionBO> listLearningSessions = new ArrayList<>();
                    LearningSessionMapper mapper = new LearningSessionMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningSessionEntity entity = sessionSnapshot.getValue(LearningSessionEntity.class);
                        LearningSessionBO learningSessionBO = mapper.map(entity);
                        listLearningSessions.add(learningSessionBO);
                    }

                    trainerLearningSessionBOs.setValue(listLearningSessions);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadParticipantLearningSessions(String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANTS).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningSessionBO> listLearningSessions = new ArrayList<>();
                    LearningSessionMapper mapper = new LearningSessionMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningSessionEntity entity = sessionSnapshot.getValue(LearningSessionEntity.class);
                        LearningSessionBO learningSessionBO = mapper.map(entity);
                        listLearningSessions.add(learningSessionBO);
                    }

                    participantLearningSessionBOs.setValue(listLearningSessions);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * Creates a learning session as a trainer
     * @param learningSessionBO
     * @param userId
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningSession(LearningSessionBO learningSessionBO, String userId) {
        setLearningSession(learningSessionBO);
        setTrainerLearningSession(learningSessionBO, userId);
    }

    /**
     * Enrolls a participant into a learning session
     * @param sessionId
     * @param userId
     */
    public void enrollLearningSession(LearningSessionBO learningSessionBO, String userId) {
        setParticipantLearningSession(learningSessionBO, userId);
    }


    // This method is for trainers to delete a learning session
    public void deleteLearningSession(String sessionId, String userId) {
        //deletes learning_sessions, user_learning_sessions -> trainers
        //deletes user_learning_sessions -> participants
        removeLearningSession(sessionId, userId);
    }

    // TODO
    // This is for trainers to update a learning session
    public LearningSessionBO updateLearningSession(LearningSessionBO learningSessionBO, String sessionId , String userId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());

        //since the actual changes are not specified so update entire node
        mLearningSession.child(sessionId).setValue(learningSessionBO);

        return learningSessionBO;
    }

    public void setLearningSession(LearningSessionBO learningSessionBO) {
        LearningSessionEntity eLearningSession;
        LearningSessionMapper learningSessionMapper = new LearningSessionMapper();
        eLearningSession = learningSessionMapper.mapFrom(learningSessionBO);

        this.mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        this.mLearningSession.child(eLearningSession.getSessionId()).setValue(eLearningSession);
    }

    public boolean setTrainerLearningSession(LearningSessionBO learningSessionBO, String userId) {
        LearningSessionEntity eLearningSession;
        LearningSessionMapper learningSessionMapper = new LearningSessionMapper();
        eLearningSession = learningSessionMapper.mapFrom(learningSessionBO);

        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINERS).child(userId).child(eLearningSession.getSessionId()).setValue(eLearningSession);

        return true;
    }

    public void setParticipantLearningSession(LearningSessionBO mLearningSession, String userId) {
        LearningSessionEntity eLearningSession;
        LearningSessionMapper learningSessionMapper = new LearningSessionMapper();
        eLearningSession = learningSessionMapper.mapFrom(mLearningSession);

        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANTS).child(userId).child(eLearningSession.getSessionId()).setValue(mLearningSession);
    }

    public boolean isHasLoadedLearningSessions() {
        return hasLoadedLearningSessions;
    }

    public void setHasLoadedLearningSessions(boolean hasLoadedLearningSessions) {
        this.hasLoadedLearningSessions = hasLoadedLearningSessions;
    }

    public boolean isHasLoadedLearningSession() {
        return hasLoadedLearningSession;
    }

    public void setHasLoadedLearningSession(boolean hasLoadedLearningSession) {
        this.hasLoadedLearningSession = hasLoadedLearningSession;
    }

    public boolean removeLearningSession(String sessionId, String userId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).removeValue();

        //remove trainer link
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child("trainers").child(userId).child(sessionId).removeValue();

        //remove enrolled participants
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child("participants").child(userId).child(sessionId).removeValue();

        return true;
    }
}
