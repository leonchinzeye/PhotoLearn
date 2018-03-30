package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.LearnItemCreationActivity;
import com.mtech.parttimeone.photolearn.activity.QuizItemCreationActivity;
import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;

import java.util.Random;

/**
 * Created by changling on 18/3/18.
 */

public class QuizItemCreationAdapter extends BaseAdapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_PHOTO = 1;
    private static final int TYPE_OPTION = 2;
    private static final int TYPE_EXPLANATION = 3;

    private Context context;

    //private QuizItemObj quizItemObj = new QuizItemObj();
    public QuizItemBO quizItemObj = new QuizItemBO ();

    public QuizItemCreationAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount(){
        return 3 + quizItemObj.getAnswer().size();
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
        ViewHolder titleHolder = null;
        ViewHolder descHolder = null;
        ViewHolder optionHolder = null;
        ViewHolder explanationHolder = null;
        if (convertView==null){
            switch (type){
                case TYPE_TITLE:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.inputitem_layout,parent,false);
                    EditText editText = (EditText)convertView.findViewById(R.id.text_input);
                    titleHolder = new ViewHolder(convertView,R.id.text_input);
                    convertView.setTag(titleHolder);

                }
                break;
                case TYPE_PHOTO:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.takephotoitem_layout,parent,false);
                    addButtonAction(convertView,R.id.add_photo_button,position);
                    EditText editText = (EditText)convertView.findViewById(R.id.input_photo_desc);
                    descHolder = new ViewHolder(convertView,R.id.input_photo_desc);
                    convertView.setTag(descHolder);
                }
                break;
                case TYPE_OPTION:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.option_item_layout,parent,false);

                    EditText editText = (EditText)convertView.findViewById(R.id.option_text);
//                    OptionItem optionItem = quizItemObj.options.get(position - 2);

                    editText.setTextColor(quizItemObj.isAns(position-2)?Color.parseColor("#0B6623"):Color.parseColor("#FF000000"));
                    optionHolder = new ViewHolder(convertView,R.id.option_text);
