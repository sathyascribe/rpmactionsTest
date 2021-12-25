package com.inventica.rpmapp.ui.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.StatusModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class ChallengeDetails_Activity extends AppCompatActivity {


    TextView challengeName_tv,DaysRemaining_tv,distance_tv,total_distance_tv,duration_tv,time_taken_tv,participants_tv,events_tv;
    LinearLayout leaderBoard_ll,fullscreen_ll;

    String ChallengeName,Distance,startDate,endDate,remainingDays,participents,challengeId;
    NavigationView navigationView;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_challengesdetails);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Challenge");

        mContext = this;
        getAllView();

        leaderBoard_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              /*  Fragment  fragment2 = new LeaderBoardFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            /*    fullscreen_ll.setVisibility(View.GONE);
                androidx.fragment.app.FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
// Replace the content of the container
                fts.replace(R.id.nav_host_fragment, new LeaderBoardFragment());
// Append this transaction to the backstack
                fts.addToBackStack("optional tag");
// Commit the changes
                fts.commit();*/
                Intent intent = new Intent(ChallengeDetails_Activity.this, LeaderBoard_Activity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();

         ChallengeName = getIntent().getExtras().getString("ChallengeName");
         Distance = getIntent().getExtras().getString("Distance");
         remainingDays = getIntent().getExtras().getString("remainingDays");
         participents = getIntent().getExtras().getString("participents");

         startDate =getIntent().getExtras().getString("startDate");
         endDate = getIntent().getExtras().getString("endDate");
        challengeId =  getIntent().getExtras().getString("challengeId");
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

      //  menu.findItem(R.id.nav_setting).setVisible(false);
        //  menu.findItem(R.id.nav_search).setVisible(false);
        //menu.findItem(R.id.nav_notification).setVisible(false);
        //menu.findItem(R.id.nav_add).setVisible(false);

        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {

            return true;
        } *//*else if (id == R.id.nav_add) {
            startActivity(new Intent(mContext, Create_ChallengeActivity.class));
            return true;
        }*//* else if (id == android.R.id.home) {
            onBackPressed();
            return true;

            case R.id.action_editchallenge: return true;
            case R.id.action_leavechallenge : return true;
        }


        return super.onOptionsItemSelected(item);
    }
*/
   @Override
   public boolean onOptionsItemSelected (MenuItem item) {
       int id = item.getItemId() ;
       switch (id)
       {
           /*case R.id.nav_notification:

               startActivity(new Intent(mContext,NotificationActivity.class));

               return true;
           case R.id.nav_setting :
               startActivity(new Intent(mContext,SettingActivity.class));
               return true;*/
           case android.R.id.home :
               onBackPressed();
               return true;
           case R.id.action_editchallenge:
               //startActivity(new Intent(mContext, Edit_ChallengeActivity.class));
               Intent intent = new Intent(mContext, Edit_ChallengeActivity.class);
               intent.putExtra("ChallengeName",ChallengeName);
               intent.putExtra("Distance", Distance);
               //intent.putExtra("remainingDays", remainingDays);
              // intent.putExtra("participents", participents);
               intent.putExtra("time",time_taken_tv.getText().toString());
               intent.putExtra("startDate",startDate);
               intent.putExtra("endDate", endDate);
               mContext.startActivity(intent);
               return true;
            case R.id.action_leavechallenge :
                //showCustomLogoutDialog(navigationView);
                showWarningAlert(ChallengeDetails_Activity.this);
                return true;
           case R.id.action_invitechallenge :
               //showCustomLogoutDialog(navigationView);
               Intent intent1 = new Intent(mContext, Challenge_InviteFriends_Activity.class);
               intent1.putExtra("challengeId",challengeId);
               mContext.startActivity(intent1);
               return true;

       }

       return super .onOptionsItemSelected(item) ;
   }
    private void showWarningAlert(Context context) { //Added argument
        AlertDialog alertDialog = new AlertDialog.Builder(context).create(); //Use context
        alertDialog.setTitle("Leave Challenge");
        alertDialog.setMessage("Are you sure you want to Leave Challenge?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        StatusModel statusModel=new StatusModel();
                        statusModel.challengeId(Integer.valueOf(challengeId));
                        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesLeaveChallengePost(statusModel).enqueue(new Callback<ModelApiResponse>() {
                            @Override
                            public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                                Log.e("tag", "response==" + response.body().getMessage());

                                //  if(response.code()==200){
                                if (response.body().getStatusCode()==200) {
                                    // if (response.message().equalsIgnoreCase("Success")) {

                                    Toast.makeText(ChallengeDetails_Activity.this, "Successfully Updated.", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ChallengeDetails_Activity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
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
                            }
                        });

                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    private void getAllView() {
        challengeName_tv = (TextView) findViewById(R.id.challengeName_tv);
        DaysRemaining_tv = (TextView) findViewById(R.id.DaysRemaining_tv);
        distance_tv = (TextView)findViewById(R.id.distance_tv);
        total_distance_tv = (TextView) findViewById(R.id.total_distance_tv);
        duration_tv = (TextView) findViewById(R.id.duration_tv);
        time_taken_tv = (TextView) findViewById(R.id.time_taken_tv);
        participants_tv = (TextView) findViewById(R.id.participants_tv);
        events_tv = (TextView) findViewById(R.id.events_tv);
        leaderBoard_ll= (LinearLayout) findViewById(R.id.leaderBoard_ll);
        fullscreen_ll=(LinearLayout) findViewById(R.id.fullscreen_ll);


    }

    void showCustomLogoutDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(ChallengeDetails_Activity.this).inflate(R.layout.logout_dialog,viewGroup, false);

        Button submit=dialogView.findViewById(R.id.logout_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        TextView title = dialogView.findViewById(R.id.dialogTitle);
        TextView text = dialogView.findViewById(R.id.text);

        title.setText("Leave Challenge");
        text.setText("Are you sure you want to Leave Challenge?");
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ChallengeDetails_Activity.this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionHelper.setToken(ChallengeDetails_Activity.this, "", "");
                ConnectionHelper.setLoginDetails(ChallengeDetails_Activity.this,"","");

                alertDialog.cancel();
                finish();
            }
        });
        cancel.setOnClickListener(v -> alertDialog.cancel());

        alertDialog.show();
    }



}