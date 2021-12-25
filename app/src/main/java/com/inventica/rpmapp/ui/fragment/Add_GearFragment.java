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
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_GearFragment extends Fragment {

    private Add_GearFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};
    LinearLayout ll_date,ll_name,ll_distance,ll_time;
    private int mYear, mMonth, mDay;
    private int cYear, cMonth, cDay;
    TextView date_tv;
    TextView challengeName_tv,time_tv;
    private NumberPicker distance_picker,label_picker;
    private String[] pickerVals,distanceVals;
    TimePicker timePicker;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_addgear, container, false);
        ll_date = (LinearLayout) root.findViewById(R.id.ll_date);
        ll_name = (LinearLayout) root.findViewById(R.id.ll_name);
        ll_distance = (LinearLayout) root.findViewById(R.id.ll_distance);
        ll_time= (LinearLayout) root.findViewById(R.id.ll_time);
        date_tv = (TextView) root.findViewById(R.id.date_tv);
        time_tv =(TextView) root.findViewById(R.id.time_tv);
        challengeName_tv = (TextView) root.findViewById(R.id.challengeName_tv);

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
   //     ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.activity_listview, mobileArray);

        /*ListView listView = (ListView) root.findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/
        return root;
    }


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

        distance_picker.setDisplayedValues(pickerVals);

        distance_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = distance_picker.getValue();
                Log.d("picker value", distanceVals[valuePicker1]);
            }
        });
        label_picker = dialogView.findViewById(R.id.distanceLabel_picker);
        pickerVals = new String[] {"Mi", "", "", "", ""};

        label_picker.setMaxValue(4);
        label_picker.setMinValue(0);
        label_picker.setDisplayedValues(pickerVals);

        label_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = label_picker.getValue();
                Log.d("picker value", pickerVals[valuePicker1]);
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
               // challengeName_tv.setText(challengeName.getText().toString());
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

        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);
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

                Log.e("tag","textView_time: "+textView_time.getText().toString());
                time_tv.setText(textView_time.getText().toString());
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

