package com.mtech.parttimeone.photolearn.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.Adapter.QuizItemCreationAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.ViewModel.QuizItemViewModel;
import com.mtech.parttimeone.photolearn.asyncTask.UploadAsyncTask;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.handler.LifeCycleHandler;

public class QuizItemCreationActivity extends ItemCreationActivity {

    private static final String QUIZ_TYPE = "QUIZ";

    private String titleId;
    private ListView listView;
    QuizItemCreationAdapter adapter;
    private boolean isCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_creation);

        Intent intent = getIntent();
        titleId = intent.getStringExtra("TitleID");

        initView();
    }


    private void initData() {
        QuizItemBO obj_0 = new QuizItemBO();
        obj_0.setItemtitle("How to resolve this issue");
        obj_0.setItemDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_0.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");

        obj_0.addOption("ques0_This is option 1");
        obj_0.addOption("ques0_This is option 2");
        obj_0.addOption("ques0_This is option 3");
        obj_0.addOption("ques0_This is option 4");
        obj_0.addOption("ques0_This is option 5");

        adapter.quizItemObj = obj_0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // INIT the UI
    private void initView() {

        super.setPageTitle("Create Quiz Item");
        listView = findViewById(R.id.quiz_creation_list);
        adapter = new QuizItemCreationAdapter(this);

       // initData();
//        if (adapter.quizItemObj.getOptions().isEmpty()){
//            // add new item
//            adapter.quizItemObj.addOption("");
//        }else{
//            // update quiz item.
//            initData();
//        }
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done) {
            submitQuizItem(getFilePath());
            return true;
        } else {
            return true;
        }
    }

    public void submitQuizItem(Uri file) {
        setItemType(QUIZ_TYPE);
        new UploadAsyncTask(QuizItemCreationActivity.this).execute(file);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.add(0,1,Menu.NONE,"Photo");
//        menu.add(0,2,Menu.NONE,"Library");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photoselection, menu);
    }

    // selecttion item.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.take_photo: {
                takePhoto();
            }
            break;
            case R.id.library: {
                selectPhotofromLibrary();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveItemImagePath(Uri downloadUrl) {
        //Call View Model
        Log.d(TAG, "saveItem for Quiz:Call ViewModel to save Item!" + downloadUrl);
        adapter.quizItemObj.setPhotoURL(downloadUrl.toString());
        adapter.quizItemObj.setTitleId(titleId);
       // adapter.quizItemObj.setUserId(LifeCycleHandler.getInstance().getAccountBO().getUid());
        QuizItemViewModel vmquizItemViewModel = ViewModelProviders.of(this).get(QuizItemViewModel.class);
        try {
            vmquizItemViewModel.createQuizItem(adapter.quizItemObj);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            Toast.makeText(this,"Add Quiz Item Successfully!",Toast.LENGTH_SHORT).show();
            this.onBackPressed();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Error adding Quiz Item!",Toast.LENGTH_SHORT).show();
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        }



    }


}
