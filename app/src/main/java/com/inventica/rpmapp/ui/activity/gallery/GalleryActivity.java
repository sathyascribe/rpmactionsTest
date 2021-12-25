package com.inventica.rpmapp.ui.activity.gallery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
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
import com.inventica.rpmapp.ui.activity.gear.AddGear;

import com.inventica.rpmapp.ui.modles.MyVideoModel;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.GetVideoCateoryModel;
import org.openapitools.client.model.GetVideoCateoryModelListApiResponse;
import org.openapitools.client.model.GetVideoDetailsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    private Context mContext;

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapterSlider;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.Adapter adapter3;

    private ScrollView scrollView;

    private TextView textView2;
    private TextView textView3;

    private ArrayList<MyVideoModel> myVideoModelList = new ArrayList<>();

    private ProgressBar progressBar;

    private ArrayList<GetVideoDetailsModel> preWorkOuts = new ArrayList<>();
    private ArrayList<GetVideoDetailsModel> postWorkOuts = new ArrayList<>();
    private ArrayList<GetVideoDetailsModel> coachForBeginners = new ArrayList<>();
    private ArrayList<GetVideoDetailsModel> coachForIntermediaries = new ArrayList<>();
    private ArrayList<GetVideoDetailsModel> coachForProfessionals = new ArrayList<>();
    private ArrayList<GetVideoCateoryModel> videoCategoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN );

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Videos Gallery");
        mContext = this;
        getAllViewAndSet();
        getVideoCategory();


    }

    private void getAllViewAndSet() {
        adapter = new GalleryAdaptor(mContext, myVideoModelList);
        adapterSlider = new VideoCategoryAdaptor(mContext, videoCategoryList);
        adapter2 = new VideoCategory2(mContext, coachForIntermediaries);
        adapter3 = new VideoCardAdaptor(mContext, coachForProfessionals);

        textView2 = findViewById(R.id.textTitle2);
        textView3 = findViewById(R.id.textTitle3);

        progressBar = findViewById(R.id.progressBar);

        scrollView = findViewById(R.id.scrollView);

        RecyclerView recyclerViewSlider = findViewById(R.id.recyclerViewSlider);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);

        recyclerViewSlider.setAdapter(adapterSlider);
        recyclerViewSlider.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

        recyclerView3.setAdapter(adapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

    }

    private void getVideoCategory() {

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        try {

            Rest_Adapter.getDropDownApi().apiDropDownGetAllVideoCategoriesGet().enqueue(new Callback<GetVideoCateoryModelListApiResponse>() {
                @Override
                public void onResponse(Call<GetVideoCateoryModelListApiResponse> call, Response<GetVideoCateoryModelListApiResponse> response) {

                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    if (response.isSuccessful() && response.code() == 200) {

                        Log.d("VideoCategoryList", "onResponse: " + response.body());

                        videoCategoryList.clear();

                        for (GetVideoCateoryModel item : response.body().getData()) {

                            videoCategoryList.add(item);

                        }
                        adapterSlider.notifyDataSetChanged();
                        textView2.setText(videoCategoryList.get(3).getVideoType());
                        textView3.setText(videoCategoryList.get(4).getVideoType());

                        getVideo();


                    } else if (response.isSuccessful() && response.code() == 500) {
                        Toast.makeText(mContext, "Internal Server Error", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<GetVideoCateoryModelListApiResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }

            });

        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }

    private void getVideo() {

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        try {

            Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetAllVideoDetailsGet().enqueue(new Callback<List<GetVideoDetailsModel>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<GetVideoDetailsModel>> call, Response<List<GetVideoDetailsModel>> response) {
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    if (response.isSuccessful() && response.code() == 200) {

                        Log.d("VideoList", "onResponse: " + response.body());

                        myVideoModelList.clear();

                        for (GetVideoDetailsModel item : response.body()) {

                            int categoryId = Math.toIntExact(item.getCategoryId());

                            switch (categoryId) {
                                case 0:
                                    preWorkOuts.add(item);
                                    break;
                                case 1:
                                    preWorkOuts.add(item);
                                    preWorkOuts.add(item);
                                    postWorkOuts.add(item);
                                    postWorkOuts.add(item);
                                    coachForIntermediaries.add(item);
                                    coachForProfessionals.add(item);
                                    coachForProfessionals.add(item);
                                    coachForBeginners.add(item);
                                    coachForBeginners.add(item);
                                    break;
                                case 2:

                                    coachForIntermediaries.add(item);

                                    break;
                                case 3:
                                    coachForIntermediaries.add(item);
                                    break;
                                case 4:
                                    coachForProfessionals.add(item);
                                    break;

                            }
                        }

                        myVideoModelList.add(new MyVideoModel(videoCategoryList.get(0).getVideoType(), preWorkOuts));
                        myVideoModelList.add(new MyVideoModel(videoCategoryList.get(1).getVideoType(), postWorkOuts));
                        myVideoModelList.add(new MyVideoModel(videoCategoryList.get(2).getVideoType(), coachForBeginners));

                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                        adapter3.notifyDataSetChanged();

                    } else if (response.isSuccessful() && response.code() == 500) {
                        Toast.makeText(mContext, "Internal Server Error", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<List<GetVideoDetailsModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                }
            });

        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }

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