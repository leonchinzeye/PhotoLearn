package com.mtech.parttimeone.photolearn.activity;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.mtech.parttimeone.photolearn.Adapter.OptionItem;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemDetailAdapter;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemDetailViewPagerAdapter;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemObj;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.Item;
import com.mtech.parttimeone.photolearn.fragments.LearningSessionListFragment;
import com.mtech.parttimeone.photolearn.fragments.QuizItemDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class QuizItemDetailActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private ViewPager viewPager;
    private List<QuizItemBO> itemArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_detail);
        initView();
        initData();
        //viewPager.setOffscreenPageLimit(fragmentList.size());
    }

    private void initView() {

        viewPager= (ViewPager) findViewById(R.id.quiz_detail_viewpager);
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        getMenuInflater().inflate(R.menu.menu_edit_quizitemdetail_submit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_unsave) {
            ExitandSave();
            return true;
        } else if (item.getItemId() == R.id.action_unsave){
            ExitandUnsave();
            return true;
        }else if (item.getItemId() == R.id.action_submit){
            Submit();
            return true;
        }
        return false;
    }


    public void initData(){


        QuizItemBO obj_0 = new QuizItemBO();
        obj_0.setItemtitle("How to resolve this issue");
        obj_0.setPhotoDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_0.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");
        List<String> anslist = new ArrayList<>();
        anslist.add("true");
        anslist.add("false");
        anslist.add("true");
        anslist.add("false");
        obj_0.setOptions(anslist);
        QuizAttemptBO attemptBO_0 = new QuizAttemptBO("",new ArrayList<>());
        attemptBO_0.setAnswer(anslist);
        //obj_0.setQuizAttemptBO(attemptBO_0);
        List<String> optionList = new ArrayList<>();
        optionList.add("ques0_This is option 1");
        optionList.add("ques0_This is option 2");
        optionList.add("ques0_This is option 3");
        optionList.add("ques0_This is option 4");
        obj_0.setOptions(optionList);
        itemArray.add(obj_0);

        QuizItemBO obj_1 = new QuizItemBO();
        obj_1.setItemtitle("How to resolve this issue 1");
        obj_1.setPhotoDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_1.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");
        List<String> anslist1 = new ArrayList<>();
        anslist1.add("true");
        anslist1.add("false");
        anslist1.add("true");
        anslist1.add("false");
        obj_1.setOptions(anslist);
        QuizAttemptBO attemptBO_01 = new QuizAttemptBO("",new ArrayList<>());;
        attemptBO_01.setAnswer(anslist1);
        //obj_1.setQuizAttemptBO(attemptBO_01);
        List<String> optionList1 = new ArrayList<>();
        optionList1.add("ques1_This is option 1");
        optionList1.add("ques1_This is option 2");
        optionList1.add("ques1_This is option 3");
        optionList1.add("ques1_This is option 4");
        obj_1.setOptions(optionList1);
        itemArray.add(obj_1);

        QuizItemBO obj_2 = new QuizItemBO();
        obj_2.setItemtitle("How to resolve this issue 2");
        obj_2.setPhotoDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_2.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");
        List<String> anslist2 = new ArrayList<>();
        anslist2.add("true");
        anslist2.add("false");
        anslist2.add("true");
        anslist2.add("false");
        obj_2.setOptions(anslist2);
        QuizAttemptBO attemptBO_02 = new QuizAttemptBO("",new ArrayList<>());;
        attemptBO_02.setAnswer(anslist2);
       // obj_2.setQuizAttemptBO(attemptBO_02);
        List<String> optionList2 = new ArrayList<>();
        optionList2.add("ques2_This is option 1");
        optionList2.add("ques2_This is option 2");
        optionList2.add("ques2_This is option 3");
        optionList2.add("ques2_This is option 4");
        optionList2.add("ques2_This is option 5");
        optionList2.add("ques2_This is option 6");

        obj_2.setOptions(optionList2);
        itemArray.add(obj_2);

        QuizItemBO obj_3 = new QuizItemBO();
        obj_3.setItemtitle("How to resolve this issue 3");
        obj_3.setPhotoDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_3.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");
        List<String> anslist3 = new ArrayList<>();
        anslist3.add("true");
        anslist3.add("false");
        anslist3.add("true");
        anslist3.add("false");
        obj_3.setOptions(anslist);
        QuizAttemptBO attemptBO_03 = new QuizAttemptBO("",new ArrayList<>());;
        attemptBO_03.setAnswer(anslist3);
        //obj_3.setQuizAttemptBO(attemptBO_03);
        List<String> optionList3 = new ArrayList<>();
        optionList3.add("ques3_This is option 1");
        optionList3.add("ques3_This is option 2");
        optionList3.add("ques3_This is option 3");
        optionList3.add("ques3_This is option 4");
        obj_3.setOptions(optionList3);
        itemArray.add(obj_3);

        QuizItemBO obj_4 = new QuizItemBO();
        obj_4.setItemtitle("How to resolve this issue 4");
        obj_4.setPhotoDesc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj_4.setDetailedSolution("he Internet of things (IoT) is the network of physical devices, vehicles.");
        List<String> anslist4 = new ArrayList<>();
        anslist4.add("true");
        anslist4.add("false");
        anslist4.add("true");
        anslist4.add("false");
        obj_4.setOptions(anslist);
        QuizAttemptBO attemptBO_04 = new QuizAttemptBO("",new ArrayList<>());;
        attemptBO_04.setAnswer(anslist4);
       // obj_4.setQuizAttemptBO(attemptBO_04);
        List<String> optionList4 = new ArrayList<>();
        optionList4.add("ques4_This is option 1");
        optionList4.add("ques4_This is option 2");
        optionList4.add("ques4_This is option 3");
        optionList4.add("ques4_This is option 4");
        obj_4.setOptions(optionList4);
        itemArray.add(obj_4);



//        for (int i=0;i<items.size();i++){
//
//            QuizItemDetailFragment itemDetailFragment = QuizItemDetailFragment.newInstance(i);
//            fragmentList.add(itemDetailFragment);
//        }

       // viewPager.setAdapter(new QuizItemDetailViewPagerAdapter(mFragmentManager,items.size(),items));

        viewPager.setAdapter(new QuizItemDetailViewPagerAdapter(mFragmentManager, itemArray.size()));

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
////                QuizItemDetailFragment itemDetailFragment = (QuizItemDetailFragment)fragmentList.get(position);
////                itemDetailFragment.setItemObj(items.get(position));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    private void ExitandSave(){
    }

    private void ExitandUnsave(){

    }

    private void Submit(){
        showScoreReview("Your total score is 70%, Go back and review the Answer?");
    }

    public void updateData(QuizItemBO obj,int position){

        itemArray.set(position,obj);
        viewPager.getAdapter().notifyDataSetChanged();
    }

    public List<QuizItemBO> getItemArray() {
        return itemArray;
    }

    private void showScoreReview(String content){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Score");
       builder.setMessage(content);
       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               // go back to Quiz title page ?
           }
       });

       builder.create();
       builder.show();

   }

}
