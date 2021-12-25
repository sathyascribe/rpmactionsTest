package com.inventica.rpmapp.ui.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.AddChallengeModel;
import org.openapitools.client.model.ModelApiResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class Create_ChallengesFragment extends Fragment {

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_createchallenges, container, false);
        ll_date = (LinearLayout) root.findViewById(R.id.ll_date);
        ll_end_date = (LinearLayout) root.findViewById(R.id.ll_end_date);

        ll_name = (LinearLayout) root.findViewById(R.id.ll_name);
        ll_distance = (LinearLayout) root.findViewById(R.id.ll_distance);
        ll_choose_theme = (LinearLayout) root.findViewById(R.id.ll_choose_theme);
        ll_time= (LinearLayout) root.findViewById(R.id.ll_time);
        date_tv = (TextView) root.findViewById(R.id.date_tv);
        end_date_tv = (TextView) root.findViewById(R.id.end_date_tv);
        time_tv =(TextView) root.findViewById(R.id.time_tv);
        challengeName_tv = (TextView) root.findViewById(R.id.challengeName_tv);
        tv_distance = (TextView) root.findViewById(R.id.tv_distance);
        btn_challenge = (Button) root.findViewById(R.id.btn_challenge);
        challengeType_radiogroup =(RadioGroup) root.findViewById(R.id.challengeType_radiogroup);
        rdb_walking=(RadioButton) root.findViewById(R.id.rdb_walking);
        rdb_running=(RadioButton) root.findViewById(R.id.rdb_running);
        theme_tv=(TextView) root.findViewById(R.id.theme_tv);

        ll_name.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           showCustomNameDialog(root);
                                       }
                                   });
        ll_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDistanceDialog(root);
            }
        });
        ll_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimeDialog(root);
            }
        });
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DatePickerTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                cDay = dayOfMonth;
                                cMonth = monthOfYear;
                                cYear = year;

                                // String date =dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                //  String date =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;


                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                             //   SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");

                                // SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

                                try {
                                    Date d = dateFormat.parse(date);
                                    System.out.println("Formated from" + dateFormat.format(d));
                                    // fromdateseterror_TV.setVisibility(View.GONE);
                                    date_tv.setText(dateFormat.format(d).toString());

                                } catch (Exception e) {
                                    //java.text.ParseException: Unparseable date: Geting error
                                    System.out.println("Excep" + e);
                                }
                                //TextView txtExactDate = (TextView) findViewById(R.id.txt_exactDate);


                                //txtDate.edita
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DatePickerTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                cDay = dayOfMonth;
                                cMonth = monthOfYear;
                                cYear = year;

                                // String date =dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                //  String date =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;


                                  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                               // SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");

                                // SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

                                try {
                                    Date d = dateFormat.parse(date);
                                    System.out.println("Formated from" + dateFormat.format(d));
                                    // fromdateseterror_TV.setVisibility(View.GONE);
                                    end_date_tv.setText(dateFormat.format(d).toString());

                                } catch (Exception e) {
                                    //java.text.ParseException: Unparseable date: Geting error
                                    System.out.println("Excep" + e);
                                }
                                //TextView txtExactDate = (TextView) findViewById(R.id.txt_exactDate);


                                //txtDate.edita
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                datePickerDialog.show();

            }
        });

        ll_choose_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new ThemesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
//                addChallengeModel.setEventType(true);
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesCreateChallengePost(addChallengeModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            Complited_challengeFragment ldf = new Complited_challengeFragment ();
                            Bundle args = new Bundle();
                            args.putString("ChallengeName",addChallengeModel.getChallengeName());
                            args.putString("Distance", String.valueOf(tv_distance.getText().toString()));
                            args.putString("startDate", startDate);
                            args.putString("endDate", endDate);
                            args.putString("time", time_tv.getText().toString());
                            ldf.setArguments(args);
                            getFragmentManager().beginTransaction().add(R.id.nav_host_fragment, ldf).commit();
                            //startActivity(new Intent(getActivity(), Complited_challengeFragment.class));
                            Toast.makeText(getActivity(), "Challenge Created is successfull.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_LONG).show();
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
            }
        });
   //     ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.activity_listview, mobileArray);

        /*ListView listView = (ListView) root.findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/
        return root;
    }

   /* public void onRadioButtonChallengeTypeClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rdb_walking:
                if (checked) {
                    challengeType=true;
                }
                break;
            case R.id.rdb_running:
                if (checked) {
                    challengeType=false;
                }
                break;
        }
    }
*/
     void showCustomNameDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
         ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog,viewGroup, false);


        EditText challengeName= dialogView.findViewById(R.id.challengeName);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.number_picker,viewGroup, false);

        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        distance_picker = dialogView.findViewById(R.id.distance_picker_picker);

        distance_picker.setMaxValue(4);
        distance_picker.setMinValue(0);
        distanceVals = new String[] {"100", "200", "300", "400", "500"};
        final int[] valuePicker1 = new int[1];
        distance_picker.setDisplayedValues(pickerVals);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //setting the view of the builder to our custom view that we already inflated
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
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.time_picker,viewGroup, false);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.time_picker,viewGroup, false);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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

}

