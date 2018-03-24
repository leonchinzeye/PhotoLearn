package com.mtech.parttimeone.photolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.mtech.parttimeone.photolearn.R;

public class CreateLearningSessionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_learning_session);

        this.setTitle("Create Learning Session");
    }
}
