package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.data.entity.LearningItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.LearningItemMapper;
import com.mtech.parttimeone.photolearn.data.repository.LearningItemRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 29/3/2018.
 */

public class LearningItemViewModel extends ViewModel {
    private MutableLiveData<List<LearningItemBO>> learningItemBOs;
    private List<LearningItemBO> learningItems = new ArrayList<>();
    private LearningItemBO learningItem;
    private boolean hasLoadedLearningItems = false;
    private boolean hasLoadedLearningItem = false;

    /** This points to the collection of learning titles **/
    private LearningItemRepository learningItemRepository = new LearningItemRepository();

    private DatabaseReference mLearningItemRef;

    private void getLearningTitle(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningItemEntity eLearningItem = dataSnapshot.getValue(LearningItemEntity.class);

                if (eLearningItem!= null) {
                    LearningItemMapper mapper = new LearningItemMapper();
                    learningItem = mapper.map(eLearningItem);
                    hasLoadedLearningItem = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCleared() {
        learningItemRepository.removeListener();
    }

    /**
     * This method loads just all learning items
     * @param userId
     * @param titleId
     * @return list of learningitemBO
     */
    public List<LearningItemBO> loadLearningItems(String titleId, String userId) {
        loadAllLearningItems(titleId);

        if (hasLoadedLearningItems) {
            hasLoadedLearningItems = false;
            return this.learningItems;
        }
        else {
            return null;
        }
    }

    /**
     * This method loads just a single learning item
     * @param userId
     * @param itemId
     * @return learningitemBO
     */
    public LearningItemBO loadLearningItem(String itemId, String userId) {
        getLearningTitle(itemId);

        if (hasLoadedLearningItem) {
            hasLoadedLearningItem = false;
            return this.learningItem;
        } else {
            return null;
        }
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
        mLearningItemRef.child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
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

            }
        });
    }

    //creates learning items filtered by titleId
    public boolean setLearningItem(LearningItemBO learningItemBO, String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).setValue(learningItemBO);

        return true;
    }

    public boolean HasLoadedLearningItems() {
        return hasLoadedLearningItems;
    }

    public void setHasLoadedLearningItems(boolean hasLoadedLearningItems) {
        this.hasLoadedLearningItem = hasLoadedLearningItem;
    }

    public boolean HasLoadedLearningItem() {
        return hasLoadedLearningItem;
    }

    public void setHasLoadedLearningItem(boolean hasLoadedLearningItem) {
        this.hasLoadedLearningItem = hasLoadedLearningItem;
    }

    public boolean removeLearningItem(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).removeValue();

        return true;
    }
}
