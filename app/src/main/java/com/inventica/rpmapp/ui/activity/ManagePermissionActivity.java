package com.inventica.rpmapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;

import java.util.Objects;

public class ManagePermissionActivity extends AppCompatActivity {

    LinearLayout ll_accesslocation,ll_accesscamera,ll_accessmessage,ll_accessgallery;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_managepermission);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notification Settings");
        mContext = this;
        getAllView();

    }

    private void getAllView() {
        ll_accesslocation = (LinearLayout) findViewById(R.id.ll_accesslocation);
        ll_accesscamera = (LinearLayout) findViewById(R.id.ll_accesscamera);
        ll_accessmessage = (LinearLayout) findViewById(R.id.ll_accessmessage);
        ll_accessgallery = (LinearLayout) findViewById(R.id.ll_accessgallery);

        // roundedImageView = (RoundedImageView) findViewById(R.id.user_image);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
//        menu.findItem(R.id.nav_notification).setVisible(false);
//        menu.findItem(R.id.nav_add).setVisible(false);


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