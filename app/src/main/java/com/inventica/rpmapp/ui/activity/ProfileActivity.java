package com.inventica.rpmapp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.modles.State_Data;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;
import com.squareup.picasso.Picasso;

import org.openapitools.client.model.CityModel;
import org.openapitools.client.model.CountryModel;
import org.openapitools.client.model.LanguageModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.ProfilePhotoModel;
import org.openapitools.client.model.ProvinceStateModel;
import org.openapitools.client.model.UserRequestModel;
import org.openapitools.client.model.UserResponseModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class ProfileActivity extends AppCompatActivity {

    TextView tv_name,tv_emailId;
    EditText et_gender,et_height,et_weight,et_age,et_group;
    Spinner sp_country,statelist_SP,citylist_SP,sp_language;
    int stateInt,cityInt,languageInt;
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
    Context mContext;
    RoundedImageView imageProfile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String studPickBase64 = null;
    byte[] imageinbytesArray={0};
    Bitmap bmp_fromCamera;

    ArrayList<State_Data>state_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>city_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>language_datalist=new ArrayList<State_Data>();
    ArrayList<State_Data>country_datalist=new ArrayList<State_Data>();
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        mContext = this;
        dialog = new ProgressDialog(mContext);
        getAllView();
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getCountryList();
            getLanguageList();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }



        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Obj_Class_countryDetails = (CountryModel) sp_country.getSelectedItem();
                sp_countryIdLong = Obj_Class_countryDetails.getId();
                sp_countryId = sp_countryIdLong.intValue();*/
                State_Data state_data= new State_Data();
                state_data = (State_Data) sp_country.getSelectedItem();
                sp_countryId = Integer.parseInt(state_data.getValue());
                sp_countryIdLong = Long.valueOf(sp_countryId);
                getStateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        statelist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* Obj_Class_stateDetails = (ProvinceStateModel) statelist_SP.getSelectedItem();
                sp_stateIdLong = Obj_Class_stateDetails.getProvinceStateId();
                sp_stateId = sp_stateIdLong.intValue();*/
                State_Data state_data= new State_Data();
                state_data = (State_Data) statelist_SP.getSelectedItem();
                sp_stateId = Integer.parseInt(state_data.getValue());
                sp_stateIdLong = Long.valueOf(sp_stateId);
                getCityList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citylist_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*Obj_Class_cityDetails = (CityModel) citylist_SP.getSelectedItem();
                sp_cityIdLong = Obj_Class_cityDetails.getCityId();*/
                //  sp_cityId = sp_cityIdLong.intValue();
                State_Data state_data= new State_Data();
                state_data = (State_Data) citylist_SP.getSelectedItem();
                sp_cityId = Integer.parseInt(state_data.getValue());
                sp_cityIdLong = Long.valueOf(sp_cityId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /* Obj_Class_languageDetails = (LanguageModel) sp_language.getSelectedItem();
                sp_languageIdLong = Obj_Class_languageDetails.getId();
                sp_language_int = sp_languageIdLong.intValue();*/

                State_Data state_data= new State_Data();
                state_data = (State_Data) sp_language.getSelectedItem();
                sp_language_int = Integer.parseInt(state_data.getValue());
                sp_languageIdLong = Long.valueOf(sp_language_int);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getUserProfile();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {

                UserRequestModel userRequestModel=new UserRequestModel();
          //      userRequestModel.setAge(Integer.valueOf(et_age.getText().toString()));
                userRequestModel.setCityId(sp_cityIdLong);
                userRequestModel.setCountryID(sp_countryIdLong);
                userRequestModel.setProvinceStateId(sp_stateIdLong);
                userRequestModel.setLanguage(sp_languageIdLong);
                userRequestModel.setDateOfBirth(null);
                userRequestModel.setFirstName(FirstName_str);
                userRequestModel.setLastName(LastName_str);
                userRequestModel.setGender(String.valueOf(et_gender.getText().toString()));
             //   userRequestModel.setGroup(Integer.valueOf(et_group.getText().toString()));
                userRequestModel.setHeight(Double.valueOf(et_height.getText().toString()));
                userRequestModel.setWeight(Double.valueOf(et_weight.getText().toString()));
             //   userRequestModel.setProfilePhoto(studPickBase64);


                dialog.setMessage("Loading..");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesUpdateUserProfilePost(userRequestModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
//                        Log.e("tag", "response==" + response.body().getMessage());

                        dialog.dismiss();
                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {

                            Toast.makeText(ProfileActivity.this, "Profile Updated successfully.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
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
                        dialog.dismiss();
                    }
                });

                if(studPickBase64!=null){

                    ProfilePhotoModel profilePhotoModel=new ProfilePhotoModel();
                    profilePhotoModel.setPhotoPath(studPickBase64);
                    Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddProfilePhotoPost(profilePhotoModel).enqueue(new Callback<ModelApiResponse>() {
                        @Override
                        public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                            Log.e("tag", "response==" + response.body().getMessage());

                            //  if(response.code()==200){
                            if (response.body().getStatusCode()==200) {
                                // if (response.message().equalsIgnoreCase("Success")) {

                                Toast.makeText(ProfileActivity.this, "Profile photo updated successfully.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
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

                }else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void getAllView() {
        tv_name = findViewById(R.id.tv_name);
        tv_emailId = findViewById(R.id.tv_emailId);
        et_gender = findViewById(R.id.et_gender);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        et_age = findViewById(R.id.et_age);
        et_group = findViewById(R.id.et_group);
        sp_country = findViewById(R.id.sp_country);
        statelist_SP = findViewById(R.id.statelist_SP);
        citylist_SP = findViewById(R.id.citylist_SP);
        sp_language = findViewById(R.id.sp_language);
        save_btn = findViewById(R.id.save_btn);
        imageProfile = findViewById(R.id.imageProfile);
       // roundedImageView = (RoundedImageView) findViewById(R.id.user_image);
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
                    sp_country.setAdapter(dataAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                        // countryModelList.add(inner_countrylst);
                        State_Data state_data = new State_Data();
                        state_data.setText(getStatelist_obj.get(i).getProvinceStateName());
                        state_data.setValue(getStatelist_obj.get(i).getProvinceStateId().toString());
                        // state_list.add(map);
                        state_datalist.add(state_data);
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
                Log.e("MyException: ",t.toString());


                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                    sp_language.setAdapter(dataAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<LanguageModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getUserProfile(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetUserProfileGet().enqueue(new Callback<UserResponseModel>(){
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                Log.e("response.body", String.valueOf(response.body()));

//                Log.e("getUserprofile_obj", String.valueOf(response.body().getFirstName()));

                if(response.isSuccessful())
                {

                    dialog.dismiss();
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

                    Picasso.with(mContext)
                            .load(getUserprofile_obj.getProfilePhoto()) // Equivalent of what ends up in onBitmapLoaded
                            .placeholder(R.drawable.profile_temp)
                            .error(R.drawable.profile_temp)
                            .centerCrop()
                            .fit()
                            .into(imageProfile);

                    sp_country.setSelection(getIndex(sp_country, getUserprofile_obj.getCountryName()));
                    statelist_SP.setSelection(getIndex(statelist_SP, getUserprofile_obj.getProvinceStateName()));
                   citylist_SP.setSelection(getIndex(citylist_SP, getUserprofile_obj.getCityName()));
                    sp_language.setSelection(getIndex(sp_language, getUserprofile_obj.getLanguageName()));

                     /*stateInt= Integer.parseInt(getUserprofile_obj.getProvinceStateId().toString());
                     cityInt= Integer.parseInt(getUserprofile_obj.getCityId().toString());
                     languageInt= Integer.parseInt(getUserprofile_obj.getLanguageId().toString());
                    statelist_SP.setSelection(stateInt);
                    citylist_SP.setSelection(cityInt);
                    sp_language.setSelection(languageInt);*/

                  //  int countryInt= Integer.parseInt(getUserprofile_obj.getCountryID().toString());
                   /* int stateInt= Integer.parseInt(getUserprofile_obj.getProvinceStateId().toString());
                    int cityInt= Integer.parseInt(getUserprofile_obj.getCityId().toString());
                    int languageInt= Integer.parseInt(getUserprofile_obj.getLanguageId().toString());
                    sp_country.setSelection(1);
                    statelist_SP.setSelection(stateInt);
                    citylist_SP.setSelection(cityInt);
                    sp_language.setSelection(languageInt);*/
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

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();

            }

        });
    }

    public int getIndex(Spinner spinner, String myString) {
        int index = 0;
        String item = null;
        for (int i = 0; i < spinner.getCount(); i++) {
            //   Log.e("entered getIndex", "entered getIndex");

            item = spinner.getItemAtPosition(i).toString();
            if (item.equals(myString)) {
                index = i;
                Log.e("entered myString i=", String.valueOf(index));

            }
        }
        return index;
    }
//======================Camera Gallery Pop up screen =======================================================//

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    //userChoosenTask ="Take Photo";
                    cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    //userChoosenTask ="Choose from Library";
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                //onSelectFromGalleryResult(data);

                /*if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                {*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {

                    Log.d("Insidexxxxxx","greater than M");

                    //if (android.os.Build.VERSION.SDK_INT > 24) {

                    /*if(1>2)
                    {*/
                    Uri selectedImage = data.getData();
                    // Log.e("uri", selectedImage.toString());

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);


                    //studPickBase64 = Base64.encodeToString(imageinbytesArray, Base64.DEFAULT);

                  /*  ImageView imgProj = (ImageView) view2.findViewById(projImageId);
                    imgProj.setImageBitmap(bitmap);*/

//In marshmallow version camara image rotation code

                    ExifInterface exif= null;
                    try {
                        exif = new ExifInterface(picturePath.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                   /* Log.d("EXIF value1", exif.getAttribute(ExifInterface.TAG_ORIENTATION));
                    if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
                        bitmap= rotate(bitmap, 90);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
                        bitmap= rotate(bitmap, 270);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
                        bitmap= rotate(bitmap, 180);
                    }*//* else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")){
                        bitmap= rotate(bitmap, 0);
                    }*/
//-----------------------------------
                    imageProfile.setImageBitmap(bitmap);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    studPickBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    Log.d("studPickBase64sssssss:",studPickBase64);


                } else {
                    Log.d("Insidexxxxxx","elsexxxx");
                    Bundle extras2 = data.getExtras();
                    if (extras2 != null)
                    {
                        Log.d("Insidexxxxxx","extras2 not null");

                        if (data == null)
                        {
                            Toast.makeText(getApplicationContext(),"Failed to read picture data!",Toast.LENGTH_SHORT).show();
                            return;
                        }

                    //    imageProfile.setImageBitmap(BitmapFactory.decodeFile(actualImage.getAbsolutePath()));

                      /*  try {
                            actualImage = Class_FileUtil_forImage.from(this, data.getData());
                            img_profilePick.setImageBitmap(BitmapFactory.decodeFile(actualImage.getAbsolutePath()));

                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(),"Failed to read picture data!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }*/



                    }
                }


            } else if (requestCode == REQUEST_CAMERA) {
                //onCaptureImageResult(data);


                Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    // byte[] imageInByte;
                    imageinbytesArray = stream.toByteArray();

                    studPickBase64 = Base64.encodeToString(imageinbytesArray, Base64.DEFAULT);
                    //	Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("studPickBase64sssssss:",studPickBase64);

                    bmp_fromCamera = BitmapFactory.decodeByteArray(imageinbytesArray, 0, imageinbytesArray.length);
                    //report_photo.setImageBitmap(bm);


                        imageProfile.setImageBitmap(bmp_fromCamera);


                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

       // menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
//        menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);


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