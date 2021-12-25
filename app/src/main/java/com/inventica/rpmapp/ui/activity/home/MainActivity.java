package com.inventica.rpmapp.ui.activity.home;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.activity.ActivityModle_Activity;
import com.inventica.rpmapp.ui.activity.ChallengeActivity;
import com.inventica.rpmapp.ui.activity.LoginHomeActivity;
import com.inventica.rpmapp.ui.activity.NotificationActivity;
import com.inventica.rpmapp.ui.activity.ProfileActivity;
import com.inventica.rpmapp.ui.activity.RoundedImageView;
import com.inventica.rpmapp.ui.activity.SettingActivity;
import com.inventica.rpmapp.ui.activity.SupportActivity;
import com.inventica.rpmapp.ui.activity.calendar.CalendarActivity;
import com.inventica.rpmapp.ui.activity.connection.ConnectionsActivity;
import com.inventica.rpmapp.ui.activity.gallery.GalleryActivity;
import com.inventica.rpmapp.ui.activity.gear.GearActivity;
import com.inventica.rpmapp.ui.application.RPMApplication;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;
import org.openapitools.client.model.UserResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private Context mContext;
    private static final String TAG = "MainActivity";
    private ImageView imageViewBack;


    @SuppressLint("RtlHardcoded")
    ProgressDialog dialog;
    RoundedImageView imageProfile;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                ,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        mContext = this;
        dialog = new ProgressDialog(mContext);
       // --------------- for drawer without nav frag ---------------------------
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(ContextCompat
                .getColor(mContext,R.color.white));
        actionBarDrawerToggle.syncState();

        navigationView=  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getUserProfile();
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =  headerView.findViewById(R.id.textProfileName);
        imageProfile = headerView.findViewById(R.id.imageProfile);
        navUsername.setText(ConnectionHelper.getName(RPMApplication.getAppContext()));
        TextView navEmailId =  headerView.findViewById(R.id.textEmail);
        navEmailId.setText(ConnectionHelper.getEmailId(RPMApplication.getAppContext()));
        imageViewBack = headerView.findViewById(R.id.imageBack);
        imageViewBack.setOnClickListener(v -> drawerLayout.closeDrawer(Gravity.LEFT));

       // RoundedImageView roundedImageView = headerView.findViewById(R.id.imageProfile);

//        Picasso.with(mContext).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBwgu1A5zgPSvfE83nurkuzNEoXs9DMNr8Ww&usqp=CAU")
//                .placeholder(R.drawable.profile_temp).into(roundedImageView);

        // ------------------ permission for map ----------------------------------
        getPermission();

    }

    private void getPermission() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "getPermission: Permission Granted");

            } else {

                checkLocationPermission();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   SendLogcatMail();
    }
    public void getUserProfile(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetUserProfileGet().enqueue(new Callback<UserResponseModel>(){
            @Override
            public void onResponse(@NotNull Call<UserResponseModel> call, @NotNull Response<UserResponseModel> response) {
                Log.e("response.body profile", String.valueOf(response.body()));

//                Log.e("getUserprofile_obj", String.valueOf(response.body().getFirstName()));
                dialog.dismiss();
                if(response.isSuccessful())
                {

                    UserResponseModel getUserprofile_obj = response.body();
                    Log.e("tag","profile photo=="+getUserprofile_obj.getProfilePhoto());
                    Picasso.with(mContext)
                            .load(getUserprofile_obj.getProfilePhoto()) // Equivalent of what ends up in onBitmapLoaded
                            .placeholder(R.drawable.profile_temp)
                            .error(R.drawable.profile_temp)
                            .centerCrop()
                            .fit()
                            .into(imageProfile);


                }
            }

            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();

            }

        });
    }
    public void SendLogcatMail(){

    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't blockactivity_main
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION );

                            Log.d(TAG, "getPermission: Permission Granted");
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }

        }
        else{
            Log.d(TAG, "getPermission: No location permission");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId() ;
        switch (id)
        {
            case R.id.nav_notification:

                startActivity(new Intent(mContext, NotificationActivity.class));

                 return true;
            case R.id.nav_setting :
                startActivity(new Intent(mContext, SettingActivity.class));
                return true;

           /* case R.id.action_editchallenge: return true;
            case R.id.action_leavechallenge : return true;*/

        }

        return super .onOptionsItemSelected(item) ;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void displayView(int position) {
        if (position == 0) {//   popupLogout();
            startActivity(new Intent(MainActivity.this, LoginHomeActivity.class));
            finish();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_myProfile :
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_activities :
                startActivity(new Intent(MainActivity.this, ActivityModle_Activity.class));
                break;
            case  R.id.nav_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gears :
                Intent intent1 = new Intent(MainActivity.this, GearActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_gallery:
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
                break;
            case R.id.nav_connection:
                startActivity(new Intent(MainActivity.this, ConnectionsActivity.class));
                break;
            case R.id.nav_challenges:
                startActivity(new Intent(MainActivity.this, ChallengeActivity.class));
                break;
            case R.id.nav_calendar: startActivity(new Intent(mContext, CalendarActivity.class));
            break;
            case R.id.nav_notification:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.nav_support:
                startActivity(new Intent(MainActivity.this, SupportActivity.class));
                break;
            case R.id.nav_logout:
                showLogoutDialog();
                break;
        }

        return false;
    }

    private void showLogoutDialog() {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_logout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView textTitle = dialog.findViewById(R.id.dialogTitle);
            TextView textDescription = dialog.findViewById(R.id.dialogDescription);
            String title = "Retire Shoe";
            textTitle.setText(title);
            String description = mContext.getString(R.string.retire);
            textDescription.setText(description);
            Button buttonCancel = dialog.findViewById(R.id.cancel_btn);
            Button buttonRetire = dialog.findViewById(R.id.buttonRetire);
            buttonCancel.setOnClickListener(v -> dialog.dismiss());
            buttonRetire.setOnClickListener(v ->  {
                ConnectionHelper.setToken(MainActivity.this, "", "");
                ConnectionHelper.setLoginDetails(MainActivity.this,"","");
                dialog.dismiss();
                finish();
            });
            dialog.show();

        }





}