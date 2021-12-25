package com.inventica.rpmapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.CityModel;
import org.openapitools.client.model.CountryModel;
import org.openapitools.client.model.LanguageModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.ProvinceStateModel;
import org.openapitools.client.model.UserRequestModel;
import org.openapitools.client.model.UserResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class ProfileFragment extends Fragment {

    private ProfileFragment galleryViewModel;

    TextView tv_name,tv_emailId;
    EditText et_gender,et_height,et_weight,et_age,et_group;
    Spinner sp_country,statelist_SP,citylist_SP,sp_language;
    CountryModel Obj_Class_countryDetails;
    ProvinceStateModel Obj_Class_stateDetails;
    CityModel Obj_Class_cityDetails;
    LanguageModel Obj_Class_languageDetails;
    Integer sp_countryId;
    Integer sp_stateId;
    Integer sp_cityId;
    Integer sp_language_int;
    Long sp_countryIdLong,sp_stateIdLong,sp_cityIdLong,sp_languageIdLong;
    Button save_btn;
    String FirstName_str,LastName_str;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        tv_name = root.findViewById(R.id.tv_name);
        tv_emailId = root.findViewById(R.id.tv_emailId);
        et_gender = root.findViewById(R.id.et_gender);
        et_height = root.findViewById(R.id.et_height);
        et_weight = root.findViewById(R.id.et_weight);
        et_age = root.findViewById(R.id.et_age);
        et_group = root.findViewById(R.id.et_group);
        sp_country = root.findViewById(R.id.sp_country);
        statelist_SP = root.findViewById(R.id.statelist_SP);
        citylist_SP = root.findViewById(R.id.citylist_SP);
        sp_language = root.findViewById(R.id.sp_language);
        save_btn = root.findViewById(R.id.save_btn);

        getCountryList();
        getLanguageList();



        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_countryDetails = (CountryModel) sp_country.getSelectedItem();
                sp_countryIdLong = Obj_Class_countryDetails.getId();
                sp_countryId = sp_countryIdLong.intValue();
                getStateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        statelist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_stateDetails = (ProvinceStateModel) statelist_SP.getSelectedItem();
                sp_stateIdLong = Obj_Class_stateDetails.getProvinceStateId();
                sp_stateId = sp_stateIdLong.intValue();
                getCityList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_cityDetails = (CityModel) citylist_SP.getSelectedItem();
                sp_cityIdLong = Obj_Class_cityDetails.getCityId();
              //  sp_cityId = sp_cityIdLong.intValue();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_languageDetails = (LanguageModel) sp_language.getSelectedItem();
                sp_languageIdLong = Obj_Class_languageDetails.getId();
                sp_language_int = sp_languageIdLong.intValue();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getUserProfile();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRequestModel userRequestModel=new UserRequestModel();
//                userRequestModel.setAge(Integer.valueOf(et_age.getText().toString()));
                userRequestModel.setCityId(sp_cityIdLong);
                userRequestModel.setCountryID(sp_countryIdLong);
                userRequestModel.setProvinceStateId(sp_stateIdLong);
                userRequestModel.setLanguage(sp_languageIdLong);
                userRequestModel.setDateOfBirth(null);
                userRequestModel.setFirstName(FirstName_str);
                userRequestModel.setLastName(LastName_str);
                userRequestModel.setGender(String.valueOf(et_gender.getText().toString()));
            //    userRequestModel.setGroup(Integer.valueOf(et_group.getText().toString()));
                userRequestModel.setHeight(Double.valueOf(et_height.getText().toString()));
                userRequestModel.setWeight(Double.valueOf(et_weight.getText().toString()));


                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesUpdateUserProfilePost(userRequestModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            Toast.makeText(getContext(), "Profile Updated successfully.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG).show();
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
                        println(" error ${t.message}");
                    }
                });
            }
        });
        return root;
    }
    public void getCountryList(){

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
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), R.layout.spinnercenterstyle, countryModelList);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    sp_country.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getStateList(){

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
                        // countryModelList.add(inner_countrylst);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), R.layout.spinnercenterstyle, statelist_arrayObj);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    statelist_SP.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<ProvinceStateModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());
                Log.e("MyException: ",t.toString());
                Log.e("MyException: ",t.toString());


                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getCityList(){

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
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), R.layout.spinnercenterstyle, citylist_arrayObj);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    citylist_SP.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getLanguageList(){

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
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), R.layout.spinnercenterstyle, Languagelist_arrayObj);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    sp_language.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<LanguageModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getUserProfile(){

        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetUserProfileGet().enqueue(new Callback<UserResponseModel>(){
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                Log.e("response.body", String.valueOf(response.body()));

//                Log.e("getUserprofile_obj", String.valueOf(response.body().getFirstName()));

                if(response.isSuccessful())
                {


                    UserResponseModel getUserprofile_obj = response.body();
                    FirstName_str=getUserprofile_obj.getFirstName();
                    LastName_str=getUserprofile_obj.getLastName();
                    tv_name.setText(getUserprofile_obj.getFirstName()+" "+getUserprofile_obj.getLastName());
                    tv_emailId.setText(getUserprofile_obj.getEmail());
                    et_age.setText(getUserprofile_obj.getAge().toString());
                    if(getUserprofile_obj.getGender().equalsIgnoreCase("1")){
                        et_gender.setText("Male");
                    }else{
                        et_gender.setText("Female");
                    }

                    et_group.setText(getUserprofile_obj.getGroup());
                    et_height.setText(getUserprofile_obj.getHeight().toString());
                    et_weight.setText(getUserprofile_obj.getWeight().toString());

                    /*sp_country.setSelection(1);
                    statelist_SP.setSelection(17);
                    citylist_SP.setSelection(379);
                    sp_language.setSelection(Integer.parseInt(getUserprofile_obj.getLanguageId().toString()));*/
                    // List<Country> userlist_obj=response.body().;

                 //   Log.e("getUserprofile_obj", String.valueOf(getUserprofile_obj.getFirstName()));
                 /*   CountryModel[] countrylist_arrayObj= new CountryModel[getCountrylist_obj.size()];

                    List<UserResponseModel> countryModelList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        UserResponseModel inner_countrylst= new UserResponseModel();

                    }*/


                }
            }

            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();

            }

        });
    }
}