package com.mtech.parttimeone.photolearn.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.ViewModel.LearningSessionViewModel;
import com.mtech.parttimeone.photolearn.ViewModel.LearningTitleViewModel;
import com.mtech.parttimeone.photolearn.activity.BottomBarActivity;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.bo.LearningTitleBO;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateLearningTitleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateLearningTitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateLearningTitleFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1"; //UUID
    private static final String ARG_PARAM2 = "param2"; //New/Update
    private static final String ARG_PARAM3 = "param3"; //SessionID

    // TODO: Rename and change types of parameters
    private String mParam1; //UUID
    private String mParam2; //New/Update
    private String mParam3; //SessionID

    private EditText txtLearningTitle;
    Button btnSave;
    android.support.v4.app.Fragment FragmentSelf;

    private OnFragmentInteractionListener mListener;

    public CreateLearningTitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateLearningTitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateLearningTitleFragment newInstance(String param1, String param2, String param3) {
        CreateLearningTitleFragment fragment = new CreateLearningTitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_create_learning_title, container, false);

        btnSave = view.findViewById(R.id.btnSaveLearningTitle);
        txtLearningTitle = (EditText)view.findViewById(R.id.editTextLearningTitle);

        FragmentSelf = this;

        switch (mParam2){
            case "NEW":

                break;

            case "UPDATE":

                dummyDao dao = new dummyDao();
                LearningTitleViewModel vmLearningTitle = ViewModelProviders.of(this).get(LearningTitleViewModel.class);
                vmLearningTitle.getLearningTitle(mParam3,mParam1).observe(this, new Observer<LearningTitleBO>() {
                    @Override
                    public void onChanged(@Nullable LearningTitleBO learningTitleBOS) {
                        txtLearningTitle.setText(learningTitleBOS.getTitle());

                    }
                });
                break;

            default:
                break;

        }

        btnSave.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                LearningTitleBO ltbo = new LearningTitleBO();


                dummyDao dao = new dummyDao();

                ltbo.setTitle(txtLearningTitle.getText().toString());
                ltbo.setSessionId(mParam1);
                ltbo.setCreatedBy(dao.getUserName(FragmentSelf));
                //ltbo.setTitleId(UUID.randomUUID().toString());


                try {

                    switch (mParam2){
                        case "NEW":
                            dao.createLearningTitle(FragmentSelf,ltbo);
                            Toast.makeText(getActivity(),"Learning Title (" + ltbo.getTitle() +") created!",Toast.LENGTH_SHORT).show();

                            break;

                        case "UPDATE":
                            ltbo.setUuid(mParam3);
                            dao.updateLearningTitle(FragmentSelf,ltbo);
                            Toast.makeText(getActivity(),"Learning Title (" + ltbo.getTitle() +") updated!",Toast.LENGTH_SHORT).show();

                            break;

                        default:
                            break;

                    }


                     txtLearningTitle.setText("");
                    BottomBarActivity act = (BottomBarActivity)getActivity();
                    act.setTitleListFragment(ltbo.getSessionId());

                } catch (Exception e) {
                    Toast.makeText(getActivity(),"Error adding Learning Title!",Toast.LENGTH_SHORT).show();
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
