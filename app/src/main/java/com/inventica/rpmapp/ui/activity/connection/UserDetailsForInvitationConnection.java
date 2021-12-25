package com.inventica.rpmapp.ui.activity.connection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.gear.AddGear;
import com.inventica.rpmapp.ui.activity.RoundedImageView;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.squareup.picasso.Picasso;

import org.openapitools.client.model.CreateRequestModel;
import org.openapitools.client.model.StringApiResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsForInvitationConnection extends AppCompatActivity {


private Context mContext;
private ViewPager viewPager;
private TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    MyListUserModel myListUserModel;
    private ProgressDialog loadingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_for_invitation_connection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Connections");
        mContext = this;

        myListUserModel = getIntent().getParcelableExtra("Object");
        loadingProgressBar = new ProgressDialog(mContext);
        TextView textView = findViewById(R.id.userName);
        RoundedImageView roundedImageView = (RoundedImageView) findViewById(R.id.imageProfile);
        Button button = findViewById(R.id.buttonInvite);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendConnectionRequest(myListUserModel.getInviteUserId());

            }
        });

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), myListUserModel);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

                Picasso.with(mContext)
                .load(myListUserModel.getInviteUserProfilePhoto()) // Equivalent of what ends up in onBitmapLoaded
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .centerCrop()
                .fit()
                .into(roundedImageView);
                textView.setText(myListUserModel.getInviteUserName());
                tabLayout.getTabAt(0).view.setBackgroundColor
                        (getColor(R.color.purple_700));
        tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor
                        (getColor(R.color.purple_700));
                tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);
                tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
                tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor
                        (getColor(R.color.white));
                tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
                tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
                tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void sendConnectionRequest(Long inviteUserId) {

        loadingProgressBar.setMessage("Adding Shoe ...");
        loadingProgressBar.show();

        CreateRequestModel createRequestModel = new CreateRequestModel();
        createRequestModel.setRequestToUserId(inviteUserId);


        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesCreateConnectionRequestPost(createRequestModel).enqueue(new Callback<StringApiResponse>() {
            @Override
            public void onResponse(Call<StringApiResponse> call, Response<StringApiResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {

                 Toast.makeText(mContext, "Request Sent Successfully",Toast.LENGTH_LONG).show();

                } else if (response.isSuccessful() && response.code() == 500) {
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StringApiResponse> call, Throwable t) {

            }
        });

                loadingProgressBar.dismiss();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);
        // menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
        //        menu.findItem(R.id.nav_notification).setVisible(false);
       menu.findItem(R.id.nav_add).setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {
            return true;
        } else if (id == R.id.nav_add) {
            startActivity(new Intent(mContext, AddGear.class));
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}