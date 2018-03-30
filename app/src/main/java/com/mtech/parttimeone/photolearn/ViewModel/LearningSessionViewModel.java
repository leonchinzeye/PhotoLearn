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
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningSessionMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserLearningSessionRepository;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionViewModel extends ViewModel {

    private static final String KEY_TRAINER = "trainers";
    private static final String KEY_PARTICIPANT = "participants";

    /** This points to the collection of learning sessions **/
    private LearningSessionRepository learningSessionRepository = new LearningSessionRepository();

    /* Global mapper */
    private LearningSessionMapper mapper = new LearningSessionMapper();

    /**
     * This points to the collection of user learning sessions
     **/
    private UserLearningSessionRepository userLearningSessionRepository = new UserLearningSessionRepository();

    private DatabaseReference mLearningSession;
    private DatabaseReference mUserLearningSession;

    private MutableLiveData<List<LearningSessionBO>> trainerSessions;
    private MutableLiveData<List<LearningSessionBO>> participantSessions;
    private MutableLiveData<LearningSessionBO> learningSessionBO;

    @Override
    protected void onCleared() {
        learningSessionRepository.removeListener();
        userLearningSessionRepository.removeListener();
    }

    /**
     * Gets the list of learning sessions
     * If user is trainer, gets the sessions he/she created
     * If user is participant, gets the sessions that he/she enrolled
     * @param userId
     * @param type
     * @return
     */
    public LiveData<List<LearningSessionBO>> getLearningSessions(String userId, UserType type) {
        switch (type) {
            case TRAINER:
                if (trainerSessions == null) {
                    trainerSessions = new MutableLiveData<List<LearningSessionBO>>();
                    loadTrainerSessions(userId);
                }
                return trainerSessions;
            case PARTICIPANT:
                if (participantSessions == null) {
                    participantSessions = new MutableLiveData<List<LearningSessionBO>>();
                    loadParticipantSessions(userId);
                }
                return participantSessions;
            default:
                return new MutableLiveData<>();
        }
    }

    public LiveData<LearningSessionBO> getLearningSession(String sessionId) {
        if (learningSessionBO == null) {
            learningSessionBO = new MutableLiveData<LearningSessionBO>();
            loadLearningSession(sessionId);
        }

        return learningSessionBO;
    }

    private void loadTrainerSessions(String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINER).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningSessionBO> listLearningSessions = new ArrayList<>();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningSessionEntity entity = sessionSnapshot.getValue(LearningSessionEntity.class);
                        LearningSessionBO learningSessionBO = mapper.map(entity);
                        listLearningSessions.add(learningSessionBO);
                    }

                    trainerSessions.setValue(listLearningSessions);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    private void loadParticipantSessions(String userId) {
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANT).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningSessionBO> listLearningSessions = new ArrayList<>();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningSessionEntity entity = sessionSnapshot.getValue(LearningSessionEntity.class);
                        LearningSessionBO learningSessionBO = mapper.map(entity);
                        listLearningSessions.add(learningSessionBO);
                    }

                    trainerSessions.setValue(listLearningSessions);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }
    /**
     * Creates a learning session as a trainer
     * @param learningSessionBO
     * @param userId
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningSession(LearningSessionBO learningSessionBO, String userId) throws Exception {
        //inserts learning_sessions
        setLearningSession(learningSessionBO);
        //inserts user_learning_sessions -> trainers
        setTrainerLearningSession(learningSessionBO, userId);
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
        LearningSessionEntity eSession = mapper.mapFrom(learningSessionBO);

        //update main session data
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).setValue(eSession);

        //update trainer session data
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINER).child(userId).child(sessionId).setValue(eSession);

       //update participant session data
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        if(sessionSnapshot.hasChild(sessionId)) {
                            sessionSnapshot.child(sessionId).getRef().setValue(eSession);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });

        return learningSessionBO;
    }

    private void loadLearningSession(String sessionId) {
        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningSessionEntity eLearningSession = dataSnapshot.getValue(LearningSessionEntity.class);

                if (eLearningSession != null) {
                    learningSessionBO.setValue(mapper.map(eLearningSession));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    //creates learning sessions filtered by session Id
    public boolean setLearningSession(LearningSessionBO learningSessionBO) {
        LearningSessionEntity eLearningSession;
        eLearningSession = mapper.mapFrom(learningSessionBO);

        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(learningSessionBO.getSessionId()).setValue(eLearningSession);

        return true;
    }

    //creates learning sessions filtered by trainer user Id and stores the session Id
    public boolean setTrainerLearningSession(LearningSessionBO learningSessionBO, String userId) {
        LearningSessionEntity eLearningSession;
        eLearningSession = mapper.mapFrom(learningSessionBO);

        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINER).child(userId).child(learningSessionBO.getSessionId()).setValue(eLearningSession);

        return true;
    }

    //creates learning sessions filtered by participant user Id and stores the session Id
    public boolean setParticipantLearningSession(LearningSessionBO learningSessionBO, String sessionId, String userId) {
        LearningSessionEntity eLearningSession = mapper.mapFrom(learningSessionBO);
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANT).child(userId).child(sessionId).setValue(eLearningSession);

        return true;
    }

    public boolean removeLearningSession(String sessionId, String userId) {
        LearningTitleViewModel learningTitleVM = new LearningTitleViewModel();
        learningTitleVM.deleteFromSession(sessionId);

        mLearningSession = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mLearningSession.child(sessionId).removeValue();

        //remove trainer link
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_TRAINER).child(userId).child(sessionId).removeValue();

        //remove enrolled participants
        mUserLearningSession = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mUserLearningSession.child(KEY_PARTICIPANT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        if(sessionSnapshot.hasChild(sessionId)) {
                            sessionSnapshot.child(sessionId).getRef().setValue(null);
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
