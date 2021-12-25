package com.inventica.rpmapp.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.fragment.Create_ChallengesFragment;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.AddChallengeModel;
import org.openapitools.client.model.ModelApiResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class Create_ChallengeActivity extends AppCompatActivity {

    private Create_ChallengesFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};

    LinearLayout ll_date,ll_end_date,ll_name,ll_distance,ll_time,ll_choose_theme;
    private int mYear, mMonth, mDay;
    private int cYear, cMonth, cDay;
    TextView date_tv,end_date_tv,theme_tv;
    TextView challengeName_tv,time_tv,tv_distance;
    private NumberPicker distance_picker,label_picker;
    private String[] pickerVals,distanceVals;
    TimePicker timePicker;
    Button btn_challenge;
    RadioGroup challengeType_radiogroup;
    RadioButton rdb_walking,rdb_running;
    Boolean walking_bool=true, challengeType;
    String startTime,EndTime,str_distance;
    Context mContext;
    LinearLayout fullscreen_ll,fragment_ll;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_createchallenges);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Challenges");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        fullscreen_ll.setVisibility(View.VISIBLE);
        fragment_ll.setVisibility(View.GONE);
        ll_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomNameDialogNew(v);
            }
        });
        ll_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDistanceDialog(v);
            }
        });
        ll_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimeDialog(v);
            }
        });
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_ChallengeActivity.this, R.style.DatePickerTheme,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            try {

                                Date selectedDate = dateFormat.parse(date);
                                date_tv.setText(dateFormat.format(selectedDate));

                            } catch (Exception e) {

                                Log.d("Exception : ", e.toString());
                            }

                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });
        ll_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_ChallengeActivity.this, R.style.DatePickerTheme,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            try {

                                Date selectedDate = dateFormat.parse(date);
                                end_date_tv.setText(dateFormat.format(selectedDate));

                            } catch (Exception e) {

                                Log.d("Exception : ", e.toString());
                            }

                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        ll_choose_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Fragment fragment2 = new ThemesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
           /*     fullscreen_ll.setVisibility(View.GONE);
                fragment_ll.setVisibility(View.VISIBLE);
                androidx.fragment.app.FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
// Replace the content of the container
                fts.replace(R.id.fragment, new ThemesFragment());
// Append this transaction to the backstack
                fts.addToBackStack("optional tag");
// Commit the changes
                fts.commit();*/
                Intent intent = new Intent(Create_ChallengeActivity.this, ThemesActivity.class);
                startActivity(intent);
                theme_tv.setText("Blue");
            }
        });

        challengeType_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.rdb_walking:
                        challengeType=true;
                        // switch to fragment 1
                        break;
                    case R.id.rdb_running:
                        challengeType=false;
                        // Fragment 2
                        break;
                }
            }
        });

        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
                String startDate=date_tv.getText().toString();
                String endDate=end_date_tv.getText().toString();
                String str_startTime=endDate+" "+startTime;
                String str_endTime=endDate+" "+EndTime;
                Log.e("tag","str_startTime="+str_startTime);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                Date d = null;
                Date start_time = null;
                Date end_time = null;
                try {
                    d = sdf.parse(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    start_time = sdf.parse(str_startTime);
                    Log.e("tag","startTime="+start_time);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    end_time = sdf.parse(str_endTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                AddChallengeModel addChallengeModel=new AddChallengeModel();
                addChallengeModel.setThemesId(Long.valueOf(1));
                addChallengeModel.setChallengeDate(d);
                addChallengeModel.setChallengeName(challengeName_tv.getText().toString());
                addChallengeModel.setChallengeStartTime(start_time);
                addChallengeModel.setChallengeEndTime(end_time);
                addChallengeModel.setDistance(Double.valueOf(str_distance));
                addChallengeModel.setDistanceMeasurementId(Long.valueOf(1));
                addChallengeModel.setIsWalking(challengeType);
//                addChallengeModel.setEventType(true);
                    dialog.setMessage("Loading..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesCreateChallengePost(addChallengeModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        dialog.dismiss();
                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                           /* Complited_challengeFragment ldf = new Complited_challengeFragment ();
                            Bundle args = new Bundle();
                            args.putString("ChallengeName",addChallengeModel.getChallengeName());
                            args.putString("Distance", String.valueOf(tv_distance.getText().toString()));
                            args.putString("startDate", startDate);
                            args.putString("endDate", endDate);
                            args.putString("time", time_tv.getText().toString());
                            ldf.setArguments(args);
                            getFragmentManager().beginTransaction().add(R.id.nav_host_fragment, ldf).commit();*/

                            Intent intent = new Intent(Create_ChallengeActivity.this, CreateChallengeComplitedActivity.class);
                            intent.putExtra("ChallengeName",addChallengeModel.getChallengeName());
                            intent.putExtra("Distance", String.valueOf(tv_distance.getText().toString()));
                            intent.putExtra("startDate", startDate);
                            intent.putExtra("endDate", endDate);
                            intent.putExtra("time", time_tv.getText().toString());
                            startActivity(intent);
                            //startActivity(new Intent(getActivity(), Complited_challengeFragment.class));
                            Toast.makeText(Create_ChallengeActivity.this, "Challenge Created is successfull.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Create_ChallengeActivity.this, response.message(), Toast.LENGTH_LONG).show();
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
            }
        });

    }

    private void getAllView() {
        ll_date = (LinearLayout) findViewById(R.id.ll_date);
        ll_end_date = (LinearLayout) findViewById(R.id.ll_end_date);

        ll_name = (LinearLayout) findViewById(R.id.ll_name);
        ll_distance = (LinearLayout) findViewById(R.id.ll_distance);
        ll_choose_theme = (LinearLayout) findViewById(R.id.ll_choose_theme);
        ll_time= (LinearLayout) findViewById(R.id.ll_time);
        date_tv = (TextView) findViewById(R.id.date_tv);
        end_date_tv = (TextView) findViewById(R.id.end_date_tv);
        time_tv =(TextView) findViewById(R.id.time_tv);
        challengeName_tv = (TextView) findViewById(R.id.challengeName_tv);
        tv_distance = (TextView) findViewById(R.id.tv_distance);
        btn_challenge = (Button) findViewById(R.id.btn_challenge);
        challengeType_radiogroup =(RadioGroup) findViewById(R.id.challengeType_radiogroup);
        rdb_walking=(RadioButton) findViewById(R.id.rdb_walking);
        rdb_running=(RadioButton) findViewById(R.id.rdb_running);
        theme_tv=(TextView) findViewById(R.id.theme_tv);
        fullscreen_ll = (LinearLayout) findViewById(R.id.fullscreen_ll);
        fragment_ll = (LinearLayout)findViewById(R.id.fragment_ll);
    }


    void showCustomNameDialogNew(View root){
        ViewGroup viewGroup = root.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(Create_ChallengeActivity.this).inflate(R.layout.my_dialog, viewGroup, false);
        EditText challengeName= dialogView.findViewById(R.id.challengeName);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        dialogView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AlertDialog.Builder builder = new AlertDialog.Builder(Create_ChallengeActivity.this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tag","name: "+challengeName.getText().toString());
                challengeName_tv.setText(challengeName.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        alertDialog.show();

    }
    void showCustomNameDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.my_dialog,viewGroup, false);


        EditText challengeName= dialogView.findViewById(R.id.challengeName);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tag","name: "+challengeName.getText().toString());
                challengeName_tv.setText(challengeName.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

    void showCustomDistanceDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
      //  View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.number_picker,viewGroup, false);
        View dialogView = LayoutInflater.from(Create_ChallengeActivity.this).inflate(R.layout.number_picker, viewGroup, false);

        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        distance_picker = dialogView.findViewById(R.id.distance_picker_picker);

        distance_picker.setMaxValue(4);
        distance_picker.setMinValue(0);
        distanceVals = new String[] {"100", "200", "300", "400", "500"};
        final int[] valuePicker1 = new int[1];
        distance_picker.setDisplayedValues(distanceVals);

        distance_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                valuePicker1[0] = distance_picker.getValue();
                Log.d("distance_picker value", distanceVals[valuePicker1[0]]);
            }
        });
        label_picker = dialogView.findViewById(R.id.distanceLabel_picker);
        pickerVals = new String[] {"Mi", "", "", "", ""};

        label_picker.setMaxValue(4);
        label_picker.setMinValue(0);
        label_picker.setDisplayedValues(pickerVals);
        final int[] valuePicker = new int[1];
        label_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                valuePicker[0] = label_picker.getValue();
                Log.d("picker value", pickerVals[valuePicker[0]]);
            }
        });

        //Now we need an AlertDialog.Builder object
      /*  AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();*/
        dialogView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AlertDialog.Builder builder = new AlertDialog.Builder(Create_ChallengeActivity.this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Log.e("tag","name: "+challengeName.getText().toString());
                tv_distance.setText(distanceVals[valuePicker1[0]]+" "+pickerVals[valuePicker[0]]);
                str_distance=distanceVals[valuePicker1[0]];
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

    void showCustomTimeDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(Create_ChallengeActivity.this).inflate(R.layout.time_picker,viewGroup, false);

        TextView textView1=dialogView.findViewById(R.id.dialogTitle);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);
        textView1.setText("Start time");
        TextView textView_time = (TextView) dialogView.findViewById(R.id.textView_time);
        String time;
        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                textView_time.setText(hourOfDay + ":" + minute);
            }
        });

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_ChallengeActivity.this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCustomEndTimeDialog(root);
                Log.e("tag","textView_time: "+textView_time.getText().toString());
                startTime=textView_time.getText().toString();
                //  time_tv.setText(textView_time.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }
    void showCustomEndTimeDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(Create_ChallengeActivity.this).inflate(R.layout.time_picker,viewGroup, false);

        TextView textView1=dialogView.findViewById(R.id.dialogTitle);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);
        textView1.setText("End time");
        TextView textView_time = (TextView) dialogView.findViewById(R.id.textView_time);
        String time;
        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                textView_time.setText(hourOfDay + ":" + minute);
            }
        });

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_ChallengeActivity.this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EndTime=textView_time.getText().toString();
                Log.e("tag","textView_time end: "+textView_time.getText().toString());
                time_tv.setText(startTime+" - "+textView_time.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
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
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}