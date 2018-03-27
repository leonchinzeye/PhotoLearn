package com.mtech.parttimeone.photolearn.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtech.parttimeone.photolearn.Adapter.LearningSessionListAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.ViewModel.LearningSessionViewModel;
import com.mtech.parttimeone.photolearn.activity.BottomBarActivity;
import com.mtech.parttimeone.photolearn.bo.LearningSessionBO;
import com.mtech.parttimeone.photolearn.dummyModel.LearningSession;
import com.mtech.parttimeone.photolearn.dummyModel.dummyDao;
import com.mtech.parttimeone.photolearn.enumeration.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearningSessionListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearningSessionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningSessionListFragment extends android.support.v4.app.Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mListView;
    private LearningSessionListAdapter lslAdap;

    static public enum UserType {
        TRAINER,PARTICIPANT
    }

    private OnFragmentInteractionListener mListener;

    public LearningSessionListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningSessionListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningSessionListFragment newInstance(String param1, String param2) {
        LearningSessionListFragment fragment = new LearningSessionListFragment();
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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_session_list, container, false);

        mListView = (ListView) view.findViewById(R.id.fragment_learning_session_list_view);

        try {
            setListview();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        registerForContextMenu(mListView);

        getActivity().setTitle("Learning Session");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //Execute when an item is selected via context menu
                TextView sessiontitle = (TextView) view.findViewById(R.id.learning_session_title);
                TextView sessionID = (TextView) view.findViewById(R.id.learning_session_id);

                BottomBarActivity act = (BottomBarActivity)getActivity();
                act.setTitleListFragment(sessionID.getText().toString());

                //Toast.makeText(getBaseContext(), sessionID.getText(), Toast.LENGTH_LONG).show();

            }
        });



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_search, menu);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add:
                BottomBarActivity act = (BottomBarActivity)getActivity();
                act.setCreateLearningSessionFragment();
                // Not implemented here
                return false;
            case R.id.action_search:
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("Search For Learning Session");
                alert.setMessage("Enter your Learning Session ID below:");

                // Set an EditText view to get user input
                final EditText input = new EditText(getContext());
                alert.setView(input);

                alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        // Filter the BO for the specific learning session
                        dummyDao dao = new dummyDao();
                        lslAdap.setDataSource(dao.GetLearningSession(value));
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
                // Do Fragment menu item stuff here
                return true;

            default:
                break;
        }

        return false;
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

    private void setListview() throws InterruptedException {
        //Test class for displaying items
        //TODO Should do to AsyncTask

        dummyDao dao = new dummyDao();

        //ArrayList<LearningSessionBO> lsl = dao.GetLearningSessionAll();


        LearningSessionViewModel vmLearningSession = ViewModelProviders.of(this).get(LearningSessionViewModel.class);
        vmLearningSession.getLearningSessions(dao.getUserName(this), com.mtech.parttimeone.photolearn.enumeration.UserType.TRAINER).observe(this, new Observer<List<LearningSessionBO>>() {
            @Override
            public void onChanged(@Nullable List<LearningSessionBO> learningSessionBOS) {
                lslAdap = new LearningSessionListAdapter(getActivity(), learningSessionBOS);
                mListView.setAdapter(lslAdap);
            }
        });
//        List<LearningSessionBO> lsl =

//        ArrayList<LearningSessionBO> lsl =dao.GetLearningSessionByUser(this,"");

            }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        //menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Update");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Delete");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Update"){
            Toast.makeText(getContext(),"Update Called",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Delete"){
            Toast.makeText(getContext(),"Delete Called",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

}
