package com.inventica.rpmapp.ui.activity.gear;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.jetbrains.annotations.NotNull;
import org.openapitools.client.model.GetGrearBrandModel;
import org.openapitools.client.model.GetGrearBrandModelListApiResponse;
import org.openapitools.client.model.GetGrearModel;
import org.openapitools.client.model.GetGrearModelListApiResponse;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.UserGearModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGear extends AppCompatActivity {


    private int mYear, mMonth, mDay;
    private TextView text_date_of_using, text_set_distance;
    private NumberPicker distance_picker, label_picker;
    private String[] pickerValue, distanceUnit;
    private AutoCompleteTextView brandName, modelName;
    private final ArrayList<String> brandList = new ArrayList<>();
    private List<GetGrearBrandModel> brandListValue = new ArrayList<>();
    private final ArrayList<String> modelList = new ArrayList<>();
    private List<GetGrearModel> modelListVale = new ArrayList<>();
    private Map<String, Integer> brandMap = new HashMap<>();
    private Map<String, Integer> modelMap = new HashMap<>();
    private ArrayAdapter<String> brandAdapter;
    private ArrayAdapter<String> modelAdapter;
    private int brandId, modelId;
    private RadioButton radioButtonWalking, radioButtonRunning;
    private String distanceUnitString = "km";
    private Context mContext;
    private ProgressDialog loadingProgressBar;
    private Date selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goals);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Gears");
        mContext = this;

        getBrandName();
        getAllView();
        setClickOnView();

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

    private void getAllView() {
        LinearLayout ll_date = (LinearLayout) findViewById(R.id.ll_date);

        LinearLayout ll_distance = (LinearLayout) findViewById(R.id.ll_distance);

        brandName = findViewById(R.id.text_brand_name);
        modelName = findViewById(R.id.text_model_name);

        radioButtonRunning = findViewById(R.id.radio_running);
        radioButtonWalking = findViewById(R.id.radio_walking);

        text_date_of_using = findViewById(R.id.text_date_of_using);
        text_set_distance = findViewById(R.id.text_set_distance);

         loadingProgressBar = new ProgressDialog(mContext);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> checkValidation());

        ll_distance.setOnClickListener(this::showCustomDistanceDialog);

        ll_date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddGear.this, R.style.DatePickerTheme,
                    (view, year, monthOfYear, dayOfMonth) -> {

                        String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        try {

                           selectedDate = dateFormat.parse(date);
                           text_date_of_using.setText(dateFormat.format(selectedDate));

                        } catch (Exception e) {

                            Log.d("Exception : ", e.toString());
                        }

                    }, mYear, mMonth, mDay);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            datePickerDialog.show();

        });

    }

    private void setClickOnView() {

        radioButtonRunning.setOnClickListener(v -> {
            if (radioButtonRunning.isChecked()) {
                radioButtonWalking.setChecked(false);
                radioButtonRunning.setChecked(true);
            }

        });
        radioButtonWalking.setOnClickListener(v -> {
            if (radioButtonWalking.isChecked()) {
                radioButtonWalking.setChecked(true);
                radioButtonRunning.setChecked(false);
            }

        });
        brandAdapter =
                new ArrayAdapter<>(this, R.layout.auto_complete_text, R.id.text1, brandList);
        modelAdapter =
                new ArrayAdapter<>(this, R.layout.auto_complete_text, R.id.text1, modelList);

        brandName.addTextChangedListener(
                (new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        ArrayList<String> newList = new ArrayList<>();

                        // ArrayList<String> newArray = brandList.stream().filter( item -> item.contains(s)))..Collectors.toList();
                        for (int i = 0; i < brandList.size(); i++) {
                            if (brandList.get(i).toLowerCase().contains(s)) {
                                newList.add(brandList.get(i));
                            }
                        }
//                     if(newList.size()==0)
//
//                         Toast.makeText(AddGear.this,"No Brand Found", Toast.LENGTH_LONG).show();

                        brandAdapter =
                                new ArrayAdapter<>(AddGear.this, R.layout.auto_complete_text, R.id.text1, newList);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                })
        );
        brandName.setThreshold(1);
        brandName.setOnItemClickListener((parent, arg1, position, arg3) -> {
            Object item = parent.getItemAtPosition(position);
            if (item instanceof String) {

                brandId = brandMap.get(item.toString());
                Log.d("AddGearActivity : ", "onItemClick: " + brandId);
                getModelName(brandId);

            }
        });

        modelName.addTextChangedListener(
                (new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        ArrayList<String> newList = new ArrayList<>();

                        // ArrayList<String> newArray = brandList.stream().filter( item -> item.contains(s)))..Collectors.toList();
                        for (int i = 0; i < modelList.size(); i++) {
                            if (modelList.get(i).toLowerCase().contains(s)) {
                                newList.add(modelList.get(i));
                            }
                        }
//                        if(newList.size()==0)
//
//                            Toast.makeText(AddGear.this,"No Brand Model Found", Toast.LENGTH_LONG).show();

                        modelAdapter =
                                new ArrayAdapter<String>(AddGear.this, R.layout.auto_complete_text, R.id.text1, newList);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                })
        );
        modelName.setThreshold(1);
        modelName.setOnItemClickListener((parent, arg1, position, arg3) -> {
            Object item = parent.getItemAtPosition(position);
            if (item instanceof String) {

                modelId = modelMap.get(item.toString());

                Log.d("ModelId: ", modelId + "");
            }
        });

        brandName.setAdapter(brandAdapter);
        modelName.setAdapter(modelAdapter);
    }

    private void checkValidation() {

        if (brandName.getText().toString() == null) {
            Log.d("Validation: ", "Please Enter Brand Name");
            Toast.makeText(AddGear.this, "Please Enter Brand Name", Toast.LENGTH_LONG).show();

        } else if (modelName.getText().toString() == null) {
            Log.d("Validation: ", "Please Enter Model Name");
            Toast.makeText(AddGear.this, "Please Enter Model Name", Toast.LENGTH_LONG).show();
        } else if (text_set_distance.getText().toString().equals(R.string.set_max_distance)) {
            Log.d("Validation: ", "Please Select Distance");
            Toast.makeText(AddGear.this, "Please Select Distance", Toast.LENGTH_LONG).show();
        } else if (text_date_of_using.getText().toString().equals(R.string.set_date_of_start_usage)) {
            Log.d("Validation: ", "Please Select Date");
            Toast.makeText(AddGear.this, "Please Select Date", Toast.LENGTH_LONG).show();
        } else {
            loadingProgressBar.setMessage("Adding Shoe ...");
            loadingProgressBar.show();

            addShoe();
        }

    }

    private void addShoe() {

        try {

            int gearBrandId = brandId;
            int gearId = modelId;
            boolean isWalking = radioButtonWalking.isChecked();
            int maxDistance = distance;
            Date startDate = selectedDate;
            int distanceUnitId = distanceMeasurementId;

            UserGearModel userGearModel = new UserGearModel();
            userGearModel.gearId((long) gearId);
            userGearModel.nickname("NiceName");
            userGearModel.gearBrandId((long) gearBrandId);
            userGearModel.distanceMeasurementId((long)distanceUnitId);
            userGearModel.isWalking(isWalking);
            userGearModel.startDate(startDate);
            userGearModel.maxDistance((long) maxDistance);


            Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddGearDetailsPost(userGearModel).enqueue(new Callback<ModelApiResponse>() {
                @Override
                public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                    loadingProgressBar.dismiss();
                    if (response.isSuccessful() && response.code() == 200) {
                        Toast.makeText(AddGear.this, "Shoe Added Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    } else if (response.isSuccessful() && response.code() == 500) {
                        Toast.makeText(AddGear.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(AddGear.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                    loadingProgressBar.dismiss();
                    Toast.makeText(AddGear.this, "", Toast.LENGTH_LONG).show();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            loadingProgressBar.dismiss();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(AddGear.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private void getModelName(int modelId) {

        try {

            Rest_Adapter.getDropDownApi().apiDropDownGetAllModelGet(modelId).enqueue(new Callback<GetGrearModelListApiResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(@NotNull Call<GetGrearModelListApiResponse> call, @NotNull Response<GetGrearModelListApiResponse> response) {
                    Log.d("MyResponse: ", response.toString());
                    if (response.isSuccessful()) {
                        modelList.clear();
                        modelListVale.clear();
                        modelMap.clear();
                        assert response.body() != null;
                        modelListVale = response.body().getData();
                        assert response.body().getData() != null;
                        Log.d("AddGearActivity : ", "response: " + response.body().getData().toString());
                        if (modelListVale.size() > 0) {
                            modelName.setEnabled(true);
                            modelName.setFocusableInTouchMode(true);
                            for (int i = 0; i < modelListVale.size(); i++) {
                                modelList.add(modelListVale.get(i).getModelName());
                                String s = modelListVale.get(i).getModelName();
                                Integer u = Math.toIntExact(modelListVale.get(i).getModelId());
                                modelMap.put(s, u);
                            }
                        } else {
                            modelName.setEnabled(false);
                            modelName.setFocusable(false);
                            Toast.makeText(AddGear.this, "No model found for this brand, please select another brand", Toast.LENGTH_LONG).show();
                        }
                    }
                    modelAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<GetGrearModelListApiResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getBrandName() {

        try {

            Rest_Adapter.getDropDownApi().apiDropDownGetAllGearBrandGet().enqueue(new Callback<GetGrearBrandModelListApiResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<GetGrearBrandModelListApiResponse> call, Response<GetGrearBrandModelListApiResponse> response) {
                    Log.d("MyResponse: ", response.toString());
                    if (response.isSuccessful()) {

                        brandList.clear();
                        brandListValue.clear();
                        brandMap.clear();
                        brandListValue = response.body().getData();
                        for (int i = 0; i < brandListValue.size(); i++) {
                            brandList.add(brandListValue.get(i).getBrand());
                            String s = brandListValue.get(i).getBrand();
                            Integer u = Math.toIntExact(brandListValue.get(i).getId());
                            brandMap.put(s, u);
                        }
                    }
                    Log.d("All Brand Map : ", brandMap.toString());
                    brandAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<GetGrearBrandModelListApiResponse> call, Throwable t) {

                    t.printStackTrace();
                    t.toString();

                    Log.e("MyException: ", t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDistanceUnit(int distanceMeasurementId) {

        String distanceUnit ;
        switch (distanceMeasurementId)
        {
            case 1 : distanceUnit = "Km";
                break;
            case 2 : distanceUnit = "Mi";
                break;
            default: distanceUnit = "M";
        }
        return distanceUnit;

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    void showCustomDistanceDialog(View root) {

        ViewGroup viewGroup = root.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(AddGear.this).inflate(R.layout.number_picker, viewGroup, false);
        Button submit = dialogView.findViewById(R.id.submit_btn);
        Button cancel = dialogView.findViewById(R.id.cancel_btn);
        distance_picker = dialogView.findViewById(R.id.distance_picker_picker);
        dialogView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        distance_picker.setMaxValue(4);
        distance_picker.setMinValue(1);
        distanceUnit = new String[]{"100", "200", "300", "400", "500"};
        distance_picker.setDisplayedValues(pickerValue);
        distance_picker.setOnValueChangedListener((numberPicker, i, i1) -> {
            int valuePicker1 = distance_picker.getValue();
            Log.d("picker value", distanceUnit[valuePicker1]);

            distanceUnitString = getDistanceUnit(distance_picker.getValue());
        });
        label_picker = dialogView.findViewById(R.id.distanceLabel_picker);
        pickerValue = new String[]{"Meter", "Kilometer", "Mile", "", ""};
        label_picker.setMaxValue(4);
        label_picker.setMinValue(1);
        label_picker.setDisplayedValues(pickerValue);
        label_picker.setOnValueChangedListener((numberPicker, i, i1) -> {
            int valuePicker1 = label_picker.getValue();
            Log.d("picker value", pickerValue[valuePicker1]);
            getDistanceUnit(valuePicker1);
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(AddGear.this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(v -> {

            Log.e("tag", "name: " + text_set_distance.getText().toString());
            distance = distance_picker.getValue();
            distanceMeasurementId = label_picker.getValue();
            text_set_distance.setText(String.valueOf(distance)+ getDistanceUnit(label_picker.getValue()));
            alertDialog.cancel();
        });
        cancel.setOnClickListener(v -> alertDialog.cancel());
        alertDialog.show();
    }

    private  int distance = 0;
    private int distanceMeasurementId= 0;




}