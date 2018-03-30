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
import android.widget.AdapterView;
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
import com.mtech.parttimeone.photolearn.activity.QuizItemDetailActivity;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;

/**
 * Created by changling on 18/3/18.
 */

public class QuizItemDetailAdapter extends BaseAdapter  {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_PHOTO = 1;
    private static final int TYPE_OPTION = 2;
    private static final int TYPE_EXPLANATION = 3;


    public boolean isReview;

    private Context context;

    //private QuizItemObj quizItemObj = new QuizItemObj();
    private QuizItemBO quizItemObj = new QuizItemBO();
    private int currentPage;

    public QuizItemDetailAdapter(Context context, QuizItemBO obj, int page){
        this.context = context;
        this.quizItemObj = obj;
        this.currentPage = page;
    }

    @Override
    public int getCount(){
        if (isReview){
            return 3 + quizItemObj.getAnswer().size();
        }else{
            return 2 + quizItemObj.getAnswer().size();
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount(){
        if (isReview){
            return 4;
        }else{
            return 3;
        }

    }

    @Override
    public int getItemViewType(int position){

        if (isReview){
            if (position ==0){
                return TYPE_TITLE;
            }else if (position == 1){
                return TYPE_PHOTO;
            }else if (position == getCount()-1){
                return TYPE_EXPLANATION;
            }else {
                return TYPE_OPTION;
            }
        }else{
            if (position ==0){
                return TYPE_TITLE;
            }else if (position == 1){
                return TYPE_PHOTO;
            }else {
                return TYPE_OPTION;
            }
        }

    }

    public Object getItem(int position) {

        int type = getItemViewType(position);

        if (type == TYPE_TITLE){
            return quizItemObj.getItemtitle();
        }else if (type == TYPE_PHOTO){
            return quizItemObj.getItemDesc();
        }else if (position == TYPE_EXPLANATION){
            return quizItemObj.getDetailedSolution();
        }else {
            return quizItemObj.getAnswer().get(position - 2);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        ViewHolder optionHolder = null;
        if (convertView==null){
            switch (type){
                case TYPE_TITLE:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quiz_title_item_layout,parent,false);
                    TextView titleText = (TextView)convertView.findViewById(R.id.text_quiz_title);
                    titleText.setText(quizItemObj.getItemtitle());
                }
                break;
                case TYPE_PHOTO:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quizitem_photo_layout,parent,false);
                    TextView textView = (TextView) convertView.findViewById(R.id.quiz_photo_desc);
                    ImageView imageView  = (ImageView) convertView.findViewById(R.id.photo_view);
                    imageView.setImageResource(R.drawable.pic2);
                    textView.setText(quizItemObj.getItemDesc());
                }
                break;
                case TYPE_OPTION:{

                    if (!isReview){
                        convertView = LayoutInflater.from(context).inflate(
                                R.layout.quiz_option_selection_layout,parent,false);
                        optionHolder = new ViewHolder(convertView,R.id.option_selection_text);
                        convertView.setTag(optionHolder);
                    }else {
                        convertView = LayoutInflater.from(context).inflate(
                                R.layout.quiz_option_item_read_only_layout,parent,false);
                        TextView optionText = (TextView)convertView.findViewById(R.id.text_quiz_option_readonly);
                        optionText.setText(quizItemObj.getAnswer().get(position-2));
                        //TODO
                        /*
                        if (quizItemObj.isAnsCorrect()){
                            if (quizItemObj.getQuizAttemptBO().isAns(position-2)) {
                                optionText.setTextColor(Color.parseColor("#0B6623")); // green
                            }else {
                                optionText.setTextColor(Color.parseColor("#FF000000")); // black
                            }
                        }else{
                            if (quizItemObj.isAns(position-2)){
                                optionText.setTextColor(Color.parseColor("#FF4040")); // red
                            }else {
                                optionText.setTextColor(Color.parseColor("#FF000000")); // black
                            }
                        }
                        */
                    }
                }
                break;
                case TYPE_EXPLANATION:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.quizitem_explanation_layout,parent,false);
                    TextView explanationText = (TextView)convertView.findViewById(R.id.quiz_explanation_desc);
                    explanationText.setText(quizItemObj.getDetailedSolution());
                }
                break;
            }
        }else{

            if (!isReview){
                switch (type){
                    case TYPE_OPTION:{
                        optionHolder  = (ViewHolder) convertView.getTag();
                    }
                    break;
                }
            }
        }

        if (!isReview){
            switch (type){
                case TYPE_OPTION:{
                    String optionStr = quizItemObj.getAnswer().get(position-2);
                    optionHolder.optionTextView.setText(optionStr);
                    optionHolder.optionTextView.setTag(position);
                    //TODO
                    //optionHolder.optionTextView.setChecked(quizItemObj.getQuizAttemptBO().isAns(position-2));
//                optionHolder.optionTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//                        ((CheckedTextView)v).toggle();
//                        int position = (int)v.getTag();
//                        OptionItem optionItem = quizItemObj.options.get(position-2);
//                        optionItem.setAns(!optionItem.getAns());
//                        ((QuizItemDetailActivity)context).updateData(quizItemObj, currentPage);
//                    }
//                });
                }
         }

        }

        return convertView;
    }



    private class ViewHolder{
        private CheckedTextView optionTextView;

        private ViewHolder(View convertView,int id) {
            optionTextView = (CheckedTextView) convertView.findViewById(id);
        }
    }

}
