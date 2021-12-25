package com.inventica.rpmapp.ui.activity.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.CalendarView;
import android.widget.ProgressBar;



import com.applandeo.materialcalendarview.EventDay;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.helper.DateTimeString;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.GetEventdetailsModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends AppCompatActivity {

    private Context mContext;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GetEventdetailsModel> dataList1 = new ArrayList<>();


    private ArrayList<String> dataList = new ArrayList<>();
    private CalendarView calendarView;

    Date calendarDate;
    int calendarMonth;
    int calendarYear;

    public final int LAUNCH_ADD_EVENT_ACTIVITY = 30;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Calendar");
        
        mContext = this;
        dataList.add("s1");
        dataList.add("s2");
        dataList.add("s3");
        dataList.add("s4");
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new OverlapDecoration());
        progressBar = findViewById(R.id.progress_bar);
        adapter = new EventAdapter(mContext, dataList1, dataList);
        layoutManager = new LinearLayoutManager(mContext);


        calendarDate = Calendar.getInstance().getTime();

        Log.i("CalendarDate", "onCreate: " + calendarDate);

        getData();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getData() {

        progressBar.setVisibility(View.VISIBLE);
        calendarView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);


        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetEventDetailsGet(null).enqueue(new Callback<List<GetEventdetailsModel>>() {
            @Override
            public void onResponse(Call<List<GetEventdetailsModel>> call, Response<List<GetEventdetailsModel>> response) {
                Log.i("GetEvent", "onResponse: " + response.message());
                if(response.code()==200 && response.isSuccessful())
                {
                    dataList1.addAll(response.body());

                    setCalendarDate(dataList1);

                }
                else if(response.isSuccessful() && response.code() == 400)
                {

                }
                else if(response.isSuccessful() && response.code() == 500)
                {

                }
                else {

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GetEventdetailsModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        progressBar.setVisibility(View.GONE);
        calendarView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        adapter.notifyDataSetChanged();
    }

    private void setCalendarDate(ArrayList<GetEventdetailsModel> dataList1) {

        

    }

    private void getData(int year, int month) {

        progressBar.setVisibility(View.VISIBLE);
        calendarView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        Date eventStartDate =  new Date();
        eventStartDate.setYear(year);
        eventStartDate.setMonth(month);
        eventStartDate.setDate(1);

        Log.i("CalendarDate", "on Call : year :" + year + " month : " + month );

        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetEventDetailsGet(eventStartDate).enqueue(new Callback<List<GetEventdetailsModel>>() {
            @Override
            public void onResponse(Call<List<GetEventdetailsModel>> call, Response<List<GetEventdetailsModel>> response) {
                Log.i("GetEvent", "onResponse: " + response.message());
                if(response.code()==200 && response.isSuccessful())
                {
                  dataList1.addAll(response.body());

                }
                else if(response.isSuccessful() && response.code() == 400)
                {

                }
                else if(response.isSuccessful() && response.code() == 500)
                {

                }
                else {

                }

                try {
                    dataList1.add(new GetEventdetailsModel().distance(26.4)
                            .eventName("Bangalore")
                            .eventEndDate(DateTimeString.getStringToDate("2021-11-24"))
                            .eventStartDate(DateTimeString.getStringToDate("2021-11-23"))
                            .eventEndTime(DateTimeString.getStringToTime("5:30 pm"))
                            .eventStartTime(DateTimeString.getStringToTime("2:30 pm"))
                            .distanceMeasurementId((long)1)
                            .eventId(((long )1))
                    );
                    adapter.notifyDataSetChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<GetEventdetailsModel>> call, Throwable t) {
t.printStackTrace();
            }
        });



        progressBar.setVisibility(View.GONE);
        calendarView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);
        menu.findItem(R.id.nav_setting).setVisible(false);
         menu.findItem(R.id.nav_search).setVisible(false);
        //menu.findItem(R.id.nav_notification).setVisible(false);
     //   menu.findItem(R.id.nav_add).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {
            return true;
        } else if (id == R.id.nav_add) {
            startActivityForResult(new Intent(mContext, AddEventActivity.class), LAUNCH_ADD_EVENT_ACTIVITY);
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

     class OverlapDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent,
                                   RecyclerView.State state) {
            final int itemPosition = parent.getChildAdapterPosition(view);

            outRect.set(0, 0, 0, -60);//<-- bottom
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_ADD_EVENT_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }



}