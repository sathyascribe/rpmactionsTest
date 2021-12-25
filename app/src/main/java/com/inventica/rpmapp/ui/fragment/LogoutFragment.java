package com.inventica.rpmapp.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.activity.home.MainActivity;

public class LogoutFragment extends Fragment {

    private LogoutFragment galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        showCustomLogoutDialog(root);
      //  ((LoginHomeActivity)getActivity()).displayView(0);
    /*    Button submit=root.findViewById(R.id.submit_btn);
        Button cancel=root.findViewById(R.id.cancel_btn);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(root);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionHelper.setToken(getActivity(), "", "");
                ConnectionHelper.setLoginDetails(getActivity(),"","");



                // Log.e("tag","name: "+challengeName.getText().toString());
            //    challengeName_tv.setText(challengeName.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();*/
        return root;
    }

    void showCustomLogoutDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.logout_dialog,viewGroup, false);

        Button submit=dialogView.findViewById(R.id.logout_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionHelper.setToken(getActivity(), "", "");
                ConnectionHelper.setLoginDetails(getActivity(),"","");
                ((MainActivity)getActivity()).displayView(0);

                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

}