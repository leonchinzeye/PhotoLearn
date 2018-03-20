package com.mtech.parttimeone.photolearn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;

/**
 * Created by Zhikai on 14/3/2018.
 */

public class LearningSessionListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<LearningSession> mDataSource;

    public LearningSessionListAdapter(Context mContext, ArrayList<LearningSession> mDataSource) {
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

        TextView creatorTextView =
                (TextView) rowView.findViewById(R.id.learning_session_creator);

        TextView dateTextView =
                (TextView) rowView.findViewById(R.id.learning_session_date);

        LearningSession ls = (LearningSession) getItem(i);

        titleTextView.setText(ls.getLearningSessionTitle());
        subtitleTextView.setText(ls.getLearningSessionID());
        creatorTextView.setText(ls.getLearningSessionCreator());
        dateTextView.setText(ls.getLearningSessionDate());
        return rowView;
    }
}
