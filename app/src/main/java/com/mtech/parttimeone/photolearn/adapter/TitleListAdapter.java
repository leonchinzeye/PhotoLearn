package com.mtech.parttimeone.photolearn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.bo.TitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.Title;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class TitleListAdapter<T> extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mDataSource;


    public TitleListAdapter(Context mContext, List<T> mDataSource) {
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

        TextView titleIDTextView =
                (TextView) rowView.findViewById(R.id.title_id);

        TextView creatorTextView =
                (TextView) rowView.findViewById(R.id.learning_session_creator);


        TitleBO title = (TitleBO) getItem(i);

        titleTextView.setText(title.getTitle());
        creatorTextView.setText(title.getSessionId());
        titleIDTextView.setText(title.getUuid());
        return rowView;
    }
}
