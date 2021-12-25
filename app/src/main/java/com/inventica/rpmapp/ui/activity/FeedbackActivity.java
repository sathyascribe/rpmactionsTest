package com.inventica.rpmapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;
import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {

    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};
    int image[] = {R.drawable.icon_activity,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.ic_menu_camera,
    };
    String name[] = {"chocolate", "Aubergine", "Banana", "Burger", "Cake", "Ice Cream", "Tomato", "Watermelon"};
    ArrayList<itemModel> arrayList;
    CustomAdapter adapter1;
    ListView listView;
    private Context mContext;
    LinearLayout ll_notification,ll_permission,ll_privacyPolicy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feedback");
        mContext = this;
        getAllView();


    }

    private void getAllView() {
       /* ll_notification = (LinearLayout) findViewById(R.id.ll_notification);
        ll_permission = (LinearLayout) findViewById(R.id.ll_permission);
        ll_privacyPolicy = (LinearLayout) findViewById(R.id.ll_privacyPolicy);*/
       // listView = (ListView) findViewById(R.id.mobile_list);
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