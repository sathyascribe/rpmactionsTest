package com.inventica.rpmapp.ui.activity.Music;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.GetAllSongs;
import org.openapitools.client.model.GetPlaylistModel;
import org.openapitools.client.model.ModelApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class PlayList_SongsActivity extends AppCompatActivity {


    private Context mContext;
    ProgressDialog dialog;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<GetAllSongs>song_datalist=new ArrayList<GetAllSongs>();
    ArrayList<String> songlist = new ArrayList<>();
    HashMap<String, Integer> mapIndex;
    ArrayList<String> myDataset = new ArrayList<String>();
    CardView cd_playBtn;
    String PlayListId="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_songs_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        Intent intent = getIntent();
        PlayListId = getIntent().getExtras().getString("PlayListId");
        Log.e("tag","PlayListId"+PlayListId);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getPlaylistSongsDetails();
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
                Intent intent = new Intent(PlayList_SongsActivity.this, MusicPlay_Activity.class);
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

    public void getPlaylistSongsDetails(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Long playlistId= Long.valueOf(PlayListId);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetPlaylistSongsGet(playlistId).enqueue(new Callback<List<GetAllSongs>>(){
            @Override
            public void onResponse(Call<List<GetAllSongs>> call, Response<List<GetAllSongs>> response) {
                if(response.isSuccessful())
                {


                    List<GetAllSongs> getLanguagelist_obj = response.body();
                    Log.e("tag","getLanguagelist_obj=="+getLanguagelist_obj.get(0).getTitle());

                    GetAllSongs[] Languagelist_arrayObj= new GetAllSongs[getLanguagelist_obj.size()];

                  //  Log.e("tag","song=="+getLanguagelist_obj.get(0).getSongFilePath());
                    ///  List<CityModel> stateModelList = new ArrayList<>();
                    for(int i=0;i<getLanguagelist_obj.size();i++){
                        GetAllSongs inner_citylst= new GetAllSongs();
                        inner_citylst.setArtist(getLanguagelist_obj.get(i).getArtist());
                        inner_citylst.setSongId(getLanguagelist_obj.get(i).getSongId());
                        inner_citylst.setDuration(getLanguagelist_obj.get(i).getDuration());
                        inner_citylst.setSongLink(getLanguagelist_obj.get(i).getSongLink());
                        inner_citylst.setSize(getLanguagelist_obj.get(i).getSize());
                     //   inner_citylst.se(getLanguagelist_obj.get(i).getSongFilePath());
                        inner_citylst.setTitle(getLanguagelist_obj.get(i).getTitle());
                        Languagelist_arrayObj[i]=inner_citylst;
                        // countryModelList.add(inner_countrylst);

                     /*   State_Data state_data = new State_Data();
                        state_data.setText(getLanguagelist_obj.get(i).getSongFilePath());
                        state_data.setValue(getLanguagelist_obj.get(i).getId().toString());*/
                        // state_list.add(map);
                        Log.e("tag","GetPlaylistSongs=="+getLanguagelist_obj.get(i).getTitle());
                        songlist.add(getLanguagelist_obj.get(i).getTitle());
                        song_datalist.add(inner_citylst);
                    }
                    Log.e("tag","GetPlaylistSongs size=="+songlist.size());

                    // specify an adapter (see also next example)
                 /*   for(int i=0; i<26; i++) {
                        myDataset.add(Character.toString((char)(65 + i)));
                    }*/
                    mapIndex = calculateIndexesForName(songlist);

                    mAdapter = new MyAdapterFavourite(songlist, mapIndex,song_datalist);
                    mRecyclerView.setAdapter(mAdapter);
                    FastScrollRecyclerViewItemDecoration decoration = new FastScrollRecyclerViewItemDecoration(PlayList_SongsActivity.this);
                    mRecyclerView.addItemDecoration(decoration);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<GetAllSongs>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_playlist, menu);

      /*  menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
//        menu.findItem(R.id.nav_notification).setVisible(false);
        menu.findItem(R.id.nav_add).setVisible(false);*/


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
        } else if(id == R.id.action_editplayliste){
            showDialog();
            return true;
        } else if(id == R.id.action_songdelete){
            showWarningAlert(PlayList_SongsActivity.this);
            return true;
        }
            return super.onOptionsItemSelected(item);
    }

    void showDialog(){
        TextView txtName;
        Button btnFollow;
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_playlistedit);
        txtName =(TextView) myDialog.findViewById(R.id.playlistName);
        Button submit=myDialog.findViewById(R.id.submit_btn);
        Button cancel=myDialog.findViewById(R.id.cancel_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long playListId= Long.valueOf(PlayListId);
                GetPlaylistModel getPlaylistModel=new GetPlaylistModel();
                getPlaylistModel.setPlayListId(playListId);
                getPlaylistModel.setPlayListName(txtName.getText().toString());
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesEditMyPlaylistNamePost(getPlaylistModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {

                            Toast.makeText(PlayList_SongsActivity.this, "Successfully Updated.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PlayList_SongsActivity.this, MusicMainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                        //}
                        //  } //else
                        /*if (response.code() == 409)
                            Toast.makeText(getApplicationContext(), "Email Id is already registered", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try later", Toast.LENGTH_LONG).show();*/
                    }

                    @Override
                    public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                        println("register error ${t.message}");
                    }
                });

                myDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    void showCustomNameDialogNew(View root){
        ViewGroup viewGroup = root.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(PlayList_SongsActivity.this).inflate(R.layout.dialog_playlistedit, viewGroup, false);
        EditText playlistName= dialogView.findViewById(R.id.playlistName);
        Button submit=dialogView.findViewById(R.id.submit_btn);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        dialogView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayList_SongsActivity.this);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("tag","name: "+playlistName.getText().toString());
                /*challengeName_tv.setText(challengeName.getText().toString());*/
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        alertDialog.show();

    }
    private void showWarningAlert(Context context) { //Added argument
        AlertDialog alertDialog = new AlertDialog.Builder(context).create(); //Use context
        alertDialog.setTitle("Delete Playlist");
        alertDialog.setMessage("Are you sure you want to Delete your playlist?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        Long playListId= Long.valueOf(PlayListId);
                        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesDeletePlaylistSongsDelete(playListId).enqueue(new Callback<ModelApiResponse>() {
                            @Override
                            public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                                Log.e("tag", "response==" + response.body().getMessage());

                                //  if(response.code()==200){
                                if (response.body().getStatusCode()==200) {
                                    // if (response.message().equalsIgnoreCase("Success")) {

                                    Toast.makeText(PlayList_SongsActivity.this, "Successfully Updated.", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(PlayList_SongsActivity.this, MusicMainActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                                }
                                //}
                                //  } //else
                        /*if (response.code() == 409)
                            Toast.makeText(getApplicationContext(), "Email Id is already registered", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try later", Toast.LENGTH_LONG).show();*/
                            }

                            @Override
                            public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                                println("register error ${t.message}");
                            }
                        });

                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}