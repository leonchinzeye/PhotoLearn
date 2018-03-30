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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 29/3/2018.
 */

public class LearningItemViewModel extends ViewModel {

    /* for front end usage */
    private MutableLiveData<List<LearningItemBO>> learningItemBOs;
    private MutableLiveData<LearningItemBO> learningItemBO;

    /* global mapper */
    private LearningItemMapper mapper = new LearningItemMapper();

    /* This points to the collection of learning titles */
    private LearningItemRepository learningItemRepository = new LearningItemRepository();

    private DatabaseReference mLearningItemRef;

    /**
     *
     * @param titleId
     * @param itemId
     * @return
     */
    public LiveData<LearningItemBO> getLearningItem(String titleId, String itemId) {
        if (learningItemBO == null) {
            learningItemBO = new MutableLiveData<LearningItemBO>();
        }
        loadLearningItem(titleId, itemId);
        return learningItemBO;
    }

    /**
     * This method loads all the items belonging to a learning title
     * @param titleId
     * @return list of learningitemBO
     */
    public LiveData<List<LearningItemBO>> getLearningItems(String titleId) {
        if (learningItemBOs == null) {
            learningItemBOs = new MutableLiveData<>();
        }
        loadLearningItems(titleId, null);
        return learningItemBOs;
    }

    public LiveData<List<LearningItemBO>> getLearningItems(String titleId, String userId) {
        if (learningItemBOs == null) {
            learningItemBOs = new MutableLiveData<>();
        }
        loadLearningItems(titleId, userId);
        return learningItemBOs;
    }

    /**
     * Creates a learning item
     * @param learningItemBO
     * @throws Exception if the learning session is not a unique session ID
     */
    public void createLearningItem(LearningItemBO learningItemBO) throws Exception {
        addLearningItem(learningItemBO);
    }

    /**
     * Allows a participant to delete an item
     *
     * @param itemBO
     */
    public void deleteLearningItem(LearningItemBO itemBO) {
        removeLearningItem(itemBO.getTitleId(), itemBO.getUuid());
    }

    /**
     * For deletion of entire title
     *
     * @param titleId
     */
    public void deleteFromTitle(String titleId) {
        removeLearningItems(titleId);
    }

    /**
     * For updating of a item
     *
     * @param learningItemBO
     * @return
     */
    public LearningItemBO updateLearningItem(LearningItemBO learningItemBO) {
        LearningItemEntity eItem = mapper.mapFrom(learningItemBO);

        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(learningItemBO.getTitleId()).child(learningItemBO.getUuid()).setValue(eItem);

        return learningItemBO;
    }

    private void loadLearningItems(String titleId, String userId) {
        if (StringUtils.isEmpty(titleId) && StringUtils.isNotEmpty(userId)) {
            loadParticipantLearningItems(userId);
            return;
        }

        if (StringUtils.isNotEmpty(titleId) && StringUtils.isEmpty(userId)) {
            loadTitleLearningItems(titleId);
            return;
        }

        if (StringUtils.isNotEmpty(titleId) && StringUtils.isNotEmpty(userId)) {
            loadTitleUserLearningItems(titleId, userId);
            return;
        }

        if (StringUtils.isEmpty(titleId) && StringUtils.isEmpty(userId)) {
            learningItemBOs = new MutableLiveData<>();
            return;
        }
    }

    private void loadParticipantLearningItems(String userId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningItemBO> items = new ArrayList<>();

                    for (DataSnapshot sessionTitles : dataSnapshot.getChildren()) {
                        if (sessionTitles.getValue() != null) {
                            for (DataSnapshot title : sessionTitles.getChildren()) {
                                LearningItemEntity eTitle = title.getValue(LearningItemEntity.class);
                                if (StringUtils.equals(eTitle.getUserId(), userId)) {
                                    LearningItemBO itemBO = mapper.map(eTitle);
                                    itemBO.setUuid(title.getKey());
                                    items.add(itemBO);
                                }
                            }
                        }
                    }
                    learningItemBOs.setValue(items);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadTitleLearningItems(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningItemBO> listItems = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LearningItemEntity eItem = snapshot.getValue(LearningItemEntity.class);
                        LearningItemBO titleBO = mapper.map(eItem);
                        titleBO.setUuid(snapshot.getKey());
                        listItems.add(titleBO);
                    }
                    learningItemBOs.setValue(listItems);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadTitleUserLearningItems(String titleId, String userId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<LearningItemBO> listItems = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LearningItemEntity eTitle = snapshot.getValue(LearningItemEntity.class);
                        if (StringUtils.equals(userId, eTitle.getUserId())) {
                            LearningItemBO itemBO = mapper.map(eTitle);
                            itemBO.setUuid(snapshot.getKey());
                            listItems.add(itemBO);
                        }
                    }
                    learningItemBOs.setValue(listItems);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", databaseError.getMessage());
            }
        });
    }

    private void loadLearningItem(String titleId, String itemId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LearningItemEntity eLearningItem = dataSnapshot.getValue(LearningItemEntity.class);

                if (eLearningItem!= null) {
                    LearningItemBO itemBO = mapper.map(eLearningItem);
                    learningItemBO.setValue(mapper.map(eLearningItem));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ",databaseError.getMessage());
            }
        });
    }

    private void addLearningItem(LearningItemBO learningItemBO) {
        LearningItemEntity eItem = mapper.mapFrom(learningItemBO);
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());

        String uuid = mLearningItemRef.child(learningItemBO.getTitleId()).push().getKey();
        mLearningItemRef.child(learningItemBO.getTitleId()).child(uuid).setValue(eItem);
        learningItemBO.setUuid(uuid);
    }

    private void removeLearningItem(String titleId, String itemId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).child(itemId).removeValue();
    }

    private void removeLearningItems(String titleId) {
        mLearningItemRef = FirebaseDatabase.getInstance().getReference(learningItemRepository.getRootNode());
        mLearningItemRef.child(titleId).removeValue();
    }

    @Override
    protected void onCleared() {
        learningItemRepository.removeListener();
    }
}
