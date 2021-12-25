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
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.CountryModel;
import org.openapitools.client.model.GroupAdminUserAdmin;
import org.openapitools.client.model.ModelApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class InviteCodeActivity extends AppCompatActivity {

    Button submit_btn;
    EditText et_invitecode;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        dialog = new ProgressDialog(InviteCodeActivity.this);

        et_invitecode=(EditText) findViewById(R.id.et_invitecode);

        submit_btn=(Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {

                    dialog.setMessage("Loading..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    String InviteCode=et_invitecode.getText().toString();

                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetGroupUserDetailsByInviteCodeGet(InviteCode).enqueue(new Callback<List<GroupAdminUserAdmin>>() {
                    @Override
                    public void onResponse(Call<List<GroupAdminUserAdmin>> call, Response<List<GroupAdminUserAdmin>> response) {
                        Log.e("tag", "response==" + response.code());


                        dialog.dismiss();
                        if (!response.body().equals("")) {

                            List<GroupAdminUserAdmin> groupAdminUserAdmins = response.body();
                            // List<Country> userlist_obj=response.body().;

                            String firstname = null,email = null,groupname = null,lastname = null,dob = null;
                     //       Log.e("getCountrylist_obj", String.valueOf(groupAdminUserAdmins.get(0).getFirstName()));
                            GroupAdminUserAdmin[] groupAdminUser_arrayObj= new GroupAdminUserAdmin[groupAdminUserAdmins.size()];
                            for(int i=0;i<groupAdminUserAdmins.size();i++){
                                firstname=groupAdminUserAdmins.get(i).getFirstName();
                                lastname=groupAdminUserAdmins.get(i).getLastName();
                                groupname=groupAdminUserAdmins.get(i).getGroupName();
                                email=groupAdminUserAdmins.get(i).getEmail();
                                dob= String.valueOf(groupAdminUserAdmins.get(i).getDateOfBirth());
                            }
                            Intent i = new Intent(InviteCodeActivity.this,SetPasswordActivity.class);
                            i.putExtra("firstname",firstname);
                            i.putExtra("lastname", lastname);
                            i.putExtra("groupname", groupname);
                            i.putExtra("email", email);
                            i.putExtra("dob", dob);
                            i.putExtra("inviteCode",InviteCode);
                            startActivity(i);

                            Toast.makeText(InviteCodeActivity.this, "Invite code sent successfully.", Toast.LENGTH_LONG).show();
                         //   startActivity(new Intent(InviteCodeActivity.this, SetPasswordActivity.class));
                        } else {
                            Toast.makeText(InviteCodeActivity.this, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GroupAdminUserAdmin>> call, Throwable t) {
                        println("register error ${t.message}");
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