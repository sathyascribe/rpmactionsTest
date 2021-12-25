package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.HeightModel;
import org.openapitools.client.model.LoginModel;
import org.openapitools.client.model.LoginResponseServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class SignInActivity extends AppCompatActivity {

    TextView forgot_pwd_tv,joinus_tv;
    Button btn_signin;
    EditText et_emailId,et_password;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        dialog = new ProgressDialog(SignInActivity.this);

        forgot_pwd_tv= (TextView) findViewById(R.id.forgot_pwd_tv);
        btn_signin=(Button) findViewById(R.id.btn_signin);
        et_emailId=(EditText) findViewById(R.id.et_emailId);
        et_password=(EditText)findViewById(R.id.et_password);
        joinus_tv=(TextView)findViewById(R.id.joinus_tv);

        Spannable wordtoSpan = new SpannableString("Not a Member? Join Us");

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 13, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        joinus_tv.setText(wordtoSpan);

        joinus_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SelectionActivity.class));
                //   finish();
            }
        });
        forgot_pwd_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
             //   finish();
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {

                    if (validation()) {
                    LoginModel loginModel = new LoginModel();
                    loginModel.setEmail(et_emailId.getText().toString());
                    loginModel.setPassword(et_password.getText().toString());
                        dialog.setMessage("Loading..");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    Rest_Adapter.getAccountApi().apiAccountLoginPut(loginModel).enqueue(new Callback<LoginResponseServiceResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponseServiceResponse> call, Response<LoginResponseServiceResponse> response) {
                            Log.e("tag", "response==" + response.message());

                            if (response.body().getStatusCode() == 200) {
                                // if(response.message().equalsIgnoreCase("Success")) {
                                Log.e("tag", "response msg==" + response.body().getMessage());
                                String bearer=response.body().getData().getAccessToken();
                                Rest_Adapter.getApiClient().setBearerToken("bearer "+bearer);
                                ConnectionHelper.setToken(getApplication(), response.body().getData().getAccessToken(), response.body().getData().getRefreshToken());
                                ConnectionHelper.setLoginDetails(getApplication(),response.body().getData().getUserEmail(),response.body().getData().getUsername());
                                startActivity(new Intent(SignInActivity.this, StartActivity.class));
                                Toast.makeText(SignInActivity.this, "Successfully Logged In.", Toast.LENGTH_LONG).show();
                                getCountryList();
                            } else {
                                Log.e("tag", "response msg==" + response.body().getMessage());
                                Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            } //else
                        /*if (response.code() == 409)
                            Toast.makeText(getApplicationContext(), "Email Id is already registered", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try later", Toast.LENGTH_LONG).show();*/
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<LoginResponseServiceResponse> call, Throwable t) {
                            println("register error ${t.message}");
                            dialog.dismiss();
                        }
                    });
                }}else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getCountryList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetAllMeasureHeightGet().enqueue(new Callback<List<HeightModel>>(){
            @Override
            public void onResponse(Call<List<HeightModel>> call, Response<List<HeightModel>> response) {
                Log.e("HeightModel:", response.message());
                if(response.isSuccessful())
                {


                    List<HeightModel> getCountrylist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;

                 //   Log.e("getCountrylist_obj", String.valueOf(getCountrylist_obj.get(0).getCountry()));
                    HeightModel[] countrylist_arrayObj= new HeightModel[getCountrylist_obj.size()];

                    List<HeightModel> countryModelList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        HeightModel inner_countrylst= new HeightModel();
                        inner_countrylst.setHeightMeasurementName(getCountrylist_obj.get(i).getHeightMeasurementName());
                        inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                        countrylist_arrayObj[i]=inner_countrylst;
                        countryModelList.add(inner_countrylst);
                    }
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<HeightModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(SignInActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean validation() {

        Boolean bemail = true, bpassword = true;

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

        if (et_password.getText().toString().length() == 0) {
            et_password.setError("Password is required!");
            bpassword = false;
        }

        if (bemail && bpassword) {
            return true;
        } else {
            return false;
        }
    }

}
