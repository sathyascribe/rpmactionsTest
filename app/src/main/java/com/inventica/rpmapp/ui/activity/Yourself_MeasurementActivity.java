package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.WindowManager;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.AddUserHeightModel;
import org.openapitools.client.model.HeightModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.WeightModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class Yourself_MeasurementActivity extends AppCompatActivity {

    Button btn_continue;
    EditText weight_et,height_et;
    TextView tv_height,tv_weight;
    Spinner spinner;
    Spinner spinnerWeight;
    final HeightModel[] Obj_Class_heightDetails = new HeightModel[1];
    final Long[] sp_height = new Long[1];

    final WeightModel[] Obj_Class_weightDetails = new WeightModel[1];
    final Long[] sp_weight = new Long[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourself__measurement);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        weight_et=(EditText) findViewById(R.id.weight_et);
        height_et=(EditText)findViewById(R.id.height_et);
        tv_weight=(TextView) findViewById(R.id.tv_weight);
        tv_height=(TextView)findViewById(R.id.tv_height);

        tv_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeightDialog();
              //  showCustomHeightDialog();
            }
        });
        tv_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHightDialog();
             //   showCustomHeightDialog();
            }
        });
        btn_continue=(Button) findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long height=sp_height[0];
                Long weight=sp_weight[0];
                AddUserHeightModel addUserHeightModel=new AddUserHeightModel();
                addUserHeightModel.setHeight(Double.valueOf(height_et.getText().toString()));
                addUserHeightModel.setHeightMeasurementId(height);
                addUserHeightModel.setWeight(Double.valueOf(weight_et.getText().toString()));
                addUserHeightModel.setWeightMeasurementId(weight);
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddHeightandWeightPost(addUserHeightModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {

                            Toast.makeText(Yourself_MeasurementActivity.this, "Added successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Yourself_MeasurementActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(Yourself_MeasurementActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                        println("error ${t.message}");
                    }

                });


                //   finish();
            }
        });
    }

    void showHightDialog(){
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.spinner_dialog);
        TextView title= myDialog.findViewById(R.id.dialogTitle);
        title.setText("Height Measurement");
        spinner=myDialog.findViewById(R.id.spinner_SP);

        Button submit=myDialog.findViewById(R.id.submit_btn);
        Button cancel=myDialog.findViewById(R.id.cancel_btn);

        getHeightList();
        //txtclose.setText("M");
        //  btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_heightDetails[0] = (HeightModel) spinner.getSelectedItem();
                sp_height[0] = Obj_Class_heightDetails[0].getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   Log.e("tag","sp_height: "+sp_height.toString());
                tv_height.setText(spinner.getSelectedItem().toString());

                myDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.cancel();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    void showWeightDialog(){
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.spinner_weightdialog);
        TextView title= myDialog.findViewById(R.id.dialogTitle);
        title.setText("Weight Measurement");
        spinnerWeight=myDialog.findViewById(R.id.spinnerweight_SP);

        Button submit=myDialog.findViewById(R.id.submit_btn);
        Button cancel=myDialog.findViewById(R.id.cancel_btn);
        getWeightList();

        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_weightDetails[0] = (WeightModel) spinnerWeight.getSelectedItem();
                sp_weight[0] = Obj_Class_weightDetails[0].getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tag","sp_weight: "+sp_weight.toString());
                tv_weight.setText(spinnerWeight.getSelectedItem().toString());

                myDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.cancel();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


  public void getWeightList(){

      Rest_Adapter.getDropDownApi().apiDropDownGetAllMeasureWeightGet().enqueue(new Callback<List<WeightModel>>(){
          @Override
          public void onResponse(Call<List<WeightModel>> call, Response<List<WeightModel>> response) {
              if(response.isSuccessful())
              {


                  List<WeightModel> getCountrylist_obj = response.body();
                  // List<Country> userlist_obj=response.body().;

                  Log.e("getCountrylist_obj", String.valueOf(getCountrylist_obj.get(0).getWeightMeasurementName()));
                  WeightModel[] countrylist_arrayObj= new WeightModel[getCountrylist_obj.size()];

                  List<WeightModel> countryModelList = new ArrayList<>();
                  for(int i=0;i<getCountrylist_obj.size();i++){
                      WeightModel inner_countrylst= new WeightModel();
                      inner_countrylst.setWeightMeasurementName(getCountrylist_obj.get(i).getWeightMeasurementName());
                      inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                      inner_countrylst.setUnit(getCountrylist_obj.get(i).getUnit());
                      countrylist_arrayObj[i]=inner_countrylst;
                      countryModelList.add(inner_countrylst);
                  }
                  ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, countryModelList);
                  dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                  spinnerWeight.setAdapter(dataAdapter);

                  //login_progressDoalog.dismiss();

              }
          }

          @Override
          public void onFailure(Call<List<WeightModel>> call, Throwable t) {
              Log.e("TAG", "onFailure: " + t.toString());

              Log.e("tag", "Error:" + t.getMessage());

              //   login_progressDoalog.dismiss();
              Toast.makeText(Yourself_MeasurementActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
          }
      });
  }

    public void getHeightList(){

        Rest_Adapter.getDropDownApi().apiDropDownGetAllMeasureHeightGet().enqueue(new Callback<List<HeightModel>>(){
            @Override
            public void onResponse(Call<List<HeightModel>> call, Response<List<HeightModel>> response) {
                if(response.isSuccessful())
                {


                    List<HeightModel> getCountrylist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;

                    Log.e("getCountrylist_obj", String.valueOf(getCountrylist_obj.get(0).getHeightMeasurementName()));
                    HeightModel[] countrylist_arrayObj= new HeightModel[getCountrylist_obj.size()];

                    List<HeightModel> countryModelList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        HeightModel inner_countrylst= new HeightModel();
                        inner_countrylst.setHeightMeasurementName(getCountrylist_obj.get(i).getHeightMeasurementName());
                        inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                        inner_countrylst.setUnit(getCountrylist_obj.get(i).getUnit());
                        countrylist_arrayObj[i]=inner_countrylst;
                        countryModelList.add(inner_countrylst);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, countryModelList);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    spinner.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<HeightModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(Yourself_MeasurementActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

