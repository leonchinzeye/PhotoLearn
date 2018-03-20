package com.mtech.parttimeone.photolearn.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.adapter.ItemRecyclerAdapter;
import com.mtech.parttimeone.photolearn.dummyModel.Item;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;
import com.mtech.parttimeone.photolearn.fragments.ItemRecyclerFragment;
import com.mtech.parttimeone.photolearn.fragments.TitleListFragment;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity   implements ItemRecyclerFragment.OnFragmentInteractionListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Intent intent = getIntent();
        String LearningTitle = intent.getStringExtra("learning_title"); //if it's a string you stored.
        String LearningSessionID = intent.getStringExtra("learning_session_ID");
        String Type = intent.getStringExtra("Type");

        ItemRecyclerFragment fragment =ItemRecyclerFragment.newInstance(LearningSessionID,LearningTitle, Type);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.itemlist_fragmentFrame,fragment);
        fragmentTransaction.commit();


    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
