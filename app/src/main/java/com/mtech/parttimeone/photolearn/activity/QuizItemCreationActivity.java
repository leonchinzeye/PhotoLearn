package com.mtech.parttimeone.photolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mtech.parttimeone.photolearn.Adapter.QuizItemCreationAdapter;
import com.mtech.parttimeone.photolearn.R;

public class QuizItemCreationActivity extends BaseActivity  {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_creation);
        initView();

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
        QuizItemCreationAdapter adapter = new  QuizItemCreationAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done) {
            submitQuizItem();
            return true;
        } else {
            return true;
        }
    }

    public void submitQuizItem(){

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

    public void takePhoto(){

    }

    public void selectPhotofromLibrary(){

    }

    // add button call back function - v = addBtn.
    public void addPhoto(View v){
        registerForContextMenu(v);
        openContextMenu(v);
    }

}
