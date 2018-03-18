package com.mtech.parttimeone.photolearn.fragments;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
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

import com.mtech.parttimeone.photolearn.Adapter.ListModel;
import com.mtech.parttimeone.photolearn.Adapter.MeListAdapter;
import com.mtech.parttimeone.photolearn.R;

import java.util.ArrayList;

public class MeFragment extends Fragment implements AdapterView.OnItemClickListener {

   // private OnFragmentInteractionListener mListener;
   String userName = "ChangLingLiu";
   ArrayList<ListModel>infoList;
   private ListView listView;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        infoList  = new ArrayList();
        ListModel itemmodel = new ListModel();
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
            //logout function.
            onClicklogout();
            default:
            break;
        }
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    private void onClicklogout(){

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
