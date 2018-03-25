package com.mtech.parttimeone.photolearn.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_item_detail);
       // initView();
    }

    private void initView() {

        viewPager= (ViewPager) findViewById(R.id.quiz_detail_viewpager);

        mFragmentManager = getSupportFragmentManager();
        itemDetailFragment = new QuizItemDetailFragment();
        setDefaultFragment();
    }

    private void setDefaultFragment() {

//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.item_detail_fragment_content, itemDetailFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

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

        QuizItemDetailAdapter adapter = new QuizItemDetailAdapter(this, obj);

    }

}
