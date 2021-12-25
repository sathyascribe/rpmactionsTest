package com.inventica.rpmapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.inventica.rpmapp.R;

import org.openapitools.client.api.DataApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChallengeDetailsFragment extends Fragment {

    private ChallengeDetailsFragment galleryViewModel;

    TextView challengeName_tv,DaysRemaining_tv,distance_tv,total_distance_tv,duration_tv,time_taken_tv,participants_tv,events_tv;
    LinearLayout leaderBoard_ll;
    // array list for storing entries.
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_challengesdetails, container, false);

        challengeName_tv = (TextView) root.findViewById(R.id.challengeName_tv);
        DaysRemaining_tv = (TextView) root.findViewById(R.id.DaysRemaining_tv);
        distance_tv = (TextView) root.findViewById(R.id.distance_tv);
        total_distance_tv = (TextView) root.findViewById(R.id.total_distance_tv);
        duration_tv = (TextView) root.findViewById(R.id.duration_tv);
        time_taken_tv = (TextView) root.findViewById(R.id.time_taken_tv);
        participants_tv = (TextView) root.findViewById(R.id.participants_tv);
        events_tv = (TextView) root.findViewById(R.id.events_tv);
        leaderBoard_ll= (LinearLayout) root.findViewById(R.id.leaderBoard_ll);

        leaderBoard_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fragment  fragment2 = new LeaderBoardFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();               }
        });

        String ChallengeName = getArguments().getString("ChallengeName");
        String Distance = getArguments().getString("Distance");
        String remainingDays = getArguments().getString("remainingDays");
        String participents = getArguments().getString("participents");

        String startDate = getArguments().getString("startDate");
        String endDate = getArguments().getString("endDate");
        Log.e("tag","startDate:"+startDate);
        Log.e("tag","endDate:"+endDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat dateDurationFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        Date objDate = null;
        try {
            objDate = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date objendDate = null;
        try {
            objendDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date objdurationDate = null;
        try {
            objdurationDate = dateDurationFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Expected date format
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm aa");
        SimpleDateFormat dateFormatDuration = new SimpleDateFormat("dd MMM YYYY");

        String finalstartDate = dateFormat2.format(objDate);
        String finalendDate = dateFormat2.format(objendDate);
        String finalDuration= dateFormatDuration.format(objdurationDate);
        Log.e("Date Format:", "Final Date:"+finalstartDate);
       // Log.e("tag","startDate d:"+d);

        /* String time = getArguments().getString("time");*/

        challengeName_tv.setText(ChallengeName);
        distance_tv.setText("0.00 / "+Distance);
        DaysRemaining_tv.setText(remainingDays+" Days Remaining");
        participants_tv.setText(participents+" Participants");
        events_tv.setText(ChallengeName);
        total_distance_tv.setText(Distance);
        time_taken_tv.setText(finalstartDate+" - "+finalendDate);
        duration_tv.setText(finalDuration);
        return root;
    }

}