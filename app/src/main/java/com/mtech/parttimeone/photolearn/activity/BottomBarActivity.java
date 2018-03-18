/**
 * Activity for the Bottom Navigation Bar.
 * Created by Changling
 */
package com.mtech.parttimeone.photolearn.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.fragments.MeFragment;

public class BottomBarActivity extends BaseActivity {

    Fragment meFragment;
    Fragment mainFragment;
    Fragment currentFragment;
    private FragmentManager mFragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_main:
                    setCurrentFragment(mainFragment);
                    return true;
                case R.id.navigation_me:
                    setCurrentFragment(meFragment);
                    return true;
            }
            return false;
        }
    };

    private void setDefaultFragment(){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
              fragmentTransaction.add(R.id.fragment_content, mainFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        currentFragment = mainFragment;
    }

    // INIT the UI
    private void intheView(){
        mFragmentManager = getSupportFragmentManager();
        meFragment = new MeFragment();
        mainFragment = new Fragment();
    }

    private void setCurrentFragment(Fragment fragment){
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = mFragmentManager.beginTransaction();
         if (fragment.isAdded()) {
                    fragmentTransaction1.hide(currentFragment).show(fragment);
                } else {

                    fragmentTransaction1.hide(currentFragment).add(R.id.fragment_content, fragment);
                    fragmentTransaction1.addToBackStack(null);

                }
         currentFragment = fragment;
         fragmentTransaction1.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_main);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        intheView();
        setDefaultFragment();
    }

}
