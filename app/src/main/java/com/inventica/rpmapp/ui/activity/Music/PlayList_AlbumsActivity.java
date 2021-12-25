package com.inventica.rpmapp.ui.activity.Music;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.GetPlaylistModel;
import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayList_AlbumsActivity extends AppCompatActivity {

    ProgressDialog dialog;
    Context mContext;

   // TextView playlistName_tv;
    LinearLayout ll_name;
    ListView playlist_lv;
    ListAdapterPlayList lAdapter;
    ArrayList<GetPlaylistModel> song_datalist=new ArrayList<GetPlaylistModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_playlistalbms);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlists");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);

        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getlistSongsDetails();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }

        ll_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayList_AlbumsActivity.this, PlayList_AddSongsActivity.class);
           //     Log.e("tag","before PlayListId"+model.getPlayListId());
              //  intent.putExtra("PlayListId",model.getPlayListId().toString());
                startActivity(intent);
            }
        });

    }

    private void getAllView() {
     //   playlistName_tv=findViewById(R.id.playlistName_tv);
        playlist_lv=(ListView) findViewById(R.id.playlist_lv);
        ll_name = (LinearLayout)findViewById(R.id.ll_name);
    }

    public void getlistSongsDetails(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetMyPlaylistGet().enqueue(new Callback<List<GetPlaylistModel>>(){
            @Override
            public void onResponse(Call<List<GetPlaylistModel>> call, Response<List<GetPlaylistModel>> response) {
                if(response.isSuccessful())
                {


                    List<GetPlaylistModel> getLanguagelist_obj = response.body();

                    GetPlaylistModel[] Languagelist_arrayObj= new GetPlaylistModel[getLanguagelist_obj.size()];

                //    Log.e("tag","song=="+getLanguagelist_obj.get(0).getSongFilePath());
                    ///  List<CityModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getLanguagelist_obj.size();i++){
                        GetPlaylistModel inner_citylst= new GetPlaylistModel();
                        inner_citylst.setPlayListName(getLanguagelist_obj.get(i).getPlayListName());
                        inner_citylst.setPlayListId(getLanguagelist_obj.get(i).getPlayListId());

                        Languagelist_arrayObj[i]=inner_citylst;
                        // countryModelList.add(inner_countrylst);

                       // playlistName_tv.setText(getLanguagelist_obj.get(i).getTitle());
                        Log.e("tag","song=="+getLanguagelist_obj.get(i).getPlayListId());
                        song_datalist.add(inner_citylst);
                    }
                    lAdapter = new ListAdapterPlayList(PlayList_AlbumsActivity.this, song_datalist);
                    playlist_lv.setAdapter(lAdapter);

                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<GetPlaylistModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ListAdapterPlayList extends BaseAdapter {

        Context context;
        /*private final String [] values;
        private final String [] numbers;
        private final int [] images;*/
        ArrayList<GetPlaylistModel>song_datalist=new ArrayList<GetPlaylistModel>();

        public ListAdapterPlayList(Context context,ArrayList<GetPlaylistModel> song_datalist ){
            //super(context, R.layout.single_list_app_item, utilsArrayList);
            this.context = context;
            this.song_datalist = song_datalist;
           /* this.values = values;
            this.numbers = numbers;
            this.images = images;*/
        }

        @Override
        public int getCount() {
            return song_datalist.size();
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

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.child_playlist_item, parent, false);
                viewHolder.playlistName_tv = (TextView) convertView.findViewById(R.id.playlistName_tv);
                viewHolder.ll_playlist_title = (LinearLayout) convertView.findViewById(R.id.ll_playlist_title);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }
            final GetPlaylistModel model = song_datalist.get(position);
            viewHolder.playlistName_tv.setText(model.getPlayListName());
            viewHolder.ll_playlist_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlayList_AlbumsActivity.this, PlayList_SongsActivity.class);
                    Log.e("tag","before PlayListId"+model.getPlayListId());
                    intent.putExtra("PlayListId",model.getPlayListId().toString());
                    startActivity(intent);
                }
            });
          /*  viewHolder.txtVersion.setText(numbers[position]);

            viewHolder.icon.setImageResource(images[position]);

            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
              *//*  Fragment fragment2 = new JoinChallengeFragment();
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
*//*
                    Intent intent = new Intent(context, JoinChallenge_Activity.class);
                    intent.putExtra("ChallengeName",viewHolder.txtName.getText().toString());
                    intent.putExtra("Distance", "100 Mi");
                    intent.putExtra("remainingDays", viewHolder.days_tv.getText().toString());
                *//*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*//*
                    context.startActivity(intent);
                }
            });*/
            return convertView;
        }

        private class ViewHolder {

            TextView playlistName_tv;
            TextView txtVersion,days_tv;
            ImageView icon;
            LinearLayout ll_playlist_title;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
//        menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}