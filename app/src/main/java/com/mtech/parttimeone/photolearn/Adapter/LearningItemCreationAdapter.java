package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.LearnItemCreationActivity;
import com.mtech.parttimeone.photolearn.activity.MainActivity;
import com.mtech.parttimeone.photolearn.bo.ItemBO;

/**
 * Created by changling on 18/3/18.
 */

public class LearningItemCreationAdapter extends BaseAdapter {

    public static final int TYPE_INPUT = 0;
    public static final int TYPE_PHOTO = 1;

    private Context context;
    String title;
    String desc_photo;

    public LearningItemCreationAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount(){
        return 2;
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
        if (position ==0){
            return TYPE_INPUT;
        }else{
            return TYPE_PHOTO;
        }

    }

    public Object getItem(int position) {
        return "";
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder titleHolder = null;
        ViewHolder descHolder = null;
        if (convertView==null){

            if (position == 0){
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.inputitem_layout,parent,false);
                EditText editText = (EditText)convertView.findViewById(R.id.text_input);
                titleHolder = new ViewHolder(convertView,R.id.text_input);
                convertView.setTag(titleHolder);

            }else if (position ==1){
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.takephotoitem_layout,parent,false);
                addButtonAction(convertView);
                descHolder = new ViewHolder(convertView,R.id.input_photo_desc);
                convertView.setTag(descHolder);

            }
        }else{
            if (position==0){
                titleHolder = (ViewHolder) convertView.getTag();

            }else if(position == 1){
                descHolder = (ViewHolder) convertView.getTag();
            }
        }

        if (position==0){

            titleHolder.editText.clearFocus();
            monitorEdit(titleHolder,position);


        }else if(position == 1){

            descHolder.editText.clearFocus();
            monitorEdit(descHolder,position);
        }

        return convertView;
    }

    private void addButtonAction(View view){
        ImageButton addBth = (ImageButton)view.findViewById(R.id.add_photo_button);
        addBth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LearnItemCreationActivity) context).addPhoto(v);
            }
        });
    }

    private void monitorEdit(ViewHolder holder,int position){
        if (holder.editText.getTag() instanceof TextWatcher) {
            holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
        }

        if (position ==0){
            holder.editText.setText(title);
        }else if(position==1){
            holder.editText.setText(desc_photo);
        }
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    if (position ==0){
                        title = "";
                    }else if(position==1){
                        desc_photo = "";
                    }
                } else {
                    if (position ==0){
                        title = s.toString();
                        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                    }else if(position==1){
                        desc_photo = s.toString();
                        Toast.makeText(context, desc_photo, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };
        holder.editText.addTextChangedListener(watcher);
        holder.editText.setTag(watcher);
    }

    private class ViewHolder{
        private EditText editText;

        public ViewHolder(View convertView,int id) {
            editText = (EditText) convertView.findViewById(id);
        }
    }

}
