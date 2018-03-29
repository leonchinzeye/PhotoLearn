package com.mtech.parttimeone.photolearn.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mtech.parttimeone.photolearn.fragments.QuizItemDetailFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by changling on 25/3/18.
 */

public class QuizItemDetailViewPagerAdapter extends FragmentStatePagerAdapter {

    //public List<Fragment> list;
    private int size;

    public  QuizItemDetailViewPagerAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        return QuizItemDetailFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return size;
    }


//    @Override
//    public int getItemPosition(Object object){
////        if (object instanceof QuizItemDetailFragment){
////            ((QuizItemDetailFragment) object).updateData();
////        }
//        return QuizItemDetailViewPagerAdapter.POSITION_NONE;//super.getItemPosition(object);
//    }

}
