package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.mtech.parttimeone.photolearn.R;

import com.mtech.parttimeone.photolearn.activity.MainActivity;
import com.mtech.parttimeone.photolearn.fragments.MeFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by changling on 17/3/18.
 */

public class MeListAdapter extends BaseAdapter {

    public static final int TYPE_USERNAME = 0;
    public static final int TYPE_ACTION = 1;

    Context context;
    private String text_item0 = "";
    private ArrayList<ListModel> actionList;

    public MeListAdapter(Context context,String text_item,ArrayList<ListModel> actionlist){
        this.actionList = actionlist;
        this.context = context;
        this.text_item0 = text_item;
    }

    @Override
    public int getCount(){
        return 1+actionList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0){
            return TYPE_USERNAME;
        }else{
            return TYPE_ACTION;
        }
    }

    public Object getItem(int position) {
        if (position ==0){
            return text_item0;
        }else{
            return actionList.get(position-1);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            if (position == 0){
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.text_layout,parent,false);
            }else{
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.pictureandtext_item_layout,parent,false);
            }
        }

        if (position == 0){

            TextView textView = (TextView) convertView.findViewById(R.id.text_t);
            convertView.setOnClickListener(null);
            textView.setText(text_item0);

            }else{

            Drawable img = actionList.get(position-1).getImage();
            String title = actionList.get(position-1).getTitle();
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            TextView textView = (TextView) convertView.findViewById(R.id.textt_p);
            imageView.setImageDrawable(img);
            textView.setText(title);

        }

        return convertView;
    }

}
