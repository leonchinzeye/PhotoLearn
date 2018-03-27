package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;

/**
 * Created by Zhikai on 14/3/2018.
 */

public class LearningSessionListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<LearningSessionBO> mDataSource;

    public LearningSessionListAdapter(Context mContext, List<LearningSessionBO> mDataSource) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {

        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_learningsession_layout, viewGroup, false);

        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.learning_session_title);

        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.learning_session_id);

        //TextView creatorTextView =
        //        (TextView) rowView.findViewById(R.id.learning_session_creator);

        TextView dateTextView =
                (TextView) rowView.findViewById(R.id.learning_session_date);

        LearningSessionBO ls = (LearningSessionBO) getItem(i);

        titleTextView.setText(ls.getCourseCode());
        subtitleTextView.setText(ls.getCourseModule());
        //creatorTextView.setText(ls.getLearningSessionCreator());
        dateTextView.setText(ls.getCourseDate());
        return rowView;
    }

    public void setDataSource(ArrayList<LearningSessionBO> mDataSource){
        this.mDataSource = mDataSource;
        this.notifyDataSetChanged();
    }
}
