package com.mtech.parttimeone.photolearn.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.mtech.parttimeone.photolearn.Adapter.OptionItem;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemDetailAdapter;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemObj;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.fragments.QuizItemDetailFragment;

public class QuizItemDetailActivity extends BaseActivity {

    private QuizItemDetailFragment itemDetailFragment;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_detail);
        initView();
    }

    private void initView(){
        mFragmentManager = getSupportFragmentManager();
        itemDetailFragment = new QuizItemDetailFragment();
        setDefaultFragment();
    }

    private void setDefaultFragment(){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.item_detail_fragment_content, itemDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
