package com.mtech.parttimeone.photolearn.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.mtech.parttimeone.photolearn.Adapter.OptionItem;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemDetailAdapter;

import com.mtech.parttimeone.photolearn.Adapter.QuizItemObj;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.QuizItemDetailActivity;
import com.mtech.parttimeone.photolearn.bo.QuizAttemptBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizItemDetailFragment extends Fragment  {

    private static final String ARG_PARAM1 = "param1";

    private ListView listView;
    QuizItemBO itemObj;
    private int mParam1;
    QuizItemDetailAdapter adapter;
    Boolean isReview;

    public QuizItemDetailFragment() {
        // Required empty public constructor
    }

    public static QuizItemDetailFragment newInstance(int params) {
        // Required empty public constructor
        QuizItemDetailFragment fragment = new QuizItemDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_item_detail, container, false);

        listView = (ListView) view.findViewById(R.id.quiz_item_detail_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (isReview||position==0||position==1){
                    return;
                }
                CheckedTextView checkedTextView = view.findViewById(R.id.option_selection_text);
                //TODO
                /*
                QuizAttemptBO attemptBO = itemObj.getQuizAttemptBO();
                attemptBO.addAns(!checkedTextView.isChecked(),position-2,itemObj.getAnswer().size());
                */
                checkedTextView.setChecked(!checkedTextView.isChecked());
                adapter.notifyDataSetChanged();
                ((QuizItemDetailActivity)getActivity()).updateData(itemObj, mParam1);
            }
        });
        adapter  = new QuizItemDetailAdapter(getActivity(),itemObj,mParam1);
        adapter.isReview = isReview;
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
        itemObj = ((QuizItemDetailActivity)getActivity()).getItemArray().get(mParam1);
        isReview = ((QuizItemDetailActivity)getActivity()).isReview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateData(){
        QuizItemDetailActivity activity = new QuizItemDetailActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listView.setOnItemClickListener(null);
    }


}
