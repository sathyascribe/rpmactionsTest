package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.calendar.FriendListAdapter;

import com.inventica.rpmapp.ui.activity.gear.AddGear;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.ListUserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteFriendsActivity extends AppCompatActivity {


    private Context mContext;
    public final int LAUNCH_SECOND_ACTIVITY = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final ArrayList<ListUserModel> dataList = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Gears");
        mContext = this;

        recyclerView = findViewById(R.id.recyclerView);
        Button finishButton = findViewById(R.id.buttonFinish);
        adapter = new FriendListAdapter(mContext, dataList);
        layoutManager = new LinearLayoutManager(mContext);
        progressBar = findViewById(R.id.progress_bar);
        getRPMContacts();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        finishButton.setOnClickListener(v -> {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",dataList);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }

    private void getRPMContacts() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetListUserListGet().enqueue(new Callback<List<ListUserModel>>() {
            @Override
            public void onResponse(Call<List<ListUserModel>> call, Response<List<ListUserModel>> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (response.isSuccessful() && response.code() == 200) {
                    dataList.clear();
                    assert response.body() != null;

                    for (ListUserModel item: response.body()
                         ) {

                        if(item.getIsConnected())
                        {
                            dataList.add(item);
                        }

                    }

                    adapter.notifyDataSetChanged();

                } else if (response.isSuccessful() && response.code() == 500) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<ListUserModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                t.printStackTrace();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
        //  menu.findItem(R.id.nav_search).setVisible(false);
        //menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);

        return true;
    }

   public void returnIntent(ArrayList<ListUserModel> dataList )
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (ListUserModel item:dataList
             ) {

            if(item.getIsSelected())
            {
                Toast.makeText(mContext,"Invitation sends to " + item.getInviteUserName(),Toast.LENGTH_SHORT);
                stringBuilder.append(item.getInviteUserId());
                stringBuilder.append("//");
            }
        }
        String resultString = stringBuilder.toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",dataList);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {

            return true;
        } else if (id == R.id.nav_add) {
            startActivity(new Intent(mContext, AddGear.class));
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}