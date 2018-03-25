package com.mtech.parttimeone.photolearn.activity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.Adapter.LearningItemCreationAdapter;
import com.mtech.parttimeone.photolearn.R;

public class LearnItemCreationActivity extends BaseActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_item_creation);

        intheView();
    }

    //call back function for "Done" button on right top.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done) {
            createtheLearningItem();
            return true;
        } else {
            Toast.makeText(LearnItemCreationActivity.this, R.string.error_gernal, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_done,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // INIT the UI
    private void intheView(){
        super.setPageTitle("Create Learning Item");
        listView = (ListView) findViewById(R.id.learnning_creation_list);
        LearningItemCreationAdapter adapter = new  LearningItemCreationAdapter(this);
        listView.setAdapter(adapter);
    }

    //function for click the done button.
    public void createtheLearningItem(){


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

