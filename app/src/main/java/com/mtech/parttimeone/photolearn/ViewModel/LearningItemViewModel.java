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
import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningItemMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningItemRepository;

import org.apache.commons.lang3.mutable.Mutable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 29/3/2018.
 */

public class LearningItemViewModel extends ViewModel {
    private MutableLiveData<List<LearningItemBO>> learningItemBOs;
    private MutableLiveData<LearningItemBO> learningItemBO;

    /** This points to the collection of learning titles **/
    private LearningItemRepository learningItemRepository = new LearningItemRepository();

    private DatabaseReference mLearningItemRef;

    /**
     * This method loads just a single learning item
     * @param titleId
     * @return learningitemBO
     */
    public LiveData<LearningItemBO> getLearningTitle(String titleId) {
        if (learningItemBO == null) {
            learningItemBO = new MutableLiveData<LearningItemBO>();
            loadLearningItem(titleId);
        }
        return learningItemBO;
    }

    /**
     * This method loads just all learning items
     * @param titleId
     * @return list of learningitemBO
     */
    public LiveData<List<LearningItemBO>> getLearningTitles(String titleId) {
        loadAllLearningItems(titleId);

        return learningItemBOs;
    }

    @Override
    protected void onCleared() {
        learningItemRepository.removeListener();
    }

    private void loadLearningItem(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningItemEntity eLearningItem = dataSnapshot.getValue(LearningItemEntity.class);

                if (eLearningItem!= null) {
                    LearningItemMapper mapper = new LearningItemMapper();
                    learningItemBO.setValue(mapper.map(eLearningItem));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    /**
     * Creates a learning item
     * @param learningItemBO
     * @param titleId
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningItem(LearningItemBO learningItemBO, String titleId) throws Exception {
        //inserts learning_items
        setLearningItem(learningItemBO, titleId);
    }

    // This method is to delete a learning item
    public void deleteLearningItem(String titleId) {
        //deletes learning_items
        removeLearningItem(titleId);
    }

    // TODO
    // This is to update a learning item
    public LearningItemBO updateLearningItem(LearningItemBO learningItemBO, String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mLearningItemRef.child(titleId).setValue(learningItemBO);

        return learningItemBO;
    }

    //this loads all items based on titleId
    private void loadAllLearningItems(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningItemBO> listLearningItems = new ArrayList<>();
                    LearningItemMapper mapper = new LearningItemMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        LearningItemEntity entity = sessionSnapshot.getValue(LearningItemEntity.class);
                        LearningItemBO learningItemBO = mapper.map(entity);
                        listLearningItems.add(learningItemBO);
                    }
                    learningItemBOs.setValue(listLearningItems);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    //creates learning items filtered by titleId
    public boolean setLearningItem(LearningItemBO learningItemBO, String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).setValue(learningItemBO);

        return true;
    }

    public boolean removeLearningItem(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).removeValue();

        return true;
    }
}
