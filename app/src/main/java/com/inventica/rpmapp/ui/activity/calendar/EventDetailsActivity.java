package com.inventica.rpmapp.ui.activity.calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.helper.DateTimeString;
import com.inventica.rpmapp.ui.helper.MyHelper;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.AddEventModel;
import org.openapitools.client.model.GetEventdetailsModel;
import org.openapitools.client.model.GetUserGearModel;
import org.openapitools.client.model.GetVideoDetailsModel;
import org.openapitools.client.model.ModelApiResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity {

    private Context mContext;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Event Details");

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        GetEventdetailsModel model =
                (GetEventdetailsModel) bundle.getSerializable("Object");

        String distanceUnit = MyHelper.getDistanceUnit(model.getDistanceMeasurementId());
        String date = DateTimeString.getDateToString(model.getEventStartDate()) + " - " + DateTimeString.getDateToString(model.getEventEndDate());
        String time = DateTimeString.getTimeToString(model.getEventStartTime()) + " - " + DateTimeString.getTimeToString(model.getEventEndTime());

        TextView textViewName = findViewById(R.id.editTextEventName);
        TextView textViewDistance = findViewById(R.id.editTextDistance);
        TextView textViewDate = findViewById(R.id.editTextSelectDate);
        TextView textViewTime = findViewById(R.id.editTextSelectTime);

        textViewName.setText(model.getEventName());
        textViewDistance.setText(model.getDistance().toString() + " " + distanceUnit);
        textViewDate.setText(date);
        textViewTime.setText(time);

        mContext = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);
        menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
        //menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {

            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}