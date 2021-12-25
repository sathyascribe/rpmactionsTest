package com.inventica.rpmapp.ui.activity.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.InviteFriendsActivity;
import com.inventica.rpmapp.ui.helper.DateTimeString;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.AddEventModel;
import org.openapitools.client.model.ModelApiResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    private Context mContext;
    private EditText editTextEventName, editTextDistance,editTextDate,editTextTime;
    private Date startDate, endDate, startTime,endTime;
    private String startTimeString, endTimeString;
    public final int LAUNCH_SECOND_ACTIVITY = 1;
    public final int LAUNCH_ADD_EVENT_ACTIVITY = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Calendar");

        Button invite = findViewById(R.id.buttonInvite);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextDate = findViewById(R.id.editTextSelectDate);
        editTextTime = findViewById(R.id.editTextSelectTime);
        editTextDistance = findViewById(R.id.editTextDistance);

        editTextDate.setOnClickListener(v -> openDatePicker());
        editTextTime.setOnClickListener(v -> openTimePicker());

        invite.setOnClickListener(v -> {

            Intent i = new Intent(this, InviteFriendsActivity.class);
            startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
        });
        mContext = this;
    }

    private void openTimePicker() {
       Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) ->
                startTimeString = ( selectedHour + ":" + selectedMinute ), hour, minute, false);
                startTime = mCurrentTime.getTime();
        openTimePicker2();
        mTimePicker.setTitle("Start Time");
        mTimePicker.show();
    }
    private void openTimePicker2() {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) ->
                endTimeString = ( selectedHour + ":" + selectedMinute ), hour, minute, false);
                endTime = mCurrentTime.getTime();
                setTime();
        mTimePicker.setTitle("End Time");
        mTimePicker.show();
    }

    private void setTime() {

        editTextTime.setText(startTimeString + " - "+ endTimeString);
    }

    private void openDatePicker() {

        new DatePickerDialog(mContext, date, selectStartDate
                .get(Calendar.YEAR), selectStartDate.get(Calendar.MONTH),
                selectStartDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    final Calendar selectStartDate = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {

        selectStartDate.set(Calendar.YEAR, year);
        selectStartDate.set(Calendar.MONTH, month);
        selectStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        startDate = selectStartDate.getTime();

        openDatePicker2();
    };



    private void openDatePicker2() {
        new DatePickerDialog(mContext, date1, selectEndDate
                .get(Calendar.YEAR), selectEndDate.get(Calendar.MONTH),
                selectEndDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    final Calendar selectEndDate = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date1 = (view, year, month, dayOfMonth) -> {

        selectEndDate.set(Calendar.YEAR, year);
        selectEndDate.set(Calendar.MONTH, month);
        selectEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        endDate = selectEndDate.getTime();
        updateLabel();
    };
        @SuppressLint("SetTextI18n")
        private void updateLabel() {

        editTextDate.setText( DateTimeString.getDateToString(selectStartDate.getTime())
                + " - "+
                DateTimeString.getDateToString(selectEndDate.getTime()));

    }

    public void addEvent() {

        try {

            AddEventModel addEventModel = new AddEventModel();
            addEventModel.distance(40.0);

            addEventModel.distanceMeasurementId((long) 1);
            addEventModel.eventEndDate(selectEndDate.getTime());
            addEventModel.eventStartDate(selectStartDate.getTime());
            addEventModel.eventEndTime(endTime);
            addEventModel.eventStartTime(startTime);
            addEventModel.eventName(editTextEventName.getText().toString());

            Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesCreateEventPost(addEventModel).enqueue(new Callback<ModelApiResponse>() {
                @Override
                public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {

                    if (response.isSuccessful() && response.body().getStatusCode()== 200) {

                        Toast.makeText(mContext, "Event Added Successfully", Toast.LENGTH_LONG).show();
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();

                    }

                  else  if (response.isSuccessful() && response.body().getStatusCode()== 400) {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                    else  if (response.isSuccessful() && response.body().getStatusCode()== 500) {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                    else{
                        Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        }  else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
               addEvent();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }
}