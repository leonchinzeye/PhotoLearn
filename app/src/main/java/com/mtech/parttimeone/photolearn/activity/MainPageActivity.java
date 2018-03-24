package com.mtech.parttimeone.photolearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.Adapter.LearningSessionListAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;

import java.util.ArrayList;

public class MainPageActivity extends BaseActivity {

    private ListView mListView;
    static public enum UserType {
        TRAINER,PARTICIPANT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mListView = (ListView) findViewById(R.id.learning_session_list_view);

        setListview();

        registerForContextMenu(mListView);

        this.setTitle("Learning Session");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView sessiontitle = (TextView) view.findViewById(R.id.learning_session_title);
                TextView sessionID = (TextView) view.findViewById(R.id.learning_session_id);

                Intent myIntent = new Intent(MainPageActivity.this, TitleListActivity.class);
                myIntent.putExtra("learning_session_id", sessionID.getText()); //Optional parameters
                MainPageActivity.this.startActivity(myIntent);

                //Toast.makeText(getBaseContext(), sessionID.getText(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void setListview(){
        //Test class for displaying items

        ArrayList<LearningSession> lsl = new ArrayList<LearningSession>();

        //test
        LearningSession ls1 = new LearningSession("Internet of things","MTECH-ORO-002","2018/07/03","Liu Changling");
        LearningSession ls2 = new LearningSession("Computing Power","MTECH-ORO-001","2018/07/03","Lim Zhi Kai");
        LearningSession ls3 = new LearningSession("Digital User Interfaces","MTECH-ORO-003","2018/07/03","Lim Zhi Kai");
        LearningSession ls4 = new LearningSession("Agile Project Management","MTECH-ORO-004","2018/07/03","Liu Changling");
        LearningSession ls5 = new LearningSession("Business Process Re-Engineering","MTECH-ORO-005","2018/07/03","Lim Zhi Kai");
        LearningSession ls6 = new LearningSession("Business Communication","MTECH-ORO-006","2018/07/03","Lim Zhi Kai");
        LearningSession ls7 = new LearningSession("Java Programming","MTECH-ORO-007","2018/07/03","Liu Changling");
        LearningSession ls8 = new LearningSession("Internet of things","MTECH-ORO-002","2018/07/03","Liu Changling");
        LearningSession ls9 = new LearningSession("Computing Power","MTECH-ORO-001","2018/07/03","Lim Zhi Kai");
        LearningSession ls10 = new LearningSession("Digital User Interfaces","MTECH-ORO-003","2018/07/03","Liu Changling");
        LearningSession ls11 = new LearningSession("Agile Project Management","MTECH-ORO-004","2018/07/03","Lim Zhi Kai");
        LearningSession ls12 = new LearningSession("Business Process Re-Engineering","MTECH-ORO-005","2018/07/03","Liu Changling");
        LearningSession ls13 = new LearningSession("Business Communication","MTECH-ORO-006","2018/07/03","Lim Zhi Kai");
        LearningSession ls14 = new LearningSession("Java Programming","MTECH-ORO-007","2018/07/03","Lim Zhi Kai");

        lsl.add(ls1);
        lsl.add(ls2);
        lsl.add(ls3);
        lsl.add(ls4);
        lsl.add(ls5);
        lsl.add(ls6);
        lsl.add(ls7);
        lsl.add(ls8);
        lsl.add(ls9);
        lsl.add(ls10);
        lsl.add(ls11);
        lsl.add(ls12);
        lsl.add(ls13);
        lsl.add(ls14);

        LearningSessionListAdapter lslAdap = new LearningSessionListAdapter(this,lsl);

        mListView.setAdapter(lslAdap);


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

            //menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Update");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "Delete");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Update"){
            Toast.makeText(getApplicationContext(),"Update Called",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Delete"){
            Toast.makeText(getApplicationContext(),"Delete Called",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }





}
