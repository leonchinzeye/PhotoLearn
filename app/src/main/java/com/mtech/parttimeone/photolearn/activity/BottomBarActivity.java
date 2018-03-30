/**
 * Activity for the Bottom Navigation Bar.
 * Created by Changling
 */
package com.mtech.parttimeone.photolearn.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.dummyModel.Item;
import com.mtech.parttimeone.photolearn.fragments.CreateLearningSessionFragment;
import com.mtech.parttimeone.photolearn.fragments.CreateLearningTitleFragment;
import com.mtech.parttimeone.photolearn.fragments.CreateQuizTitleFragment;
import com.mtech.parttimeone.photolearn.fragments.ItemRecyclerFragment;
import com.mtech.parttimeone.photolearn.fragments.LearningSessionListFragment;
import com.mtech.parttimeone.photolearn.fragments.MeFragment;
import com.mtech.parttimeone.photolearn.fragments.TitleListContainerFragment;
import com.mtech.parttimeone.photolearn.fragments.TitleListFragment;

public class BottomBarActivity extends BaseActivity   implements ItemRecyclerFragment.OnFragmentInteractionListener,
                                                                      LearningSessionListFragment.OnFragmentInteractionListener,
                                                                      TitleListContainerFragment.OnFragmentInteractionListener,
                                                                      TitleListFragment.OnFragmentInteractionListener,
                                                                        CreateLearningSessionFragment.OnFragmentInteractionListener,
                                                                        CreateLearningTitleFragment.OnFragmentInteractionListener,
                                                                        CreateQuizTitleFragment.OnFragmentInteractionListener
{

    Fragment meFragment;
    Fragment mainFragment;
    Fragment currentFragment;
    private FragmentManager mFragmentManager;
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            mFragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    setCurrentFragment(mainFragment);
                    setPageTitle("Session");
                    return true;
                case R.id.navigation_me:
                    setCurrentFragment(meFragment);
                    setPageTitle("Me");
                    return true;
            }
            return false;
        }
    };

    private void setDefaultFragment(){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
              //To Changling. Why did you use add? Its causing UI problems..
        //fragmentTransaction.add(R.id.fragment_content, mainFragment);
        fragmentTransaction.replace(R.id.fragment_content, mainFragment);
     //   fragmentTransaction.add(R.id.fragment_content, mainFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        currentFragment = mainFragment;
    }

    // INIT the UI
    private void intheView(Fragment targetFragment){
        mFragmentManager = getSupportFragmentManager();
        if (meFragment==null)
            meFragment = new MeFragment();
        //mainFragment = new Fragment();
        //mainFragment = (Fragment) ItemRecyclerFragment.newInstance("MTECH-ORO-002","All about IOT", "TITLE");
        //mainFragment = (Fragment) LearningSessionListFragment.newInstance("DUMMY ACCOUNT","LEARNING SESSION ID");
        mainFragment = targetFragment;

        setDefaultFragment();
    }

    // INIT the UI
    private void initView(){
        mFragmentManager = getSupportFragmentManager();
        meFragment = new MeFragment();
        //mainFragment = new Fragment();
        //mainFragment = (Fragment) ItemRecyclerFragment.newInstance("MTECH-ORO-002","All about IOT", "TITLE");
        //mainFragment = (Fragment) LearningSessionListFragment.newInstance("DUMMY ACCOUNT","LEARNING SESSION ID");
        mainFragment =  (Fragment) LearningSessionListFragment.newInstance("TO_BE_REPLACED","TO_BE_REPLACED");;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, mainFragment);
        fragmentTransaction.addToBackStack(BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
        currentFragment = mainFragment;
    }

    private void setCurrentFragment(Fragment fragment){
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = mFragmentManager.beginTransaction();
         if (fragment.isAdded()) {
                    initView();
                } else {

                    //fragmentTransaction1.hide(currentFragment).add(R.id.fragment_content, fragment);
                    fragmentTransaction1.replace(R.id.fragment_content, fragment);
                    //fragmentTransaction1.addToBackStack(null);

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

        initView();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public Fragment setLearningSessionListFragment(){
        mainFragment = (Fragment) LearningSessionListFragment.newInstance("TO_BE_REPLACED","TO_BE_REPLACED");
        intheView(mainFragment);
        return mainFragment;
    }

    public Fragment setTitleListFragment(String learningSessionID){
        mainFragment = (Fragment) TitleListContainerFragment.newInstance(learningSessionID,"TO_BE_REPLACED");
        intheView(mainFragment);
        return mainFragment;
    }

    public Fragment setItemListFragment(String learningSessionID,String learningSessionTitleID,String Type){
        mainFragment = (Fragment) ItemRecyclerFragment.newInstance(learningSessionID,learningSessionTitleID, Type);
        intheView(mainFragment);
        return mainFragment;
    }

    public Fragment setCreateLearningSessionFragment(String sessionID, String mode){
        mainFragment = (Fragment) CreateLearningSessionFragment.newInstance(sessionID,mode);
        intheView(mainFragment);
        return mainFragment;
    }

    public Fragment setCreateLearningTitleFragment(String learningSessionID){
        mainFragment = (Fragment) CreateLearningTitleFragment.newInstance(learningSessionID,"TO_BE_REPLACED");
        intheView(mainFragment);
        return mainFragment;
    }

    public Fragment setCreateQuizTitleFragment(String learningSessionID){
        mainFragment = (Fragment) CreateQuizTitleFragment.newInstance(learningSessionID,"TO_BE_REPLACED");
        intheView(mainFragment);
        return mainFragment;
       // setDefaultFragment();
        //setPageTitle("Session");

    }

    @Override
    void setPageTitle(String title){
        super.setPageTitle(title);
    }
}
