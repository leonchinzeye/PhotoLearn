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
import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.Item;
import com.mtech.parttimeone.photolearn.fragments.LearningSessionListFragment;
import com.mtech.parttimeone.photolearn.fragments.QuizItemDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class QuizItemDetailActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private ViewPager viewPager;
    private List<QuizItemObj> items = new ArrayList<>();
//    private List<Fragment> fragmentList = new ArrayList<>();

    private SubMenu dropdownList;

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
//        dropdownList = menu.addSubMenu("");
//        dropdownList.add(0,EXITANDUNSAVE,0,"Exit And Unsave");
//        dropdownList.add(0,EXITANDSAVE,0,"Exit And Save");
//        dropdownList.add(0,SUBMIT,0,"Submit");
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

        QuizItemObj obj = new QuizItemObj();
        obj.setQuiz_title("How to resolve this issue");
        obj.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.");

        OptionItem item0 = obj.options.get(0);
        item0.setOptionDetail("This is option 1");
        OptionItem option1 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj.options.add(option1);
        OptionItem option2 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj.options.add(option2);
        OptionItem option3 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj.options.add(option3);

        QuizItemObj obj1 = new QuizItemObj();
        obj1.setQuiz_title("How to resolve this issue 01");
        obj1.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj1.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        OptionItem item10 = obj1.options.get(0);
        item10.setOptionDetail("This is option 1");
        OptionItem option11 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj1.options.add(option1);
        OptionItem option12 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj1.options.add(option2);
        OptionItem option13 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj1.options.add(option3);

        QuizItemObj obj2 = new QuizItemObj();
        obj2.setQuiz_title("How to resolve this issue 02");
        obj2.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles. 02");
        obj2.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.02");
        OptionItem item20 = obj2.options.get(0);
        item20.setOptionDetail("This is option 1");
        OptionItem option21 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj2.options.add(option1);
        OptionItem option22 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj2.options.add(option2);
        OptionItem option23 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj2.options.add(option3);


        QuizItemObj obj3 = new QuizItemObj();
        obj3.setQuiz_title("How to resolve this issue 03");
        obj3.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles.03");
        obj3.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.03");
        OptionItem item30 = obj3.options.get(0);
        item30.setOptionDetail("This is option 1");
        OptionItem option31 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj3.options.add(option1);
        OptionItem option32 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj3.options.add(option2);
        OptionItem option33 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj3.options.add(option3);

        QuizItemObj obj4 = new QuizItemObj();
        obj4.setQuiz_title("How to resolve this issue 04");
        obj4.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles.04");
        obj4.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.04");
        OptionItem item40 = obj4.options.get(0);
        item40.setOptionDetail("This is option 1");
        OptionItem option41 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj4.options.add(option1);
        OptionItem option42 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj4.options.add(option2);
        OptionItem option43 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj4.options.add(option3);

        items.add(obj);
        items.add(obj1);
        items.add(obj2);
        items.add(obj3);
        items.add(obj4);

//        for (int i=0;i<items.size();i++){
//
//            QuizItemDetailFragment itemDetailFragment = QuizItemDetailFragment.newInstance(i);
//            fragmentList.add(itemDetailFragment);
//        }

       // viewPager.setAdapter(new QuizItemDetailViewPagerAdapter(mFragmentManager,items.size(),items));

        viewPager.setAdapter(new QuizItemDetailViewPagerAdapter(mFragmentManager, items.size()));

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

    public void updateData(QuizItemObj obj,int position){
        items.remove(position);
        items.add(position,obj);
        viewPager.getAdapter().notifyDataSetChanged();
    }

   public List<QuizItemObj>getItems(){
       return this.items;
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
