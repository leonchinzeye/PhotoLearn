package com.mtech.parttimeone.photolearn.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by changling on 25/3/18.
 */

public class QuizItemDetailViewPagerAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> list;


    public  QuizItemDetailViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }



}
