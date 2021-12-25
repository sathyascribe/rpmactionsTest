package com.inventica.rpmapp.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.fragment.BeginChallengeFragment;
import com.inventica.rpmapp.ui.fragment.LeaderBoardFragment;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.JoinChallenegeModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeginChallenge_Activity extends AppCompatActivity {


    TextView challengeName_tv,DaysRemaining_tv,distance_tv,total_distance_tv,duration_tv,time_taken_tv,participants_tv,events_tv;
    LinearLayout leaderBoard_ll,fullscreen_ll;
    Button btn_joinchallenge;

    Context mContext;
    String challengeId_str,inviteId_str;
    List<DistanceModel> DistanceModelList;
    List<JoinChallenegeModel> challengeModelList;
    ProgressDialog dialog;
    String[] versionNumber,remaining_days,startTime,endTime,participants,challengeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_beginchallenges);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Begin Challenge");

        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);

        leaderBoard_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Fragment  fragment2 = new LeaderBoardFragment();
                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
              /*  fullscreen_ll.setVisibility(View.GONE);
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
// Replace the content of the container
                fts.replace(R.id.nav_host_fragment, new LeaderBoardFragment());
// Append this transaction to the backstack
                fts.addToBackStack("optional tag");
// Commit the changes
                fts.commit();*/

                Intent intent = new Intent(BeginChallenge_Activity.this, LeaderBoard_Activity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();

        String ChallengeName = getIntent().getExtras().getString("ChallengeName");
        challengeId_str = getIntent().getExtras().getString("challengeId");
        String remainingDays = getIntent().getExtras().getString("remainingDays");


        Log.e("tag","challengeId_str=="+challengeId_str);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            GetJoinChallengeById();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }


        btn_joinchallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(BeginChallenge_Activity.this, StartChallengActivity.class);
                startActivity(intent);
            }
        });


        challengeName_tv.setText(ChallengeName);
        //  distance_tv.setText("0.00 / "+Distance);
        DaysRemaining_tv.setText(remainingDays+" Days Remaining");
        // participants_tv.setText(participents+" Participants");
        events_tv.setText(ChallengeName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
        //  menu.findItem(R.id.nav_search).setVisible(false);
        //menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {

            return true;
        } /*else if (id == R.id.nav_add) {
            startActivity(new Intent(mContext, Create_ChallengeActivity.class));
            return true;
        }*/ else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void getAllView() {
        challengeName_tv = (TextView) findViewById(R.id.challengeName_tv);
        DaysRemaining_tv = (TextView) findViewById(R.id.DaysRemaining_tv);
        distance_tv = (TextView) findViewById(R.id.distance_tv);
        total_distance_tv = (TextView) findViewById(R.id.total_distance_tv);
        duration_tv = (TextView) findViewById(R.id.duration_tv);
        time_taken_tv = (TextView) findViewById(R.id.time_taken_tv);
        participants_tv = (TextView) findViewById(R.id.participants_tv);
        events_tv = (TextView) findViewById(R.id.events_tv);
        leaderBoard_ll= (LinearLayout) findViewById(R.id.leaderBoard_ll);
        btn_joinchallenge = (Button) findViewById(R.id.btn_beginchallenge);
        fullscreen_ll = (LinearLayout) findViewById(R.id.fullscreen_ll);

    }

    public void GetJoinChallengeById(){
        GetAllMeasureDistance();
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Log.e("tag","2challengeId_str=="+challengeId_str);

        Long challengeId_long= Long.valueOf(challengeId_str);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetJoinChallengeByIdGet(challengeId_long).enqueue(new Callback<List<JoinChallenegeModel>>(){
            @Override
            public void onResponse(Call<List<JoinChallenegeModel>> call, Response<List<JoinChallenegeModel>> response) {
                Log.e("GetMyChallengeModel:", response.message());
                if(response.isSuccessful())
                {


                    List<JoinChallenegeModel> getchallengelist_obj = response.body();

                    JoinChallenegeModel[] challengelist_arrayObj= new JoinChallenegeModel[getchallengelist_obj.size()];

                    challengeModelList = new ArrayList<>();
                    for(int i=0;i<getchallengelist_obj.size();i++){
                        JoinChallenegeModel inner_challengelst= new JoinChallenegeModel();
                        inner_challengelst.setChallengeName(getchallengelist_obj.get(i).getChallengeName());
                        inner_challengelst.setChallengeId(getchallengelist_obj.get(i).getChallengeId());
                        inner_challengelst.setChallengeDate(getchallengelist_obj.get(i).getChallengeDate());
                        inner_challengelst.setChallengeEndDate(getchallengelist_obj.get(i).getChallengeEndDate());
                        inner_challengelst.setChallengeEndTime(getchallengelist_obj.get(i).getChallengeEndTime());
                        inner_challengelst.setChallengeStartTime(getchallengelist_obj.get(i).getChallengeStartTime());
                        //     inner_challengelst.setChallengeStatus(getchallengelist_obj.get(i).getChallengeStatus());
                        inner_challengelst.setDistance(getchallengelist_obj.get(i).getDistance());
                        inner_challengelst.setDistanceMeasurementId(getchallengelist_obj.get(i).getDistanceMeasurementId());
                        //   inner_challengelst.setEventType(getchallengelist_obj.get(i).getEventType());
                        inner_challengelst.setRemainingDate(getchallengelist_obj.get(i).getRemainingDate());
                        //    inner_challengelst.setThemeName(getchallengelist_obj.get(i).getThemeName());
                        //    inner_challengelst.setThemesId(getchallengelist_obj.get(i).getThemesId());
                        inner_challengelst.setTotalParticipantsCount(getchallengelist_obj.get(i).getTotalParticipantsCount());
                        challengelist_arrayObj[i]=inner_challengelst;
                        challengeModelList.add(inner_challengelst);
                    }
                    // if(challengeModelList.get(0).)
              /*  //    version=new String[challengeModelList.size()];

                    remaining_days=new String[challengeModelList.size()];
                    participants=new String[challengeModelList.size()];
                    startTime=new String[challengeModelList.size()];
                    endTime=new String[challengeModelList.size()];
              //      images = new int[challengeModelList.size()];*/
                    versionNumber=new String[challengeModelList.size()];
                    challengeId = new String[challengeModelList.size()];
                    for(int j=0;j<challengeModelList.size();j++){

                        Log.e("tag","challengeModelList"+challengeModelList.get(j).getChallengeName());


                        for(int k=0;k<DistanceModelList.size();k++){
                            Log.e("tag","getChallengeId="+challengeModelList.get(j).getDistanceMeasurementId());
                            Log.e("tag","getId="+DistanceModelList.get(k).getId());

                            if(challengeModelList.get(j).getDistanceMeasurementId()==DistanceModelList.get(k).getId()){
                                Log.e("tag","getDistanceMeasurementName"+DistanceModelList.get(k).getDistanceMeasurementName());
                                Log.e("tag","getDistance"+challengeModelList.get(j).getDistance());

                                versionNumber[j]=challengeModelList.get(j).getDistance()+"  "+DistanceModelList.get(k).getDistanceMeasurementName();
                            }
                        }
                        //       version[j]=challengeModelList.get(j).getChallengeName();
                        /*remaining_days[j]= String.valueOf(challengeModelList.get(j).getRemainingDate());
                        participants[j]= String.valueOf(challengeModelList.get(j).getTotalParticipantsCount());
                        startTime[j]= String.valueOf(challengeModelList.get(j).getChallengeStartTime());
                        endTime[j]= String.valueOf(challengeModelList.get(j).getChallengeEndTime());
                        challengeId[j]=String.valueOf(challengeModelList.get(j).getChallengeId());*/
                        //     images[j]=R.drawable.challenge1;

                        String startDate=challengeModelList.get(j).getChallengeDate().toString();
                        String endDate=challengeModelList.get(j).getChallengeEndDate().toString();
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
                        challengeName_tv.setText(challengeModelList.get(j).getChallengeName().toString());
                        distance_tv.setText("0.00 / "+versionNumber[j].toString());
                        DaysRemaining_tv.setText(challengeModelList.get(j).getRemainingDate().toString() +" Days Remaining");
                        participants_tv.setText(challengeModelList.get(j).getTotalParticipantsCount().toString() +" Participants");
                        total_distance_tv.setText(versionNumber[j].toString());
                        duration_tv.setText(finalstartDate);
                   //     challengeId_tv.setText(challengeModelList.get(j).getChallengeId().toString());
                        // events_tv.setText(ChallengeName);
                    }
                  /*  Log.e("tag","version="+version);
                    Log.e("tag","versionNumber="+versionNumber.toString());
                    lAdapter = new ListAdapterMyChallenges(JoinChallenge_Activity.this, version, versionNumber, images,remaining_days,participants,startTime,endTime,challengeId);
                    listView.setAdapter(lAdapter);*/
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<JoinChallenegeModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(BeginChallenge_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void GetAllMeasureDistance(){

        Rest_Adapter.getDropDownApi().apiDropDownGetAllMeasureDistanceGet().enqueue(new Callback<List<DistanceModel>>(){
            @Override
            public void onResponse(Call<List<DistanceModel>> call, Response<List<DistanceModel>> response) {
                if(response.isSuccessful())
                {


                    List<DistanceModel> getDistancelist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;
                    Log.e("getDistancelist_obj", String.valueOf(getDistancelist_obj.get(0).getDistanceMeasurementName()));

                    DistanceModel[] DistanceModellist_arrayObj= new DistanceModel[getDistancelist_obj.size()];

                    DistanceModelList = new ArrayList<>();
                    for(int i=0;i<getDistancelist_obj.size();i++){
                        DistanceModel inner_countrylst= new DistanceModel();
                        inner_countrylst.setDistanceMeasurementName(getDistancelist_obj.get(i).getDistanceMeasurementName());
                        inner_countrylst.setId(getDistancelist_obj.get(i).getId());
                        DistanceModellist_arrayObj[i]=inner_countrylst;
                        DistanceModelList.add(inner_countrylst);
                        Log.e("getDistancelist_obj", String.valueOf(DistanceModelList.get(i).getDistanceMeasurementName()));

                    }


                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<DistanceModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(BeginChallenge_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }



}