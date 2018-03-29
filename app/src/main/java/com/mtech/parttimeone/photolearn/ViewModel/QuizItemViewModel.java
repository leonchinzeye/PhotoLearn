package com.mtech.parttimeone.photolearn.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.data.entity.QuizItemEntity;
import com.mtech.parttimeone.photolearn.data.mapper.QuizItemMapper;
import com.mtech.parttimeone.photolearn.data.repository.QuizItemRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karen on 29/3/2018.
 */

public class QuizItemViewModel extends ViewModel {
    private MutableLiveData<List<QuizItemBO>> quizItemBOs;
    private List<QuizItemBO> quizItems = new ArrayList<>();
    private QuizItemBO quizItem;
    private boolean hasLoadedQuizItems = false;
    private boolean hasLoadedQuizItem = false;

    /** This points to the collection of quiz titles **/
    private QuizItemRepository quizItemRepository = new QuizItemRepository();

    private DatabaseReference mQuizItemRef;

    private void getQuizTitle(String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                QuizItemEntity eQuizItem = dataSnapshot.getValue(QuizItemEntity.class);

                if (eQuizItem!= null) {
                    QuizItemMapper mapper = new QuizItemMapper();
                    quizItem = mapper.map(eQuizItem);
                    hasLoadedQuizItem = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCleared() {
        quizItemRepository.removeListener();
    }

    /**
     * This method loads just all quiz items
     * @param titleId
     * @return list of quizitemBO
     */
    public List<QuizItemBO> loadQuizItems(String titleId) {
        loadAllQuizItems(titleId);

        if (hasLoadedQuizItems) {
            hasLoadedQuizItems = false;
            return this.quizItems;
        }
        else {
            return null;
        }
    }

    /**
     * This method loads just a single quiz item
     * @param titleId
     * @return quizitemBO
     */
    public QuizItemBO loadQuizItem(String titleId) {
        getQuizTitle(titleId);

        if (hasLoadedQuizItem) {
            hasLoadedQuizItem = false;
            return this.quizItem;
        } else {
            return null;
        }
    }

    /**
     * Creates a quiz item
     * @param quizItemBO
     * @param titleId
     * @throws Exception if the quiz session is not a unique session ID
     */
    public void createQuizItem(QuizItemBO quizItemBO, String titleId) throws Exception {
        //inserts quiz_items
        setQuizItem(quizItemBO, titleId);
    }

    // This method is for trainers to delete a quiz title
    public void deleteQuizItem(String titleId) {
        //deletes quiz_items
        removeQuizItem(titleId);
    }

    // TODO
    // This is to update a quiz item
    public QuizItemBO updateQuizItem(QuizItemBO quizItemBO, String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        //since the actual changes are not specified so update the entire node
        mQuizItemRef.child(titleId).setValue(quizItemBO);

        return quizItemBO;
    }

    //this loads all items based on titleId
    private void loadAllQuizItems(String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    List<QuizItemBO> listQuizItems = new ArrayList<>();
                    QuizItemMapper mapper = new QuizItemMapper();

                    for (DataSnapshot sessionSnapshot : dataSnapshot.getChildren()) {
                        QuizItemEntity entity = sessionSnapshot.getValue(QuizItemEntity.class);
                        QuizItemBO quizItemBO = mapper.map(entity);
                        listQuizItems.add(quizItemBO);
                    }

                    quizItemBOs.setValue(listQuizItems);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //creates quiz items filtered by title Id
    public boolean setQuizItem(QuizItemBO quizItemBO, String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).setValue(quizItemBO);

        return true;
    }

    public boolean HasLoadedQuizItems() {
        return hasLoadedQuizItems;
    }

    public void setHasLoadedQuizItems(boolean hasLoadedQuizItems) {
        this.hasLoadedQuizItem = hasLoadedQuizItem;
    }

    public boolean HasLoadedQuizItem() {
        return hasLoadedQuizItem;
    }

    public void setHasLoadedQuizItem(boolean hasLoadedQuizItem) {
        this.hasLoadedQuizItem = hasLoadedQuizItem;
    }

    public boolean removeQuizItem(String titleId) {
        mQuizItemRef = FirebaseDatabase.getInstance().getReference(quizItemRepository.getRootNode());
        mQuizItemRef.child(titleId).removeValue();

        return true;
    }
}