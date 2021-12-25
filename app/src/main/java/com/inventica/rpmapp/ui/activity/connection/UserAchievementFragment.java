package com.inventica.rpmapp.ui.activity.connection;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.inventica.rpmapp.R;

import java.util.ArrayList;


public class UserAchievementFragment extends Fragment {

    private GridView gridView;
    private Context mContext;
    public UserAchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_achivement, container, false);
        MyListUserModel myListUserModel = getArguments().getParcelable("Object");
        mContext = getActivity();
        gridView = view.findViewById(R.id.gridView);
        ArrayList<String> courseModelArrayList = new ArrayList<String>();
        courseModelArrayList.add("1");
        courseModelArrayList.add("1");
        courseModelArrayList.add("1"); courseModelArrayList.add("1");
        courseModelArrayList.add("1");
        courseModelArrayList.add("1"); courseModelArrayList.add("1"); courseModelArrayList.add("1");
        courseModelArrayList.add("1");
        courseModelArrayList.add("1"); courseModelArrayList.add("1");
        courseModelArrayList.add("1");
        courseModelArrayList.add("1");
        courseModelArrayList.add("1"); courseModelArrayList.add("1");
        courseModelArrayList.add("1");

        AchievementGridAdapter adapter = new AchievementGridAdapter(mContext, courseModelArrayList);
        gridView.setAdapter(adapter);



        return view;
    }


}