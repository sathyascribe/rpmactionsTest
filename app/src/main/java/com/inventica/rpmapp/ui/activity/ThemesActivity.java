package com.inventica.rpmapp.ui.activity;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import java.util.Objects;


public class ThemesActivity extends AppCompatActivity {

    ProgressDialog dialog;
    GridView gridview;
    Button btn_submit;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_theme);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Themes");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);

        gridview.setAdapter(new ImageAdapter(ThemesActivity.this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("tag","position=="+position);
                if(position==0){
                    Toast.makeText(ThemesActivity.this, "Selected Blue", Toast.LENGTH_LONG).show();
                }else if(position==1){
                    Toast.makeText(ThemesActivity.this, "Selected Orange", Toast.LENGTH_LONG).show();
                }else if(position==2){
                    Toast.makeText(ThemesActivity.this, "Selected Pink", Toast.LENGTH_LONG).show();
                }else if(position==3){
                    Toast.makeText(ThemesActivity.this, "Selected Green", Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(ThemesActivity.this, position, Toast.LENGTH_LONG).show();
            }

           /* @Override
            public void onItemClick(AdapterView<!--?--> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",fruitNames[i]);
                intent.putExtra("image",fruitImages[i]);
                startActivity(intent);

            }*/
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fm = getFragmentManager();
                    fm.popBackStack();*/
                onBackPressed();
            }
        });

    }

    private void getAllView() {
         gridview = (GridView) findViewById(R.id.gridview);
         btn_submit = (Button) findViewById(R.id.btn_submit);
    }


    class ImageAdapter extends BaseAdapter {

        private Context mContext;

        // Constructor
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            }
            else
            {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // Keep all Images in array
        public Integer[] mThumbIds = {
                R.drawable.theme1, R.drawable.theme3,
                R.drawable.theme2, R.drawable.theme4
        };
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