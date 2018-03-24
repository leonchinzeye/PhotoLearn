package com.mtech.parttimeone.photolearn.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mtech.parttimeone.photolearn.R;

import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

public class ModeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    final String trainer = "Trainer";
    final String participant="Participant";

    private ListView modeListV;
    private String[] modeOptions = {trainer, participant};
    private ArrayAdapter<String> itemsAdapter;
    private String currentMode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        createListView();
    }

    //create the menu button.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_done,menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void createListView() {
        modeListV = (ListView) findViewById(R.id.modelist);
        itemsAdapter = new ArrayAdapter<String>(this, R.layout.modeitem_layout, modeOptions){

            //set row height.
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position,convertView,parent);
                // Get the Layout Parameters for ListView Current Item View
                LayoutParams params = view.getLayoutParams();
                params.height = 150;
                view.setLayoutParams(params);

                return view;
            }
        };
       // modeListV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        modeListV.setAdapter(itemsAdapter);
        modeListV.setOnItemClickListener(this);
    }

    //call back function for click the item - set the string of mode.
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        currentMode = ((TextView)view).getText().toString();
        //itemsAdapter.notifyDataSetChanged();
    }

    //call back function for "Done" button on right top.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done) {
            registertheMode();
            return true;
        } else {
            Toast.makeText(ModeActivity.this, R.string.error_gernal, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void registertheMode(){
        if (currentMode.equals(trainer)){
            // proceed with trainer.

        }else if (currentMode.equals(participant)){
            // proceed with participant

        }else{
            // didn't select any mode
            Toast.makeText(ModeActivity.this, R.string.error_selectmode, Toast.LENGTH_SHORT).show();
        }
    }
}
