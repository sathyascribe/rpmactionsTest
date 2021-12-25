package com.inventica.rpmapp.ui.activity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.ChallengeStatusModel;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.JoinChallenegeModel;
import org.openapitools.client.model.ModelApiResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class JoinChallenge_Activity extends AppCompatActivity {


    TextView challengeName_tv,DaysRemaining_tv,distance_tv,total_distance_tv,duration_tv,time_taken_tv,participants_tv,events_tv,challengeId_tv;
    LinearLayout leaderBoard_ll,fullscreen_ll;
    Button btn_joinchallenge;
    ProgressDialog dialog;
    Context mContext;
    List<DistanceModel> DistanceModelList;
    List<JoinChallenegeModel> challengeModelList;
    List<ChallengeStatusModel> ChallengeStatuslList;
    String[] versionNumber,remaining_days,startTime,endTime,participants,challengeId;
    Spinner spinner;
    String challengeId_str,inviteId_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_joinchallenges);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Join Challenge");

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
               /* fullscreen_ll.setVisibility(View.GONE);
                androidx.fragment.app.FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
// Replace the content of the container
                fts.replace(R.id.nav_host_fragment, new LeaderBoardFragment());
// Append this transaction to the backstack
                fts.addToBackStack("optional tag");
// Commit the changes
                fts.commit();*/

                Intent intent = new Intent(JoinChallenge_Activity.this, LeaderBoard_Activity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();

        String ChallengeName = getIntent().getExtras().getString("ChallengeName");
        challengeId_str = getIntent().getExtras().getString("challengeId");
        String remainingDays = getIntent().getExtras().getString("remainingDays");
     //   String participents = getIntent().getExtras().getString("participents");

      /*  String startDate =getIntent().getExtras().getString("startDate");
        String endDate = getIntent().getExtras().getString("endDate");
        Log.e("tag","startDate:"+startDate);
        Log.e("tag","endDate:"+endDate);*/

        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            GetJoinChallengeById();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }

        btn_joinchallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              //  fullscreen_ll.setVisibility(View.GONE);

                showAcceptOrRejectDialog();

/*
                Intent intent = new Intent(JoinChallenge_Activity.this, BeginChallenge_Activity.class);
                intent.putExtra("ChallengeName",ChallengeName);
                intent.putExtra("Distance", "100 Mi");
                intent.putExtra("remainingDays", remainingDays);
                *//*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*//*
                startActivity(intent);*/
            }
        });
   //     String ChallengeName = getArguments().getString("ChallengeName");
///        String Distance = getArguments().getString("Distance");
     //   String remainingDays = getArguments().getString("remainingDays");
        //  String participents = getArguments().getString("participents");

        //String startDate = getArguments().getString("startDate");
        //String endDate = getArguments().getString("endDate");
        //Log.e("tag","startDate:"+startDate);
        // Log.e("tag","endDate:"+endDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat dateDurationFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

     /*   Date objDate = null;
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
        Log.e("Date Format:", "Final Date:"+finalstartDate);*/
        // Log.e("tag","startDate d:"+d);

        /* String time = getArguments().getString("time");*/

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
        btn_joinchallenge = (Button) findViewById(R.id.btn_joinchallenge);
        fullscreen_ll = (LinearLayout) findViewById(R.id.fullscreen_ll);
        challengeId_tv = (TextView) findViewById(R.id.challengeId_tv);
    }

    void showAcceptOrRejectDialog(){
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.spinner_dialog);
        TextView title= myDialog.findViewById(R.id.dialogTitle);
        title.setText("Challenge Status");
        spinner=myDialog.findViewById(R.id.spinner_SP);

        Button submit=myDialog.findViewById(R.id.submit_btn);
        Button cancel=myDialog.findViewById(R.id.cancel_btn);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Accept");
        spinnerArray.add("Reject");
        ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, spinnerArray);
        dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("tag","Selected spinner item=="+spinner.getSelectedItem());
                if(spinner.getSelectedItem().equals("Accept")){
                    inviteId_str="2";
                    Log.e("tag","1 inviteId_str=="+inviteId_str);

                }else{
                    inviteId_str="3";
                    Log.e("tag","1 inviteId_str=="+inviteId_str);

                }
               // Obj_Class_heightDetails[0] = (HeightModel) spinner.getSelectedItem();
                //sp_height[0] = Obj_Class_heightDetails[0].getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tag","spinner.getSelectedItem().toString(): "+spinner.getSelectedItem().toString());
               // tv_height.setText(spinner.getSelectedItem().toString());
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
                    Long challengeId,inviteId;
                    Log.e("tag","inviteId_str=="+inviteId_str);
                    challengeId= Long.valueOf(challengeId_tv.getText().toString());
                    inviteId= Long.valueOf(inviteId_str);
                    Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAccessOrRejectChallengeGet(challengeId,inviteId).enqueue(new Callback<ModelApiResponse>() {
                        @Override
                        public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                            Log.e("tag", "response==" + response.body().getMessage());

                            dialog.dismiss();
                            //  if(response.code()==200){
                            if (response.body().getStatusCode()==200) {

                                Toast.makeText(JoinChallenge_Activity.this, "Joined Challenge Successfully.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(JoinChallenge_Activity.this, BeginChallenge_Activity.class);
                                intent.putExtra("ChallengeName","ChallengeName");
                                intent.putExtra("challengeId", challengeId_tv.getText().toString());
                                intent.putExtra("remainingDays", "remainingDays");
                /*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*/
                                startActivity(intent);
                            } else {
                                Toast.makeText(JoinChallenge_Activity.this, response.message(), Toast.LENGTH_LONG).show();
                            }
                            //}
                            //  } //else
                        /*if (response.code() == 409)
                            Toast.makeText(getApplicationContext(), "Email Id is already registered", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try later", Toast.LENGTH_LONG).show();*/
                        }

                        @Override
                        public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                            println("register error ${t.message}");
                            dialog.dismiss();
                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
                myDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.cancel();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void GetJoinChallengeById(){
        GetAllMeasureDistance();
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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
                        challengeId_tv.setText(challengeModelList.get(j).getChallengeId().toString());
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
                Toast.makeText(JoinChallenge_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(JoinChallenge_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }


}