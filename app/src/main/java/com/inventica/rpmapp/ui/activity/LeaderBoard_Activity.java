package com.inventica.rpmapp.ui.activity;

import android.app.AlertDialog;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.fragment.LeaderBoardFragment;
import com.inventica.rpmapp.ui.modles.RpmModule;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;
import java.util.Objects;

public class LeaderBoard_Activity extends AppCompatActivity {

    ArrayList<RpmModule> dataModels;
    ListView listView;
    private static LeaderBoardFragment.CustomAdapter adapter;
    ListAdapter lAdapter;
    int[] images = {R.drawable.leader1, R.drawable.leader2, R.drawable.leader3,R.drawable.leader2, R.drawable.leader1};

    String[] version = {"Sandra Adams", "Imma jones", "Mark Smith", "Sandra Adams", "Imma jones"};
    String[] slno = {"1", "2", "3", "4", "5"};
    String[] distance = {"54.48 KM", "25.36 KM", "32.48 KM", "24.48 KM", "34.29 KM"};
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leaderboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setting");
        mContext = this;
        getAllView();

        lAdapter = new ListAdapter(LeaderBoard_Activity.this, version, slno, distance, images);
        listView.setAdapter(lAdapter);
    }

    private void getAllView() {
        listView=(ListView) findViewById(R.id.list);
    }
    class ListAdapter extends BaseAdapter {

        Context context;
        private final String [] values;
        private final String [] numbers;
        private final String [] distance;
        private final int [] images;

        public ListAdapter(Context context, String [] values, String [] numbers, String [] distance, int [] images){
            //super(context, R.layout.single_list_app_item, utilsArrayList);
            this.context = context;
            this.values = values;
            this.numbers = numbers;
            this.distance = distance;
            this.images = images;
        }

        @Override
        public int getCount() {
            return values.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            ViewHolder viewHolder;

            final View result;

            if (convertView == null) {

                viewHolder = new ListAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.leaderboard_adapter, parent, false);
                viewHolder.sl_no_tv = (TextView) convertView.findViewById(R.id.sl_no_tv);
                viewHolder.leadername_tv = (TextView) convertView.findViewById(R.id.leadername_tv);
                viewHolder.distance_tv = (TextView) convertView.findViewById(R.id.distance_tv);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.leaderboard_iv);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.sl_no_tv.setText(numbers[position]);
            viewHolder.leadername_tv.setText(values[position]);
            viewHolder.distance_tv.setText(distance[position]);

            viewHolder.icon.setImageResource(images[position]);

            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              /*  Fragment fragment2 = new JoinChallengeFragment();
                Bundle args = new Bundle();
                args.putString("ChallengeName",viewHolder.txtName.getText().toString());
                args.putString("Distance", "100 Mi");
                args.putString("remainingDays", viewHolder.days_tv.getText().toString());
                // args.putString("participents", viewHolder.participents_tv.getText().toString());
                // args.putString("startDate", viewHolder.startTime_tv.getText().toString());
                //args.putString("endDate", viewHolder.endTime_tv.getText().toString());
                fragment2.setArguments(args);
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.nav_host_fragment, fragment2).commit();// mFragmentTransaction.addToBackStack(null).commit();
*/
                    Intent intent = new Intent(context, ComplitedChallengeActivity.class);
                   /* intent.putExtra("ChallengeName",viewHolder.txtName.getText().toString());
                    intent.putExtra("Distance", "100 Mi");
                    intent.putExtra("remainingDays", viewHolder.days_tv.getText().toString());*/
                /*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*/
                    context.startActivity(intent);
                }
            });
            return convertView;
        }

        private class ViewHolder {

            TextView distance_tv;
            TextView leadername_tv,sl_no_tv;
            ImageView icon;

        }

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