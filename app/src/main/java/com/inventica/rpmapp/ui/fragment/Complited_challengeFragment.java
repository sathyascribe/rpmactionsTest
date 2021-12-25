package com.inventica.rpmapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.QueryMasterModel;

public class Complited_challengeFragment extends Fragment {

    private Complited_challengeFragment galleryViewModel;

    Spinner sp_query;
    Button btn_submit;
    Integer sp_queryId;
    EditText query_msg_et;
    QueryMasterModel Obj_Class_countryDetails;

    TextView time_tv,date_tv,distance_tv,challengeName;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_complitedchallenges, container, false);

        String ChallengeName = getArguments().getString("ChallengeName");
        String Distance = getArguments().getString("Distance");
        String startDate = getArguments().getString("startDate");
        String endDate = getArguments().getString("endDate");
        String time = getArguments().getString("time");

        time_tv = (TextView) root.findViewById(R.id.time_tv);
        date_tv = (TextView) root.findViewById(R.id.date_tv);
        distance_tv = (TextView) root.findViewById(R.id.distance_tv);
        challengeName = (TextView) root.findViewById(R.id.challengeName);

        challengeName.setText(ChallengeName);
        distance_tv.setText(Distance);
        date_tv.setText(startDate+" - "+endDate);
        time_tv.setText(time);

        Log.e("tag","ChallengeName new:"+ChallengeName);
        return root;
    }
}