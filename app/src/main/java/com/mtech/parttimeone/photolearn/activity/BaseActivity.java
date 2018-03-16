package com.mtech.parttimeone.photolearn.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.mtech.parttimeone.photolearn.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();
    }

    private void setCustomActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout_actionbar);
    }

    void setPageTitle(String title){
        ActionBar actionBar = getActionBar();
        TextView tiletexV = findViewById(R.id.page_id);
        tiletexV.setText(title);
    }
}
