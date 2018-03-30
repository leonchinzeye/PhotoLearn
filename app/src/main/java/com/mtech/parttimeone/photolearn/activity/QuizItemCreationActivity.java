package com.mtech.parttimeone.photolearn.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.Adapter.QuizItemCreationAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;

import java.util.ArrayList;
import java.util.List;
import com.mtech.parttimeone.photolearn.asyncTask.UploadAsyncTask;

import org.apache.commons.lang3.StringUtils;

public class QuizItemCreationActivity extends ItemCreationActivity  {

    private ListView listView;
    QuizItemCreationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_creation);
        initView();
    }


    private void initData(){
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
        getMenuInflater().inflate(R.menu.menu_done,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // INIT the UI
    private void initView(){

        super.setPageTitle("Create Quiz Item");
        listView = findViewById(R.id.quiz_creation_list);
        adapter = new  QuizItemCreationAdapter(this);
        initData();
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

    public void submitQuizItem(Uri file){
        //TO DO - need to put logic to derive Item id
        setItemId("quiz_1");
        new UploadAsyncTask(this).execute(file);
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
    public boolean onContextItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.add_photo_button:{
                takePhoto();
            }
            break;
            case R.id.library:{
                selectPhotofromLibrary();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }





}
