package com.inventica.rpmapp.ui.activity.Music;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.modles.State_Data;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.LanguageModel;
import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSongsActivity extends AppCompatActivity {


    private Context mContext;
    ProgressDialog dialog;

    ListView listView;
    private AllSongsListAdapter adapter;
    AllSongsListAdapter lAdapter;
    String[] version = {"Divergent", "Divergent", "Divergent", "Divergent", "Divergent"};

    String[] versionNumber = {"Ooyy", "Ooyy", "Ooyy", "Ooyy", "Ooyy"};

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<UpdateSongsModel>song_datalist=new ArrayList<UpdateSongsModel>();
    ArrayList<String> songlist = new ArrayList<>();
    HashMap<String, Integer> mapIndex;
    ArrayList<String> myDataset = new ArrayList<String>();
    CardView cd_playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_allsongs);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Songs");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getlistSongsDetails();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cd_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag","song_datalist=="+song_datalist.size());
                Intent intent = new Intent(AllSongsActivity.this, MusicPlay_Activity.class);
                intent.putExtra("songlist", song_datalist);
                startActivity(intent);
            }
        });


    }

    private void getAllView() {
     //   listView=(ListView) findViewById(R.id.mobile_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        cd_playBtn = (CardView) findViewById(R.id.cd_playBtn);
    }

    private HashMap<String, Integer> calculateIndexesForName(ArrayList<String> items){
        HashMap<String, Integer> mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i<items.size(); i++){
            String name = items.get(i);
            String index = name.substring(0,1);
            index = index.toUpperCase();

            if (!mapIndex.containsKey(index)) {
                mapIndex.put(index, i);
            }
        }
        return mapIndex;
    }

    public void getlistSongsDetails(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetlistSongsDetailsGet().enqueue(new Callback<List<UpdateSongsModel>>(){
            @Override
            public void onResponse(Call<List<UpdateSongsModel>> call, Response<List<UpdateSongsModel>> response) {
                if(response.isSuccessful())
                {


                    List<UpdateSongsModel> getLanguagelist_obj = response.body();

                    UpdateSongsModel[] Languagelist_arrayObj= new UpdateSongsModel[getLanguagelist_obj.size()];

                    Log.e("tag","song=="+getLanguagelist_obj.get(0).getSongFilePath());
                    ///  List<CityModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getLanguagelist_obj.size();i++){
                        UpdateSongsModel inner_citylst= new UpdateSongsModel();
                        inner_citylst.setArtist(getLanguagelist_obj.get(i).getArtist());
                        inner_citylst.setId(getLanguagelist_obj.get(i).getId());
                        inner_citylst.setDuration(getLanguagelist_obj.get(i).getDuration());
                        inner_citylst.setLink(getLanguagelist_obj.get(i).getLink());
                        inner_citylst.setSize(getLanguagelist_obj.get(i).getSize());
                        inner_citylst.setSongFilePath(getLanguagelist_obj.get(i).getSongFilePath());
                        inner_citylst.setTitle(getLanguagelist_obj.get(i).getTitle());
                        Languagelist_arrayObj[i]=inner_citylst;
                        // countryModelList.add(inner_countrylst);

                     /*   State_Data state_data = new State_Data();
                        state_data.setText(getLanguagelist_obj.get(i).getSongFilePath());
                        state_data.setValue(getLanguagelist_obj.get(i).getId().toString());*/
                        // state_list.add(map);
                        Log.e("tag","song=="+getLanguagelist_obj.get(i).getTitle());
                        songlist.add(getLanguagelist_obj.get(i).getTitle());
                        song_datalist.add(inner_citylst);
                    }
                    Log.e("tag","songlist=="+songlist.size());

                    // specify an adapter (see also next example)
                 /*   for(int i=0; i<26; i++) {
                        myDataset.add(Character.toString((char)(65 + i)));
                    }*/
                    mapIndex = calculateIndexesForName(songlist);

                    mAdapter = new MyAdapter(songlist, mapIndex,song_datalist);
                    mRecyclerView.setAdapter(mAdapter);
                    FastScrollRecyclerViewItemDecoration decoration = new FastScrollRecyclerViewItemDecoration(AllSongsActivity.this);
                    mRecyclerView.addItemDecoration(decoration);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<UpdateSongsModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class AllSongsListAdapter extends BaseAdapter {

        Context context;
        private final String [] values;
        private final String [] numbers;
        private final int [] images;

        public AllSongsListAdapter(Context context, String [] values, String [] numbers, int [] images){
            //super(context, R.layout.single_list_app_item, utilsArrayList);
            this.context = context;
            this.values = values;
            this.numbers = numbers;
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

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.child_allsongslist_item, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
                viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.emailId_tv);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_view_shoe);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.txtName.setText(values[position]);
            viewHolder.txtVersion.setText(numbers[position]);
            viewHolder.icon.setImageResource(images[position]);

            return convertView;
        }

        private class ViewHolder {

            TextView txtName;
            TextView txtVersion;
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
        menu.findItem(R.id.nav_add).setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {
            return true;
        }  else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}