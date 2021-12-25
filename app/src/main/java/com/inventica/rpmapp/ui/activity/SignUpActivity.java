package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.modles.State_Data;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.remote.Interface_userservice;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.CityModel;
import org.openapitools.client.model.CountryModel;
import org.openapitools.client.model.LanguageModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.ProvinceStateModel;
import org.openapitools.client.model.RegisterModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class SignUpActivity extends AppCompatActivity {

    Button joinus_btn;
    Interface_userservice userService;
    Spinner countrylist_SP,statelist_SP,citylist_SP,languagelist_SP;
    CountryModel[] arrayObj_Class_countryDetails;
    CountryModel Obj_Class_countryDetails;
    ProvinceStateModel[] arrayObj_Class_stateDetails;
    ProvinceStateModel Obj_Class_stateDetails;
    CityModel Obj_Class_cityDetails;
    LanguageModel Obj_Class_languageDetails;

    Integer sp_countryId;
    Integer sp_stateId;
    Integer sp_cityId;
    Integer sp_language;
    TextView dob_tv,tv_signIn;
    EditText et_emailId,et_phone,et_firstName,et_lastName,ed_password;
    private int mYear, mMonth, mDay;
    private int cYear, cMonth, cDay;
    RadioGroup gender_radiogroup;
    String str_gender="Male",O_Gender;
    RadioButton rdb_male,rdb_female;

    ArrayList<State_Data>state_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>city_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>language_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>country_datalist=new ArrayList<State_Data>();
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        //     userService = Class_ApiUtils.getUserService();
        dialog = new ProgressDialog(SignUpActivity.this);
        joinus_btn = (Button) findViewById(R.id.joinus_btn);
        countrylist_SP = (Spinner) findViewById(R.id.countrylist_SP);
        statelist_SP = (Spinner) findViewById(R.id.statelist_SP);
        citylist_SP = (Spinner) findViewById(R.id.citylist_SP);
        languagelist_SP = (Spinner) findViewById(R.id.languagelist_SP);
        et_emailId = (EditText) findViewById(R.id.et_emailId);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_firstName = (EditText) findViewById(R.id.et_firstName);
        et_lastName = (EditText) findViewById(R.id.et_lastName);
        ed_password = (EditText) findViewById(R.id.ed_password);
        gender_radiogroup =(RadioGroup)findViewById(R.id. gender_radiogroup);
        rdb_male=(RadioButton) findViewById(R.id.rdb_male);
        rdb_female=(RadioButton) findViewById(R.id.rdb_female);
        dob_tv = (TextView) findViewById(R.id.dob_tv);
        tv_signIn = (TextView) findViewById(R.id.tv_signIn);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getCountryList();
            getLanguageList();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
        dob_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, R.style.DatePickerTheme,
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
                                    dob_tv.setText(dateFormat.format(d).toString());

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
       /* joinus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        countrylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* Obj_Class_countryDetails = (CountryModel) countrylist_SP.getSelectedItem();
                Long sp_countryIdLong = Obj_Class_countryDetails.getId();
                sp_countryId = sp_countryIdLong.intValue();*/


                State_Data state_data= new State_Data();
                state_data = (State_Data) countrylist_SP.getSelectedItem();
                sp_countryId = Integer.parseInt(state_data.getValue());
                getStateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        statelist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Obj_Class_stateDetails = (ProvinceStateModel) statelist_SP.getSelectedItem();
                Long sp_stateIdLong = Obj_Class_stateDetails.getProvinceStateId();
                sp_stateId = sp_stateIdLong.intValue();*/

               // sp_stateId = Integer.parseInt(state_list.get(position).get("Value"));

                State_Data state_data= new State_Data();
                state_data = (State_Data) statelist_SP.getSelectedItem();
                sp_stateId = Integer.parseInt(state_data.getValue());

                getCityList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* Obj_Class_cityDetails = (CityModel) citylist_SP.getSelectedItem();
                Long sp_cityIdLong = Obj_Class_cityDetails.getCityId();
                sp_cityId = sp_cityIdLong.intValue();*/
                State_Data state_data= new State_Data();
                state_data = (State_Data) citylist_SP.getSelectedItem();
                sp_cityId = Integer.parseInt(state_data.getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        citylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Obj_Class_cityDetails = (CityModel) citylist_SP.getSelectedItem();
//                Long sp_cityIdLong = Obj_Class_cityDetails.getCityId();
//                sp_cityId = sp_cityIdLong.intValue();
//               // getCityList();
//                //     AsyncTask_Get_CityList(sp_stateId);
//             /*   if (sel_statesp_new != sel_statesp) {
//                    sel_statesp = sel_statesp_new;
//                    ViewFarmerList_arraylist.clear();
//                    farmerListViewAdapter.notifyDataSetChanged();
//                    districtlist_SP.setSelection(0);
//                    taluklist_SP.setSelection(0);
//                    villagelist_SP.setSelection(0);
//                    grampanchayatlist_SP.setSelection(0);
//                }*/
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        citylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Obj_Class_cityDetails = (CityModel) citylist_SP.getSelectedItem();
//             //   Long sp_cityIdLong = Obj_Class_cityDetails.getCityId();
////                sp_cityId = sp_cityIdLong.intValue();
//               // getCityList();
//                //     AsyncTask_Get_CityList(sp_stateId);
//             /*   if (sel_statesp_new != sel_statesp) {
//                    sel_statesp = sel_statesp_new;
//                    ViewFarmerList_arraylist.clear();
//                    farmerListViewAdapter.notifyDataSetChanged();
//                    districtlist_SP.setSelection(0);
//                    taluklist_SP.setSelection(0);
//                    villagelist_SP.setSelection(0);
//                    grampanchayatlist_SP.setSelection(0);
//                }*/
//
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        languagelist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  Obj_Class_languageDetails = (LanguageModel) languagelist_SP.getSelectedItem();
                Long sp_languageIdLong = Obj_Class_languageDetails.getId();
                sp_language = sp_languageIdLong.intValue();*/

                State_Data state_data= new State_Data();
                state_data = (State_Data) languagelist_SP.getSelectedItem();
                sp_language = Integer.parseInt(state_data.getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        joinus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId;
                String phone;
                String firstName;
                String lastName;
                String password;
                Date dob = null;
                emailId = et_emailId.getText().toString();
                phone = et_phone.getText().toString();
                firstName = et_firstName.getText().toString();
                lastName = et_lastName.getText().toString();
                password = ed_password.getText().toString();
                SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    dob = myFormat.parse(dob_tv.getText().toString());   // initialize start date
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //  dob=dob_et.getText().toString();
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
                if (validation()) {
                    dialog.setMessage("Loading..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    RegisterModel registerModel = new RegisterModel();

                    registerModel.setEmail(emailId);
                    registerModel.setFirstName(firstName);
                    registerModel.setLastName(lastName);
                    registerModel.setContactNumber(phone);
                    registerModel.password(password);
                    registerModel.dateOfBirth(dob);
                    registerModel.setCityId(Long.valueOf(sp_cityId));
                    registerModel.setProvinceStateId(Long.valueOf(sp_stateId));
                    registerModel.setCountryID(Long.valueOf(sp_countryId));
                    registerModel.setLanguageId(Long.valueOf(sp_language));
                    registerModel.setRoleId(2); //1 for Admin and 2 for User
                    if(rdb_female.isChecked())
                    {
                        // is checked
                        O_Gender="2";
                        str_gender="Female";
                    }
                    else
                    {
                        // not checked
                        O_Gender="1";
                        str_gender="Male";
                    }
                    registerModel.setGender(O_Gender);
                    // firstName,lastName,emailId,password,phone,str_gender,sp_cityId,sp_stateId,sp_countryId,sp_language,dob,0
                    Rest_Adapter.getAccountApi().apiAccountRegisterPost(registerModel).enqueue(new Callback<ModelApiResponse>() {
                        @Override
                        public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                            Log.e("tag", "response==" + response.body().getMessage());

                          //  if(response.code()==200){
                            if (response.body().getStatusCode()==200) {
                               // if (response.message().equalsIgnoreCase("Success")) {
                                    startActivity(new Intent(SignUpActivity.this, PaymentActivity.class));
                                    Toast.makeText(SignUpActivity.this, "Registration is successfull.", Toast.LENGTH_LONG).show();
                                } else {
                                Toast.makeText(SignUpActivity.this, response.message(), Toast.LENGTH_LONG).show();
                            }
                            //}
                          //  } //else
                        /*if (response.code() == 409)
                            Toast.makeText(getApplicationContext(), "Email Id is already registered", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try later", Toast.LENGTH_LONG).show();*/

                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                            println("register error ${t.message}");
                            dialog.dismiss();
                        }
                    });
                }
                }else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                //   finish();
            }
        });
    }

    public void onRadioButtonGenderClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rdb_male:
                if (checked) {
                    str_gender="Male";
                }
                break;
            case R.id.rdb_female:
                if (checked) {
                    str_gender="Female";
                }
                break;
        }
    }


    public void getCountryList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getDropDownApi().apiDropDownGetCountryGet().enqueue(new Callback<List<CountryModel>>(){
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if(response.isSuccessful())
                {


                    List<CountryModel> getCountrylist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;

                    Log.e("getCountrylist_obj", String.valueOf(getCountrylist_obj.get(0).getCountry()));
                    CountryModel[] countrylist_arrayObj= new CountryModel[getCountrylist_obj.size()];

                    List<CountryModel> countryModelList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        CountryModel inner_countrylst= new CountryModel();
                        inner_countrylst.setCountry(getCountrylist_obj.get(i).getCountry());
                        inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                        countrylist_arrayObj[i]=inner_countrylst;
                        countryModelList.add(inner_countrylst);

                        State_Data state_data = new State_Data();
                        state_data.setText(getCountrylist_obj.get(i).getCountry());
                        state_data.setValue(getCountrylist_obj.get(i).getId().toString());
                        country_datalist.add(state_data);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, country_datalist);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    countrylist_SP.setAdapter(dataAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getStateList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getDropDownApi().apiDropDownGetProvinceStateGet(sp_countryId).enqueue(new Callback<List<ProvinceStateModel>>(){
            @Override
            public void onResponse(Call<List<ProvinceStateModel>> call, Response<List<ProvinceStateModel>> response) {

                Log.e("MyException: ",response.toString());
                if(response.isSuccessful())
                {

                    List<ProvinceStateModel> getStatelist_obj = response.body();

                    //Log.e("ProvinceStateModel", String.valueOf(getCountrylist_obj.get(0).getProvinceStateName()));
                    ProvinceStateModel[] statelist_arrayObj= new ProvinceStateModel[getStatelist_obj.size()];

                    List<ProvinceStateModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getStatelist_obj.size();i++){
                        ProvinceStateModel inner_statelst= new ProvinceStateModel();
                        inner_statelst.setProvinceStateName(getStatelist_obj.get(i).getProvinceStateName());
                        inner_statelst.setProvinceStateId(getStatelist_obj.get(i).getProvinceStateId());
                        statelist_arrayObj[i]=inner_statelst;
                       /* HashMap<String,String> map = new HashMap<String, String>();
                        map.put("Text",getStatelist_obj.get(i).getProvinceStateName());
                        map.put("Value",String.valueOf(getStatelist_obj.get(i).getProvinceStateId()));*/
                       // map.put(getStatelist_obj.get(i).getProvinceStateName().toString(), String.valueOf(getStatelist_obj.get(i).getProvinceStateId()));
                      //  state_list.add(map);
                        State_Data state_data = new State_Data();
                        state_data.setText(getStatelist_obj.get(i).getProvinceStateName());
                        state_data.setValue(getStatelist_obj.get(i).getProvinceStateId().toString());
                       // state_list.add(map);
                        state_datalist.add(state_data);
                       // countryModelList.add(inner_countrylst);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, state_datalist);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    statelist_SP.setAdapter(dataAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<ProvinceStateModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                Log.e("MyException: ",t.toString());

               dialog.dismiss();
                Toast.makeText(SignUpActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getCityList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getDropDownApi().apiDropDownGetCityGet(sp_stateId).enqueue(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                if(response.isSuccessful())
                {


                    List<CityModel> getCitylist_obj = response.body();

                    CityModel[] citylist_arrayObj= new CityModel[getCitylist_obj.size()];

                  ///  List<CityModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getCitylist_obj.size();i++){
                        CityModel inner_citylst= new CityModel();
                        inner_citylst.setCityName(getCitylist_obj.get(i).getCityName());
                        inner_citylst.setCityId(getCitylist_obj.get(i).getCityId());
                        citylist_arrayObj[i]=inner_citylst;
                        // countryModelList.add(inner_countrylst);

                        State_Data state_data = new State_Data();
                        state_data.setText(getCitylist_obj.get(i).getCityName());
                        state_data.setValue(getCitylist_obj.get(i).getCityId().toString());
                        city_datalist.add(state_data);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, city_datalist);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    citylist_SP.setAdapter(dataAdapter);

                   dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getLanguageList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getDropDownApi().apiDropDownGetLanguageGet().enqueue(new Callback<List<LanguageModel>>(){
            @Override
            public void onResponse(Call<List<LanguageModel>> call, Response<List<LanguageModel>> response) {
                if(response.isSuccessful())
                {


                    List<LanguageModel> getLanguagelist_obj = response.body();

                    LanguageModel[] Languagelist_arrayObj= new LanguageModel[getLanguagelist_obj.size()];

                    ///  List<CityModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getLanguagelist_obj.size();i++){
                        LanguageModel inner_citylst= new LanguageModel();
                        inner_citylst.setLanguageName(getLanguagelist_obj.get(i).getLanguageName());
                        inner_citylst.setId(getLanguagelist_obj.get(i).getId());
                        Languagelist_arrayObj[i]=inner_citylst;
                        // countryModelList.add(inner_countrylst);

                        State_Data state_data = new State_Data();
                        state_data.setText(getLanguagelist_obj.get(i).getLanguageName());
                        state_data.setValue(getLanguagelist_obj.get(i).getId().toString());
                        // state_list.add(map);
                        language_datalist.add(state_data);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, language_datalist);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    languagelist_SP.setAdapter(dataAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<LanguageModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isValidMobile(String phone)
    {
        String expression = "^[6-9][0-9]{9}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;

        }
    }

    public boolean validation() {

        Boolean bemail = true, bpassword = true, bfName = true, blName=true, bPhone=true;

        if (et_emailId.getText().toString().length() == 0) {
            et_emailId.setError("Email Id is required!");
            bemail = false;
        }else{
            if(isValidMail(et_emailId.getText().toString())){

                bemail = true;

            }else{
                Toast.makeText(this, "Invalid Email Id", Toast.LENGTH_SHORT).show();
                et_emailId.setError("Invalid Email ID");
                bemail = false;
            }
        }

        if (ed_password.getText().toString().length() == 0) {
            ed_password.setError("Password is required!");
            bpassword = false;
        }
        if (et_firstName.getText().toString().length() == 0) {
            et_firstName.setError("First Name is required!");
            bfName = false;
        }
        if (et_lastName.getText().toString().length() == 0) {
            et_lastName.setError("Last Name is required!");
            blName = false;
        }
        if (et_phone.getText().toString().length() == 0) {
            et_phone.setError("Phone No is required!");
            bPhone = false;
        }else{
            if(isValidMobile(et_phone.getText().toString())){
                bPhone = true;

            }else{
                et_phone.setError("Invalid Mobile Number");
                bPhone = false;
                //Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
            }

        }
        if (bemail && bpassword && bfName && blName && bPhone) {
            return true;
        } else {
            return false;
        }
    }

}

