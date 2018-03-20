package com.mtech.parttimeone.photolearn.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.ItemListActivity;
import com.mtech.parttimeone.photolearn.activity.MainPageActivity;
import com.mtech.parttimeone.photolearn.activity.TitleListActivity;
import com.mtech.parttimeone.photolearn.adapter.LearningSessionListAdapter;
import com.mtech.parttimeone.photolearn.adapter.TitleListAdapter;
import com.mtech.parttimeone.photolearn.dummyModel.Title;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TitleListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TitleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleListFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView mListView;

    private OnFragmentInteractionListener mListener;

    public TitleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TitleListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TitleListFragment newInstance(String param1, String param2) {
        TitleListFragment fragment = new TitleListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title_list, container, false);
        mListView = (ListView) view.findViewById(R.id.title_list_view);
        setListview(container.getContext());
        registerForContextMenu(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView sessiontitle = (TextView) view.findViewById(R.id.learning_session_title);

                Intent myIntent = new Intent(getActivity(), ItemListActivity.class);
                myIntent.putExtra("learning_title", sessiontitle.getText()); //Optional parameters
                myIntent.putExtra("learning_session_ID", mParam1); //Optional parameters
                myIntent.putExtra("Type", mParam2); //Optional parameters
                getActivity().startActivity(myIntent);

                //Toast.makeText(getBaseContext(), sessionID.getText(), Toast.LENGTH_LONG).show();

            }
        });

        getActivity().setTitle("Learning/Quiz Title");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setListview(Context c){
        //Test class for displaying items

        ArrayList<Title> lsl;
        dummyDao dao = new dummyDao();

        switch (mParam2) {
            case "TITLE":
                lsl = dao.GetTitleList(mParam1);
                break;
            case "QUIZ":
                lsl = dao.GetQuizList(mParam1);
                break;

            default:
                lsl = new ArrayList<Title>();
            break;

        }

//        //test
//        LearningSession ls1 = new LearningSession("Internet of things","MTECH-ORO-001","2018/07/03","Liu Changling");
//        LearningSession ls2 = new LearningSession("Computing Power","MTECH-ORO-002","2018/07/03","Lim Zhi Kai");
//        LearningSession ls3 = new LearningSession("Digital User Interfaces","MTECH-ORO-003","2018/07/03","Lim Zhi Kai");
//        LearningSession ls4 = new LearningSession("Agile Project Management","MTECH-ORO-004","2018/07/03","Liu Changling");
//        LearningSession ls5 = new LearningSession("Business Process Re-Engineering","MTECH-ORO-005","2018/07/03","Lim Zhi Kai");
//        LearningSession ls6 = new LearningSession("Business Communication","MTECH-ORO-006","2018/07/03","Lim Zhi Kai");
//        LearningSession ls7 = new LearningSession("Java Programming","MTECH-ORO-007","2018/07/03","Liu Changling");
//        LearningSession ls8 = new LearningSession("Internet of things","MTECH-ORO-001","2018/07/03","Liu Changling");
//        LearningSession ls9 = new LearningSession("Computing Power","MTECH-ORO-002","2018/07/03","Lim Zhi Kai");
//        LearningSession ls10 = new LearningSession("Digital User Interfaces","MTECH-ORO-003","2018/07/03","Liu Changling");
//        LearningSession ls11 = new LearningSession("Agile Project Management","MTECH-ORO-004","2018/07/03","Lim Zhi Kai");
//        LearningSession ls12 = new LearningSession("Business Process Re-Engineering","MTECH-ORO-005","2018/07/03","Liu Changling");
//        LearningSession ls13 = new LearningSession("Business Communication","MTECH-ORO-006","2018/07/03","Lim Zhi Kai");
//        LearningSession ls14 = new LearningSession("Java Programming","MTECH-ORO-007","2018/07/03","Lim Zhi Kai");
//
//        lsl.add(ls1);
//        lsl.add(ls2);
//        lsl.add(ls3);
//        lsl.add(ls4);
//        lsl.add(ls5);
//        lsl.add(ls6);
//        lsl.add(ls7);
//        lsl.add(ls8);
//        lsl.add(ls9);
//        lsl.add(ls10);
//        lsl.add(ls11);
//        lsl.add(ls12);
//        lsl.add(ls13);
//        lsl.add(ls14);

        TitleListAdapter TitleAdapter = new TitleListAdapter(c,lsl);

        mListView.setAdapter(TitleAdapter);


    }
}
