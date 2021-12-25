package com.inventica.rpmapp.ui.activity.connection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inventica.rpmapp.R;


public class UserInfoFragment extends Fragment {



    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        MyListUserModel myListUserModel = getArguments().getParcelable("Object");

        TextView name = view.findViewById(R.id.textName);
        TextView email = view.findViewById(R.id.textEmail);
        TextView gender = view.findViewById(R.id.textGender);
        TextView group = view.findViewById(R.id.textGroup);

        name.setText(myListUserModel.getInviteUserName());
        email.setText(myListUserModel.getInviteUserEmailId());
        gender.setText("Male");
        group.setText(myListUserModel.getGroupName());

        return view;
    }
}