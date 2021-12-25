package com.inventica.rpmapp.ui.activity.gear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.GetUserGearModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GearActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyGears;
    private RecyclerView.Adapter adapterMyGears;
    private RecyclerView recyclerViewMyGearsRetired;
    private RecyclerView.Adapter adapterMyGearsRetired;

    private ScrollView scrollView;
    private ProgressBar progressBar;

    ArrayList<GetUserGearModel> arrayList = new ArrayList<>();
    ArrayList<GetUserGearModel> arrayListRetired = new ArrayList<>();

    private TextView textViewNoActiveGear, textViewNoRetiredGear;
    private Context mContext;
    private static final String TAG = "GearActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Gears");

        mContext = this;
        getAllView();
        getGearList();
    }

    private void getAllView() {
        textViewNoActiveGear = findViewById(R.id.text_noActiveGear);
        textViewNoRetiredGear = findViewById(R.id.text_noRetiredGear);

        scrollView = findViewById(R.id.scrollView2);
        progressBar = findViewById(R.id.progress_bar);

        recyclerViewMyGears = findViewById(R.id.recyclerView_myGear);
        adapterMyGears = new ActiveGearAdaper(mContext, arrayList);
        RecyclerView.LayoutManager layoutManagerMyGears = new LinearLayoutManager(mContext);
        recyclerViewMyGears.setAdapter(adapterMyGears);
        recyclerViewMyGears.setLayoutManager(layoutManagerMyGears);
        recyclerViewMyGears.setHasFixedSize(true);
        recyclerViewMyGears.setNestedScrollingEnabled(false);

        recyclerViewMyGearsRetired = findViewById(R.id.recyclerView_myGear_retired);
        adapterMyGearsRetired = new RetiredGearAdaptor(mContext, arrayListRetired);
        RecyclerView.LayoutManager layoutManagerMyGearsRetired = new LinearLayoutManager(mContext);
        recyclerViewMyGearsRetired.setAdapter(adapterMyGearsRetired);
        recyclerViewMyGearsRetired.setLayoutManager(layoutManagerMyGearsRetired);
        recyclerViewMyGearsRetired.setHasFixedSize(true);
        recyclerViewMyGearsRetired.setNestedScrollingEnabled(false);

    }

    public void getGearList() {

        scrollView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetMyCurrentGearlistGet().enqueue(new Callback<List<GetUserGearModel>>() {
            @Override
            public void onResponse(Call<List<GetUserGearModel>> call, Response<List<GetUserGearModel>> response) {
                   progressBar.setVisibility(View.GONE);
                   scrollView.setVisibility(View.VISIBLE);


                if (response.isSuccessful() && response.code() == 200) {
                    arrayList.clear();
                    arrayListRetired.clear();
                    for (GetUserGearModel item : response.body()) {

                        Log.d(TAG, "onResponse: " + item.getBrandId());
                        if (item.getIsRetired()) {arrayListRetired.add(item);
                        } else {

                            item.setIsSelected(false);
                            arrayList.add(item);
                        }
                    }
                    setVisibility();
                    adapterMyGears.notifyDataSetChanged();
                    adapterMyGearsRetired.notifyDataSetChanged();
                } else if (response.isSuccessful() && response.code() == 500) {
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<GetUserGearModel>> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
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
//        menu.findItem(R.id.nav_add).setVisible(false);

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

    private void setVisibility() {
        if (arrayList.size() > 0) {
            recyclerViewMyGears.setVisibility(View.VISIBLE);
            textViewNoActiveGear.setVisibility(View.GONE);
        } else {
            recyclerViewMyGears.setVisibility(View.GONE);
            textViewNoActiveGear.setVisibility(View.VISIBLE);
        }
        if (arrayListRetired.size() > 0) {
            recyclerViewMyGearsRetired.setVisibility(View.VISIBLE);
            textViewNoRetiredGear.setVisibility(View.GONE);
        } else {
            recyclerViewMyGearsRetired.setVisibility(View.GONE);
            textViewNoRetiredGear.setVisibility(View.VISIBLE);
        }
    }
}