//                  Random random=new Random();
//                  optionItem.setOptionIndex(String.valueOf(random.nextInt()));

                    convertView.setTag(optionHolder);
                }
                break;
                case TYPE_EXPLANATION:{
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.explaination_item_layout,parent,false);
                    EditText editText = (EditText)convertView.findViewById(R.id.explanation_text);
                    explanationHolder = new ViewHolder(convertView,R.id.explanation_text);
                    convertView.setTag(explanationHolder);
                }
                break;
            }
        }else{

            switch (type){
                case TYPE_TITLE:{
                    titleHolder = (ViewHolder) convertView.getTag();
                }
                break;
                case TYPE_PHOTO:{
                    descHolder = (ViewHolder) convertView.getTag();
                }
                break;
                case TYPE_OPTION:{
                    optionHolder = (ViewHolder) convertView.getTag();
                }
                break;
                case TYPE_EXPLANATION:{
                    explanationHolder = (ViewHolder) convertView.getTag();
                }
                break;
            }
        }

        switch (type){
            case TYPE_TITLE:{

                monitorEdit(titleHolder,TYPE_TITLE,position);
            }
            break;
            case TYPE_PHOTO:{
                monitorEdit(descHolder,TYPE_PHOTO,position);
            }
            break;
            case TYPE_OPTION:{
                monitorEdit(optionHolder,TYPE_OPTION,position);
                addButtonAction(convertView,R.id.option_button,position);
                optionHolder.editText.setTextColor( quizItemObj.isAns(position-2) ? Color.parseColor("#0B6623"):Color.parseColor("#FF000000"));

            }
            break;
            case TYPE_EXPLANATION:{
                monitorEdit(explanationHolder,TYPE_EXPLANATION,position);
            }
            break;
        }

        return convertView;
    }

    private void addButtonAction(View view, int id,int postion){
        ImageButton addBth = (ImageButton)view.findViewById(id);
        addBth.setTag(postion);
        addBth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == R.id.add_photo_button){
                   ((QuizItemCreationActivity) context).addPhoto(v);
                }else if (id == R.id.option_button) {
                    int p = (int)v.getTag();
                    editOption(addBth,p);

                }
            }
        });
    }

    private void editOption(View v,int position){
        PopupMenu popup = new PopupMenu(context, v, Gravity.CENTER);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_optionedit, popup.getMenu());
        popup.getMenu().add(Menu.NONE, 1, 1, R.string.add_ans_option);
        popup.getMenu().add(Menu.NONE, 2, 2,R.string.delete_ans_option);
        popup.getMenu().add(Menu.NONE, 3, 3, quizItemObj.isAns(position-2) ? R.string.unmark_as_ans:R.string.mark_as_ans);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case 1:{ // add option
                        addNewOption();
                    }
                    break;
                    case 2:{ // delete option
                        //popup.dismiss();
                        deleteOption(position);
                    }
                    break;
                    case 3:{ // mark or unmark as answer
                        changeAnswerOption(position);
                    }
                    break;
                }
                return true;
            }
        });
        popup.show();

    }


    public void addNewOption(){
       // OptionItem optionitem = new OptionItem();
        quizItemObj.addOption("");
        notifyDataSetChanged();

    }

    public void deleteOption(int position){
        if (quizItemObj.getAnswer().size() == 1){
            Toast.makeText(context,R.string.error_keep_one_option, Toast.LENGTH_SHORT).show();
        }else {
            quizItemObj.deleteOptionAndAnswer(position-2);
            notifyDataSetChanged();
        }

    }

    public void changeAnswerOption(int position){
        quizItemObj.updateAns(position-2);
        notifyDataSetChanged();
    }

    private void monitorEdit(ViewHolder holder,int type,int position){

        if (holder.editText.getTag() instanceof TextWatcher) {
            holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
        }

        switch (type){
            case TYPE_TITLE:{
                holder.editText.setText(quizItemObj.getItemtitle()!=null?quizItemObj.getItemtitle():"");
            }
            break;
            case TYPE_PHOTO:{
                holder.editText.setText(quizItemObj.getItemDesc() != null?quizItemObj.getItemDesc():"");
            }
            break;
            case TYPE_OPTION:{
              // OptionItem optionitem = quizItemObj.options.get(position-2);
             //  holder.editText.setText(optionitem.getOptionDetail() != null?optionitem.getOptionDetail():"");
                holder.editText.setText(quizItemObj.getAnswer().get(position-2));
            }
            break;
            case TYPE_EXPLANATION:{
                holder.editText.setText(quizItemObj.getDetailedSolution() != null?quizItemObj.getDetailedSolution():"");
            }
            break;
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

                switch (type) {
                    case TYPE_TITLE: {
                        quizItemObj.setItemtitle(!TextUtils.isEmpty(s) ? s.toString() : "");
                        //Toast.makeText(context, quizItemObj.getQuiz_title(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case TYPE_PHOTO: {
                        quizItemObj.setItemDesc(!TextUtils.isEmpty(s) ? s.toString() : "");
                        //Toast.makeText(context, quizItemObj.getQuiz_desc(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case TYPE_OPTION: {
//                        OptionItem optionitem = quizItemObj.options.get(position - 2);
//                        optionitem.setOptionDetail(!TextUtils.isEmpty(s) ? s.toString() : "");
                         quizItemObj.updateOption(!TextUtils.isEmpty(s) ? s.toString() : "",position-2);
                        //Toast.makeText(context, optionitem.getOptionDetail(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case TYPE_EXPLANATION: {
                        quizItemObj.setDetailedSolution(!TextUtils.isEmpty(s) ? s.toString() : "");
                        //Toast.makeText(context, quizItemObj.getExplanation(), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
            }
        };
        holder.editText.addTextChangedListener(watcher);
        holder.editText.setTag(watcher);
    }



    private class ViewHolder{
        private EditText editText;

        private ViewHolder(View convertView,int id) {
            editText = (EditText) convertView.findViewById(id);
        }
    }
}
