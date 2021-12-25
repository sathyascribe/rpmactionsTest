package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.GroupAdminUserAdmin;
import org.openapitools.client.model.ModelApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class SetPasswordActivity extends AppCompatActivity {

    Button joinus_btn;
    String firstname = null,email = null,groupname = null,lastname = null,dob = null,InviteCode;

    EditText et_password;
    TextView group_name_tv,emailId_tv,firstName_tv,lastName_tv,dob_tv;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        joinus_btn=(Button) findViewById(R.id.joinus_btn);
        group_name_tv=(TextView) findViewById(R.id.group_name_tv);
        emailId_tv=(TextView) findViewById(R.id.emailId_tv);
        firstName_tv=(TextView) findViewById(R.id.firstName_tv);
        lastName_tv=(TextView) findViewById(R.id.lastName_tv);
        dob_tv=(TextView) findViewById(R.id.dob_tv);
        et_password=(EditText) findViewById(R.id.et_password );
        dialog = new ProgressDialog(SetPasswordActivity.this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstname = extras.getString("firstname");
            email = extras.getString("email");
            groupname = extras.getString("groupname");
            lastname = extras.getString("lastname");
            dob = extras.getString("dob");
            InviteCode = extras.getString("inviteCode");

            Log.e("tag","firstname="+firstname+"email="+email+"groupname="+groupname+"lastname="+lastname);
        }

        group_name_tv.setText(groupname);
        emailId_tv.setText(email);
        firstName_tv.setText(firstname);
        lastName_tv.setText(lastname);
        dob_tv.setText(dob);

        joinus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
                String password=et_password.getText().toString();
                dialog.setMessage("Loading..");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesSetUserPasswordPatch(password,InviteCode).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {

                            Toast.makeText(SetPasswordActivity.this, "Password set successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SetPasswordActivity.this, SignInActivity.class));
                        } else {
                            Toast.makeText(SetPasswordActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                        println("error ${t.message}");
                        dialog.dismiss();
                    }

                });
                }else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }

                //   finish();
            }
        });
    }
}