package com.mtech.parttimeone.photolearn.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.BottomBarActivity;
import com.mtech.parttimeone.photolearn.bo.LearningTitleBO;
import com.mtech.parttimeone.photolearn.bo.QuizTitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateQuizTitleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateQuizTitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuizTitleFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText txtQuizTitle;
    Button btnSave;
    android.support.v4.app.Fragment FragmentSelf;


    private OnFragmentInteractionListener mListener;

    public CreateQuizTitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateQuizTitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateQuizTitleFragment newInstance(String param1, String param2) {
        CreateQuizTitleFragment fragment = new CreateQuizTitleFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_quiz_title, container, false);

        btnSave = view.findViewById(R.id.btnSaveQuizTitle);
        txtQuizTitle = (EditText)view.findViewById(R.id.editQuizTitle);

        FragmentSelf = this;

        btnSave.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                QuizTitleBO ltbo = new QuizTitleBO();


                dummyDao dao = new dummyDao();

                ltbo.setTitle(txtQuizTitle.getText().toString());
                ltbo.setSessionId(mParam1);
                ltbo.setCreatedBy(dao.getUserName(FragmentSelf));
                //ltbo.setTitleId(UUID.randomUUID().toString());


                try {

                    dao.createQuizTitle(FragmentSelf,ltbo);
                    Toast.makeText(getActivity(),"Quiz Title (" + ltbo.getTitle() +") created!",Toast.LENGTH_SHORT).show();
                    txtQuizTitle.setText("");
                    BottomBarActivity act = (BottomBarActivity)getActivity();
                    act.setTitleListFragment(ltbo.getSessionId());

                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Error adding Quiz Title!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }

            }
        });

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
}
