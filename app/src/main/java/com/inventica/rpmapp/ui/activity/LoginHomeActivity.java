package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.application.RPMApplication;

public class LoginHomeActivity extends AppCompatActivity {

    Button Login_btn,joinnow_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        Login_btn = (Button) findViewById(R.id.Login_btn);
        joinnow_btn = (Button) findViewById(R.id.joinnow_btn);
        String token = ConnectionHelper.getToken(RPMApplication.getAppContext());

        Log.e("tag","token:"+token);

        if(ConnectionHelper.getToken(RPMApplication.getAppContext())==null){

        }else{
            startActivity(new Intent(LoginHomeActivity.this, MainActivity.class));
        }
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginHomeActivity.this, SignInActivity.class));
             //   finish();
            }});
        joinnow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginHomeActivity.this, SelectionActivity.class));
                //   finish();
            }});
    }



    private void popupLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginHomeActivity.this);

        builder.setTitle("Please confirm");
        builder.setMessage("Do you want to Logout from app ?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when user want to exit the app
                // Let allow the system to handle the event, such as exit th

                //hit_logou_api();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });  AlertDialog dialog = builder.create();

        dialog.show();


    }
}