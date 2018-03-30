package com.mtech.parttimeone.photolearn.fragments;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mtech.parttimeone.photolearn.Adapter.ListModel;
import com.mtech.parttimeone.photolearn.Adapter.MeListAdapter;
import com.mtech.parttimeone.photolearn.R;
import com.mtech.parttimeone.photolearn.activity.BottomBarActivity;
import com.mtech.parttimeone.photolearn.activity.LearnItemCreationActivity;
import com.mtech.parttimeone.photolearn.activity.MainActivity;
import com.mtech.parttimeone.photolearn.activity.QuizItemCreationActivity;
import com.mtech.parttimeone.photolearn.application.GlobalPhotoLearn;

import java.util.ArrayList;

public class MeFragment extends Fragment implements AdapterView.OnItemClickListener {

   // private OnFragmentInteractionListener mListener;
   String userName = "";
   ArrayList<ListModel>infoList;
   private ListView listView;
   private GlobalPhotoLearn globalPhotoLearn;
   private FirebaseAuth mAuth;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        infoList  = new ArrayList();
        ListModel itemmodel = new ListModel();
        globalPhotoLearn = (GlobalPhotoLearn)getActivity().getApplicationContext();
        mAuth = globalPhotoLearn.getmAuth();
        userName = mAuth.getCurrentUser().getDisplayName();

        ListModel itemmodel0 = new ListModel();
        itemmodel0.setTitle("Trainer");
        Drawable d0 = ContextCompat.getDrawable(getActivity(), R.drawable.ic_home_black_24dp);
        itemmodel0.setImage(d0);
        infoList.add(itemmodel0);

        itemmodel.setTitle("Logout");
        Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.ic_home_black_24dp);
        itemmodel.setImage(d);
        infoList.add(itemmodel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        listView = (ListView) view.findViewById(R.id.me_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MeListAdapter adapter = new MeListAdapter(getActivity(),userName,infoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        switch (position){
            case 1:
                changeMode();
                break;
            case 2:
            //logout function.
            onClicklogout();
            break;
            default:
            break;
        }
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    private void onClicklogout(){

        GoogleSignInClient mGoogleSignInClient = globalPhotoLearn.getmGoogleSignInClient();
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent mainActivityIntent = new Intent(getActivity(),MainActivity.class);
                        getActivity().startActivity(mainActivityIntent);
                    }
                });
    }


    private void changeMode(){
        Intent createLearningItem = new Intent(getActivity(),LearnItemCreationActivity.class);
        getActivity().startActivity(createLearningItem);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
