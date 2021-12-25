package com.inventica.rpmapp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.AddQueryDetailsModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.QueryMasterModule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class CreateChallengeComplitedActivity extends AppCompatActivity {

    TextView time_tv,date_tv,distance_tv,challengeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_complitedchallenges);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Support");
        String ChallengeName = getIntent().getExtras().getString("ChallengeName");
        String Distance = getIntent().getExtras().getString("Distance");
        String startDate = getIntent().getExtras().getString("startDate");
        String endDate = getIntent().getExtras().getString("endDate");
        String time = getIntent().getExtras().getString("time");
       /*
        String ChallengeName = getArguments().getString("ChallengeName");
        String Distance = getArguments().getString("Distance");
        String startDate = getArguments().getString("startDate");
        String endDate = getArguments().getString("endDate");
        String time = getArguments().getString("time");*/

        time_tv = (TextView) findViewById(R.id.time_tv);
        date_tv = (TextView) findViewById(R.id.date_tv);
        distance_tv = (TextView) findViewById(R.id.distance_tv);
        challengeName = (TextView) findViewById(R.id.challengeName);

        challengeName.setText(ChallengeName);
        distance_tv.setText(Distance);
        date_tv.setText(startDate+" - "+endDate);
        time_tv.setText(time);

        Log.e("tag","ChallengeName new:"+ChallengeName);
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