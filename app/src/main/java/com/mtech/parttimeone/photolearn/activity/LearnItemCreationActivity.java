package com.mtech.parttimeone.photolearn.activity;

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

import com.mtech.parttimeone.photolearn.Adapter.LearningItemCreationAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.asyncTask.UploadAsyncTask;

import org.apache.commons.lang3.StringUtils;

public class LearnItemCreationActivity extends ItemCreationActivity {

    private ListView listView;

    private static final String TAG = "PhotoLearn";
    private String titleId;
    private LearningItemCreationAdapter adapter;

    private static final String LEARNING_TYPE = "LEARNING";

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_item_creation);

        intheView();
    }

    //call back function for "Done" button on right top.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected:" + item.getItemId());
        Log.d(TAG, "onOptionsItemSelected 2:" + R.id.action_done);
        if (item.getItemId() == R.id.action_done) {
            createtheLearningItem(getFilePath());
            return true;
        } else {
            Toast.makeText(LearnItemCreationActivity.this, R.string.error_gernal, Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // INIT the UI
    private void intheView() {
        super.setPageTitle("Create Learning Item");
        listView = (ListView) findViewById(R.id.learnning_creation_list);
        adapter = new LearningItemCreationAdapter(this);
        listView.setAdapter(adapter);
    }

    //function for click the done button.
    public void createtheLearningItem(Uri file){
        String title = adapter.itemBO.getItemtitle();
        setItemType(LEARNING_TYPE);
        if (StringUtils.isBlank(title)) {
            Toast toast = Toast.makeText(LearnItemCreationActivity.this, "Title should not be blank.", Toast.LENGTH_LONG);
        } else {
            new UploadAsyncTask((LearnItemCreationActivity) this).execute(file);
        }


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
        Log.d(TAG, "onContextItemSelected:" + item.getItemId());
        Log.d(TAG, "onContextItemSelected:" + R.id.take_photo);
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
        Log.d(TAG, "saveItem for Learn:Call ViewModel to save Item!" + downloadUrl);
    }

}

