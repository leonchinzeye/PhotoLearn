package com.mtech.parttimeone.photolearn.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mtech.parttimeone.photolearn.R;

public class CreateLearningTitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_learning_title);

        this.setTitle("Create Learning Title");
    }
}
