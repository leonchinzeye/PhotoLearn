package com.mtech.parttimeone.photolearn.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mtech.parttimeone.photolearn.Adapter.TitleListAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.Adapter.ItemRecyclerAdapter;
import com.mtech.parttimeone.photolearn.ViewModel.LearningItemViewModel;
import com.mtech.parttimeone.photolearn.ViewModel.LearningTitleViewModel;
import com.mtech.parttimeone.photolearn.ViewModel.QuizItemViewModel;
import com.mtech.parttimeone.photolearn.ViewModel.QuizTitleViewModel;
import com.mtech.parttimeone.photolearn.activity.BottomBarActivity;
import com.mtech.parttimeone.photolearn.activity.LearnItemCreationActivity;
import com.mtech.parttimeone.photolearn.activity.QuizItemCreationActivity;
import com.mtech.parttimeone.photolearn.bo.ItemBO;
import com.mtech.parttimeone.photolearn.bo.LearningItemBO;
import com.mtech.parttimeone.photolearn.bo.LearningTitleBO;
import com.mtech.parttimeone.photolearn.bo.QuizItemBO;
import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.bo.TitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.Item;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemRecyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemRecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemRecyclerFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1; //Sessionid
    private String mParam2; //titleid
    private String mParam3; //Type

    private List<ItemBO> itemList;
    private RecyclerView recyclerView;
    private ItemRecyclerAdapter mAdapter;

    private OnFragmentInteractionListener mListener;

    public ItemRecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemRecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemRecyclerFragment newInstance(String param1, String param2, String param3) {
        ItemRecyclerFragment fragment = new ItemRecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1); //SessionID
        args.putString(ARG_PARAM2, param2); //TitleID
        args.putString(ARG_PARAM3, param3); //Type
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }


        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        BottomBarActivity act = (BottomBarActivity)getActivity();

        switch(item.getItemId()){
            case R.id.action_add:
                switch (mParam3) {
                    case "TITLE":
                        Intent it = new Intent(getActivity(), LearnItemCreationActivity.class);
                        it.putExtra("TitleID", mParam2);
                        startActivity(it);
                        break;
                    case "QUIZ":
                        Intent iq = new Intent(getActivity(), QuizItemCreationActivity.class);
                        iq.putExtra("TitleID", mParam2);
                        startActivity(iq);
                        break;

                    default:

                        break;

                }

                // Not implemented here
                return false;

            default:
                break;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerView);

        dummyDao dao = new dummyDao();

        //itemList = dao.GetLearningItemList(mParam1,mParam2);




        switch (mParam3){
            case "TITLE":
                getActivity().setTitle("Learning Title:" + mParam2 );
                LearningItemViewModel vmLearningItem = ViewModelProviders.of(this).get(LearningItemViewModel.class);
                vmLearningItem.getLearningItems(mParam2).observe(this, new Observer<List<LearningItemBO>>() {
                    @Override
                    public void onChanged(@Nullable List<LearningItemBO> learningItemBOS) {
                        mAdapter = new ItemRecyclerAdapter(learningItemBOS);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                        SnapHelper helper = new LinearSnapHelper();
               //         helper.attachToRecyclerView(recyclerView);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case "QUIZ":
                getActivity().setTitle("Quiz Title:"+ mParam2);
                QuizItemViewModel vmQuizItem = ViewModelProviders.of(this).get(QuizItemViewModel.class);
                vmQuizItem.getQuizItems(mParam2).observe(this, new Observer<List<QuizItemBO>>() {
                    @Override
                    public void onChanged(@Nullable List<QuizItemBO> QuizItemBOS) {
                        mAdapter = new ItemRecyclerAdapter(QuizItemBOS);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                        SnapHelper helper = new LinearSnapHelper();
                       // helper.attachToRecyclerView(recyclerView);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                break;
            default:
                getActivity().setTitle("Unknown");
                break;

        }
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

    @Override
    public void onResume() {
        super.onResume();

        if (mAdapter!=null)
            mAdapter.notifyDataSetChanged();
    }
}
