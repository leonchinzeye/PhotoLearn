package com.mtech.parttimeone.photolearn.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.fragments.TitleListFragment;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class TitleListFragmentAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String LearningSessionID;

    public TitleListFragmentAdapter(FragmentManager fm,String LearningSessionID) {

        super(fm);
        this.LearningSessionID=LearningSessionID;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return TitleListFragment.newInstance(LearningSessionID,"TITLE");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return TitleListFragment.newInstance(LearningSessionID,"QUIZ");
            default:
                return null;
        }
    }


    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "Learning Titles";
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return "Quiz Titles";
            default:
                return null;
        }
    }


}
