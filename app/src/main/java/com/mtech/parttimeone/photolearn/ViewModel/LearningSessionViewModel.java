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
import com.mtech.parttimeone.photolearn.data.mapper.UserLearningSessionMapper;
import com.mtech.parttimeone.photolearn.data.repository.FirebaseDatabaseRepository;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserLearningSessionRepository;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionViewModel extends ViewModel {

    /** This points to the collection of learning sessions **/
    private LearningSessionRepository learningSessionRepository = new LearningSessionRepository();

    /**
     * This points to the collection of user learning sessions
     **/
    private UserLearningSessionRepository userLearningSessionRepository = new UserLearningSessionRepository();

    private UserLearningSessionEntity userLearningSessionEntity;

    private DatabaseReference mLearningSession;
    private DatabaseReference mUserLearningSession;

    private MutableLiveData<List<LearningSessionBO>> learningSessionBOs;
    private List<LearningSessionBO> learningSessions;
    private LearningSessionBO learningSession;
    private boolean hasLoadedLearningSessions = false;
    private boolean hasLoadedLearningSession = false;

    @Override
    protected void onCleared() {
        learningSessionRepository.removeListener();
        userLearningSessionRepository.removeListener();
    }


    public LiveData<List<LearningSessionBO>> getLearningSessions(String userId, UserType type) {
        if (learningSessionBOs == null) {
            learningSessionBOs = new MutableLiveData<List<LearningSessionBO>>();
            loadLearningSessionsTemp(userId, type);
        }

        return learningSessionBOs;
    }

    private void loadLearningSessionsTemp(String userId, UserType type) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child("trainers").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
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

                    learningSessionBOs.setValue(listLearningSessions);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * Loads a list of learning sessions
     * If the user is a trainer, loads the learning sessions that he has created
     * If the user is a participant, loads the learning sessions that he has enrolled in
     * @param userId
     * @param type
     * @return
     */
    public List<LearningSessionBO> loadLearningSessions(String userId, UserType type) {

        switch (type) {
            case TRAINER:
                loadTrainerLearningSessions(userId);
                break;
            case PARTICIPANT:
                loadParticipantLearningSessions(userId);
                break;
            default:
                break;
        }

        if (hasLoadedLearningSessions) {
            hasLoadedLearningSessions = false;
            return this.learningSessions;
        } else {
            return null;
        }
    }

    /**
     * This method loads just a single learning session
     * @param userId
     * @param sessionId
     * @return learningSessionBO
     */
    public LearningSessionBO loadLearningSession(String userId, String sessionId) {
        getLearningSession(sessionId);

        if (hasLoadedLearningSession) {
            hasLoadedLearningSession = false;
            return this.learningSession;
        } else {
            return null;
        }
    }


    /**
     * Creates a learning session as a trainer
     * @param learningSessionBO
     * @param userId
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningSession(LearningSessionBO learningSessionBO, String sessionId, String userId) throws Exception {
        //inserts learning_sessions
        setLearningSession(learningSessionBO, sessionId);
        //inserts user_learning_sessions -> trainers
        setTrainerLearningSession(learningSessionBO, sessionId, userId);
    }

    /**
     * Enrolls a participant into a learning session
     * @param sessionId
     * @param userId
     */
    public void enrollLearningSession(LearningSessionBO learningSessionBO, String sessionId, String userId) {
        //writes user_learning_sessions -> participants
        setParticipantLearningSession(learningSessionBO, sessionId, userId);
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

    private void getLearningSession(String sessionId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningSessionEntity eLearningSession = dataSnapshot.getValue(LearningSessionEntity.class);

                if (eLearningSession != null) {
                    LearningSessionMapper mapper = new LearningSessionMapper();
                    learningSession = mapper.map(eLearningSession);
                    hasLoadedLearningSession = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadParticipantLearningSessions(String userId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child("participants").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLearningSessionEntity = dataSnapshot.getValue(UserLearningSessionEntity.class);

                // TODO
                // determine return type and fix this
                //learningSessions = (List) object;
                setHasLoadedLearningSessions(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //learningSessions = DummyUtils.populateDummyLearningBOs();
        //setHasLoadedLearningSessions(true);
    }

    private void loadTrainerLearningSessions(String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mUserLearningSession.child("trainers").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserLearningSessionEntity eLearningSession = dataSnapshot.getValue(UserLearningSessionEntity.class);

                if (eLearningSession != null) {
                    List<LearningSessionBO> learningSessionBOs = new ArrayList<>();
                    LearningSessionMapper mapper = new LearningSessionMapper();
                    for (LearningSessionEntity entity : eLearningSession.getSessionList()) {
                        LearningSessionBO learningSessionBO = mapper.map(entity);
                        learningSessionBOs.add(learningSessionBO);
                    }

                    learningSessions = learningSessionBOs;
                    setHasLoadedLearningSessions(true);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //learningSessions = DummyUtils.populateDummyLearningBOs();
        //setHasLoadedLearningSessions(true);
    }

    //creates learning sessions filtered by session Id
    public boolean setLearningSession(LearningSessionBO learningSessionBO, String sessionId) {
        LearningSessionEntity eLearningSession;
        LearningSessionMapper learningSessionMapper = new LearningSessionMapper();
        eLearningSession = learningSessionMapper.mapFrom(learningSessionBO);

        this.mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        this.mLearningSession.child(sessionId).setValue(eLearningSession);

        return true;
    }

    //creates learning sessions filtered by trainer user Id and stores the session Id
    public boolean setTrainerLearningSession(LearningSessionBO learningSessionBO, String sessionId, String userId) {
        LearningSessionEntity eLearningSession;
        LearningSessionMapper learningSessionMapper = new LearningSessionMapper();
        eLearningSession = learningSessionMapper.mapFrom(learningSessionBO);

        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child("trainers").child(userId).child(sessionId).setValue(eLearningSession);

        return true;
    }

    //creates learning sessions filtered by participant user Id and stores the session Id
    public boolean setParticipantLearningSession(LearningSessionBO mLearningSession, String sessionId, String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child("participants").child(userId).child(sessionId).setValue(mLearningSession);

        return true;
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
