package com.mtech.parttimeone.photolearn.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mtech.parttimeone.photolearn.Adapter.OptionItem;
import com.mtech.parttimeone.photolearn.Adapter.QuizItemDetailAdapter;

import com.mtech.parttimeone.photolearn.Adapter.QuizItemObj;
import com.mtech.parttimeone.photolearn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizItemDetailFragment extends Fragment {

   private ListView listView;

    public QuizItemDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_item_detail, container, false);
        listView = (ListView) view.findViewById(R.id.quiz_item_detail_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QuizItemObj obj = new QuizItemObj();
        obj.setQuiz_title("How to resolve this issue");
        obj.setExplanation("The Internet of things (IoT) is the network of physical devices, vehicles.");
        obj.setQuiz_desc("The Internet of things (IoT) is the network of physical devices, vehicles.");
        OptionItem item0 = obj.options.get(0);
        item0.setOptionDetail("This is option 1");
        OptionItem option1 = new OptionItem();
        option1.setOptionDetail("This is option 2");
        obj.options.add(option1);
        OptionItem option2 = new OptionItem();
        option2.setOptionDetail("This is option 3");
        obj.options.add(option2);
        OptionItem option3 = new OptionItem();
        option3.setOptionDetail("This is option 4");
        obj.options.add(option3);

        QuizItemDetailAdapter adapter  = new QuizItemDetailAdapter(getActivity(),obj);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }



}
