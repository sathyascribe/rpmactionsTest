package com.inventica.rpmapp.ui.activity.Music;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.inventica.rpmapp.R;

import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.AddFavouriteSongsModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicPlay_Activity extends AppCompatActivity implements View.OnClickListener{


    private Context mContext;

    ProgressDialog dialog;
    private ImageView imgBtnPlayPause, imgbtnReplay, imgBtnPrev, imgBtnNext, imgBtnSetting,img_btn_stop;
    private TextView tvCurrentTime, tvTotalTime;
    private SeekBar seekbarController;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    private boolean checkFlag = true, repeatFlag = false, playContinueFlag = false, favFlag = true, playlistFlag = false;
    private ArrayList<UpdateSongsModel> songList;
    private int currentPosition;
    private String searchText = "";
    private UpdateSongsModel currSong;
    ArrayList<UpdateSongsModel> songDataList;
    ImageView iv_fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Music");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        songList = (ArrayList<UpdateSongsModel>) getIntent().getSerializableExtra("songlist");
        Log.e("tag","songDataList=="+songList.size());
       /* for()
        attachMusic(name, path);*/
        handler = new Handler();
        mediaPlayer = new MediaPlayer();

        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {

        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }

        iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // iv_fav.setImageResource(R.drawable.ic_fav_fill);
               iv_fav.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.teal_700));
                List<AddFavouriteSongsModel> addFavouriteSongsModel = null;
                AddFavouriteSongsModel favouriteSongsModel=new AddFavouriteSongsModel();
                Log.e("tag","song Id=="+songList.get(currentPosition).getId());
                favouriteSongsModel.setSongId(songList.get(currentPosition).getId());
                dialog.setMessage("Loading..");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddFavouriteSongsPost(favouriteSongsModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.code() == 200) {
                            Toast.makeText(MusicPlay_Activity.this, "Song Added to Favourite Successfully", Toast.LENGTH_LONG).show();
                            finish();
                        } else if (response.isSuccessful() && response.code() == 500) {
                            Toast.makeText(MusicPlay_Activity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(MusicPlay_Activity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<ModelApiResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(MusicPlay_Activity.this, "", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void getAllView() {
        imgBtnPrev = findViewById(R.id.img_btn_previous);
        imgBtnNext = findViewById(R.id.img_btn_next);
        imgBtnPlayPause = findViewById(R.id.img_btn_play);
        img_btn_stop = findViewById(R.id.img_btn_stop);
        tvCurrentTime = findViewById(R.id.tv_current_time);
        tvTotalTime = findViewById(R.id.tv_total_time);
        seekbarController = findViewById(R.id.seekbar_controller);
        iv_fav = findViewById(R.id.iv_fav);

        imgBtnNext.setOnClickListener(this);
        imgBtnPrev.setOnClickListener(this);
        imgBtnPlayPause.setOnClickListener(this);
        img_btn_stop.setOnClickListener(this);
    }

    private void setControls() {
        seekbarController.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        playCycle();
        checkFlag = true;
        if (mediaPlayer.isPlaying()) {
            imgBtnPlayPause.setImageResource(R.drawable.ic_pause_blue);
            tvTotalTime.setText(getTimeFormatted(mediaPlayer.getDuration()));
        }

        seekbarController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    tvCurrentTime.setText(getTimeFormatted(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void playCycle() {
        try {
            seekbarController.setProgress(mediaPlayer.getCurrentPosition());
            tvCurrentTime.setText(getTimeFormatted(mediaPlayer.getCurrentPosition()));
            if (mediaPlayer.isPlaying()) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();

                    }
                };
                handler.postDelayed(runnable, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTimeFormatted(long milliSeconds) {
        String finalTimerString = "";
        String secondsString;

        //Converting total duration into time
        int hours = (int) (milliSeconds / 3600000);
        int minutes = (int) (milliSeconds % 3600000) / 60000;
        int seconds = (int) ((milliSeconds % 3600000) % 60000 / 1000);

        // Adding hours if any
        if (hours > 0)
            finalTimerString = hours + ":";

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10)
            secondsString = "0" + seconds;
        else
            secondsString = "" + seconds;

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // Return timer String;
        return finalTimerString;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_play:
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource("https://yoneak.s3.ap-south-1.amazonaws.com/substorage/SongsDetails/855f8487-327e-4954-98ce-e12bb365c611.mp4");
                    mediaPlayer.prepare();
                    //setControls();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (checkFlag) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        imgBtnPlayPause.setImageResource(R.drawable.ic_player_con2_00001);
                    } else if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                        imgBtnPlayPause.setImageResource(R.drawable.ic_pause_blue);
                        playCycle();
                    }
                } else {
                    Toast.makeText(this, "Select the Song ..", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.img_btn_previous:
                if (checkFlag) {
                    if (mediaPlayer.getCurrentPosition() > 10) {
                        if (currentPosition - 1 > -1) {
                            attachMusic(songList.get(currentPosition - 1).getTitle(), songList.get(currentPosition - 1).getSongFilePath());
                            currentPosition = currentPosition - 1;
                        } else {
                            attachMusic(songList.get(currentPosition).getTitle(), songList.get(currentPosition).getSongFilePath());
                        }
                    } else {
                        attachMusic(songList.get(currentPosition).getTitle(), songList.get(currentPosition).getSongFilePath());
                    }
                } else {
                    Toast.makeText(this, "Select a Song . .", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_btn_next:
                if (checkFlag) {
                    if (currentPosition + 1 < songList.size()) {
                        attachMusic(songList.get(currentPosition + 1).getTitle(), songList.get(currentPosition + 1).getSongFilePath());
                        currentPosition += 1;
                    } else {
                        Toast.makeText(this, "Playlist Ended", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Select the Song ..", Toast.LENGTH_SHORT).show();
                }
                break;

            case  R.id.img_btn_stop:
                mediaPlayer.stop();
        }
    }
    private void attachMusic(String name, String path) {
        imgBtnPlayPause.setImageResource(R.drawable.ic_player_con2_00001);
        setTitle(name);
       /* menu.getItem(1).setIcon(R.drawable.favorite_icon);
        favFlag = true;*/

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            setControls();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imgBtnPlayPause.setImageResource(R.drawable.ic_player_con2_00001);
                if (playContinueFlag) {
                    if (currentPosition + 1 < songList.size()) {
                      //  attachMusic(songList.get(currentPosition + 1).getTitle(), songList.get(currentPosition + 1).getSongFilePath());
                        attachMusic("song 1", "https://yoneak.s3.ap-south-1.amazonaws.com/substorage/SongsDetails/855f8487-327e-4954-98ce-e12bb365c611.mp4");
                        currentPosition += 1;
                    } else {
                        Toast.makeText(MusicPlay_Activity.this, "PlayList Ended", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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