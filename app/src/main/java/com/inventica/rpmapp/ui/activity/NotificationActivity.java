package com.inventica.rpmapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;

import com.inventica.rpmapp.ui.activity.gear.AddGear;
import com.inventica.rpmapp.ui.adapter.NotificationAdapter;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.GetNotificationRequests;
import org.openapitools.client.model.GetNotificationRequestsListApiResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {


    ArrayList<GetNotificationRequests> dataList = new ArrayList<>();

   private RecyclerView recyclerView;
   private RecyclerView.Adapter adapter;
   private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notifications");
        mContext = NotificationActivity.this;

        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new NotificationAdapter(mContext,dataList);
        layoutManager = new LinearLayoutManager(mContext);

         getDate();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void getDate() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetNotificationListForConnectionsGet().enqueue(new Callback<GetNotificationRequestsListApiResponse>() {
            @Override
            public void onResponse(Call<GetNotificationRequestsListApiResponse> call, Response<GetNotificationRequestsListApiResponse> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                Log.e("tag","response=="+response.message());
                if (response.isSuccessful() && response.code() == 200) {
                    dataList.clear();
                    assert response.body().getData() != null;
                    dataList.addAll(response.body().getData());
                    Log.e("tag","response=="+response.message());
                    adapter.notifyDataSetChanged();

                } else if (response.isSuccessful() && response.code() == 500) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Log.e("tag","response=="+response.message());
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Log.e("tag","response=="+response.message());
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<GetNotificationRequestsListApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
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