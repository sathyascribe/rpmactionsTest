package com.inventica.rpmapp.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;
import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

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
    LinearLayout ll_notification,ll_permission,ll_privacyPolicy,ll_feedback,ll_termsconditions,ll_deleteaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setting");
        mContext = this;
        getAllView();

        ll_notification.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent intent = new Intent(SettingActivity.this, NotificationSettingActivity.class);
                                                  startActivity(intent);
                                              }
                                          });

        ll_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ManagePermissionActivity.class);
                startActivity(intent);
            }
        });

        ll_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
        ll_termsconditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomNameDialog(v);
            }
        });
        ll_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomNameDialog(v);
            }
        });
        ll_deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDeleteAccountDialog(v);
            }
        });
    }

    private void getAllView() {
        ll_notification = (LinearLayout) findViewById(R.id.ll_notification);
        ll_permission = (LinearLayout) findViewById(R.id.ll_permission);
        ll_privacyPolicy = (LinearLayout) findViewById(R.id.ll_privacyPolicy);
        ll_feedback = (LinearLayout) findViewById(R.id.ll_feedback);
        ll_termsconditions = (LinearLayout) findViewById(R.id.ll_termsconditions);
        ll_deleteaccount = (LinearLayout) findViewById(R.id.ll_deleteaccount);
       // listView = (ListView) findViewById(R.id.mobile_list);
    }
    void showCustomNameDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.termscondition);
        Button accept_btn =(Button) myDialog.findViewById(R.id.accept_btn);
        Button cancel_btn =(Button) myDialog.findViewById(R.id.cancel_btn);

        //txtclose.setText("M");
        //  btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }
    void showCustomDeleteAccountDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.logout_dialog,viewGroup, false);

        TextView text=dialogView.findViewById(R.id.text);
        TextView titleText=dialogView.findViewById(R.id.dialogTitle);

        Button submit=dialogView.findViewById(R.id.logout_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);
        titleText.setText("Delete Account");
        text.setText("Are you sure you want to close your account?");
        submit.setText("Submit");
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SettingActivity.this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionHelper.setToken(SettingActivity.this, "", "");
                ConnectionHelper.setLoginDetails(SettingActivity.this,"","");

                alertDialog.cancel();
                finish();
            }
        });
        cancel.setOnClickListener(v -> alertDialog.cancel());

        alertDialog.show();
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