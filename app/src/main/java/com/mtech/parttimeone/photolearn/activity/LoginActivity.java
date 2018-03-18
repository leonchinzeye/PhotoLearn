package com.mtech.parttimeone.photolearn.activity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View.OnClickListener;
import com.mtech.parttimeone.photolearn.R;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

public class LoginActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    //1.0define the value
    ListView loginListV;
    String[] loginOptions = {"Google", "Apple"};

    //2.0function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createListView();
        super.setPageTitle("Login");
    }

    protected void createListView() {
        loginListV = (ListView) findViewById(R.id.loginlist);
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loginOptions){

            //set row height.
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);

                // Get the Layout Parameters for ListView Current Item View
                LayoutParams params = view.getLayoutParams();

                // Set the height of the Item View
                params.height = 150;
                view.setLayoutParams(params);

                return view;
            }
        };
        loginListV.setAdapter(itemsAdapter);
        loginListV.setOnItemClickListener(this);
    }

    //2.1 call back function for click the item on listview
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if (position == 0) {
            //put function for login from google.
            Toast.makeText(LoginActivity.this, "login from google", Toast.LENGTH_SHORT).show();
        } else {
            //put function for login from facebook
            Toast.makeText(LoginActivity.this, "login from facebook", Toast.LENGTH_SHORT).show();
        }
    }
}