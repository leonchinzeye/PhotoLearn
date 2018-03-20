package com.mtech.parttimeone.photolearn.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.adapter.TitleListFragmentAdapter;
import com.mtech.parttimeone.photolearn.fragments.TitleListFragment;

public class TitleListActivity extends AppCompatActivity  implements TitleListFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String learningSessionID = intent.getStringExtra("learning_session_id"); //if it's a string you stored.

        FragmentPagerAdapter adapterViewPager;

        setContentView(R.layout.activity_title_list);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new TitleListFragmentAdapter(getSupportFragmentManager(),learningSessionID);
        vpPager.setAdapter(adapterViewPager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

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
