package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.QuizItemCreationActivity;

/**
 * Created by changling on 18/3/18.
 */

public class QuizItemDetailAdapter extends BaseAdapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_PHOTO = 1;
    private static final int TYPE_OPTION = 2;
    private static final int TYPE_EXPLANATION = 3;


    private boolean isReview = true;

    private Context context;

    private QuizItemObj quizItemObj = new QuizItemObj();


    public QuizItemDetailAdapter(Context context, QuizItemObj obj){
        this.context = context;
        this.quizItemObj = obj;
    }

    @Override
    public int getCount(){
        return 3 + quizItemObj.options.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount(){
        return 4;
    }

    @Override
    public int getItemViewType(int position){
        if (position ==0){
            return TYPE_TITLE;
        }else if (position == 1){
            return TYPE_PHOTO;
        }else if (position == getCount()-1){
            return TYPE_EXPLANATION;
        }else {
            return TYPE_OPTION;
        }

    }

    public Object getItem(int position) {

        int type = getItemViewType(position);

        if (type == TYPE_TITLE){
            return quizItemObj.getQuiz_title();
        }else if (type == TYPE_PHOTO){
            return quizItemObj.getQuiz_desc();
        }else if (position == TYPE_EXPLANATION){
            return quizItemObj.getExplanation();
        }else {
            return quizItemObj.options.get(position - 2);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        if (convertView==null){
            switch (type){
                case TYPE_TITLE:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quiz_title_item_layout,parent,false);
                    TextView titleText = (TextView)convertView.findViewById(R.id.text_quiz_title);
                    titleText.setText(quizItemObj.getQuiz_title());
                }
                break;
                case TYPE_PHOTO:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quizitem_photo_layout,parent,false);
                    TextView textView = (TextView) convertView.findViewById(R.id.quiz_photo_desc);
                    ImageView imageView  = (ImageView) convertView.findViewById(R.id.photo_view);
                    imageView.setImageResource(R.drawable.pic2);
                    textView.setText(quizItemObj.getQuiz_desc());
                }
                break;
                case TYPE_OPTION:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quiz_option_selection_layout,parent,false);

                    CheckedTextView optionText = (CheckedTextView)convertView.findViewById(R.id.option_selection_text);
                    OptionItem item = quizItemObj.options.get(position-2);
                    optionText.setText(item.getOptionDetail());
                    optionText.setTag(position);
                    optionText.setChecked(item.isAns);
                    optionText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            optionText.toggle();
                            int position = (int)optionText.getTag();
                            OptionItem optionItem = quizItemObj.options.get(position-2);
                            optionItem.isAns = !optionItem.isAns;
                        }
                    });
                }
                break;
                case TYPE_EXPLANATION:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quizitem_explanation_layout,parent,false);
                    TextView explanationText = (TextView)convertView.findViewById(R.id.quiz_explanation_desc);
                    explanationText.setText(quizItemObj.getQuiz_desc());
                }
                break;
            }
        }

        return convertView;
    }

}
