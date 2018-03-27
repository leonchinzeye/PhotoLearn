package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.data.entity.UserLearningSessionEntity;
import com.mtech.parttimeone.photolearn.data.repository.LearningSessionRepository;
import com.mtech.parttimeone.photolearn.data.repository.UserLearningSessionRepository;
import com.mtech.parttimeone.photolearn.dummyModel.utils.DummyUtils;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

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

    private DatabaseReference mDatabaseReference;
    private DatabaseReference mDatabaseReference1;

    private List<LearningSessionBO> learningSessions;
    private LearningSessionBO learningSession;
    private boolean hasLoadedLearningSessions = false;
    private boolean hasLoadedLearningSession = false;

    @Override
    protected void onCleared() {
        learningSessionRepository.removeListener();
        userLearningSessionRepository.removeListener();
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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());

        //since the actual changes are not specified so update entire node
        mDatabaseReference.child(sessionId).setValue(learningSessionBO);

        return learningSessionBO;
    }

    private void getLearningSession(String courseCode, String userType, String userId) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mDatabaseReference.child(userType).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object listOfLearningSessions = dataSnapshot.getValue(List.class);

                if (listOfLearningSessions != null) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadParticipantLearningSessions(String userId) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mDatabaseReference.child("participants").child(userId).addValueEventListener(new ValueEventListener() {
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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mDatabaseReference.child("trainers").child(userId).addValueEventListener(new ValueEventListener() {
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

    //creates learning sessions filtered by session Id
    public boolean setLearningSession(LearningSessionBO mLearningSession, String sessionId) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mDatabaseReference.child(sessionId).setValue(mLearningSession);

        return true;
    }

    //creates learning sessions filtered by trainer user Id and stores the session Id
    public boolean setTrainerLearningSession(LearningSessionBO mLearningSession, String sessionId, String userId) {
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mDatabaseReference1.child("trainers").child(userId).child(sessionId).setValue(mLearningSession);

        return true;
    }

    //creates learning sessions filtered by participant user Id and stores the session Id
    public boolean setParticipantLearningSession(LearningSessionBO mLearningSession, String sessionId, String userId) {
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mDatabaseReference1.child("participants").child(userId).child(sessionId).setValue(mLearningSession);

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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(learningSessionRepository.getRootNode());
        mDatabaseReference.child(sessionId).removeValue();

        //remove trainer link
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mDatabaseReference1.child("trainers").child(userId).child(sessionId).removeValue();

        //remove enrolled participants
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference(userLearningSessionRepository.getRootNode());
        mDatabaseReference1.child("participants").child(userId).child(sessionId).removeValue();

        return true;
    }
}
