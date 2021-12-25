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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.home.MainActivity;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.AddQueryDetailsModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.QueryMasterModel;
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

public class SupportActivity extends AppCompatActivity {

    Spinner sp_query;
    Button btn_submit;
    Integer sp_queryId;
    EditText query_msg_et;
    QueryMasterModule Obj_Class_countryDetails;
    ImageView camera;
    RoundedImageView roundedImageView;
    String encodedString = "";
    private Context mContext;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String studPickBase64 = null;
    byte[] imageinbytesArray={0};
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_support);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Support");
        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
            getCountryList();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }

        camera.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              selectImage();
                                          }
                                      });
        sp_query.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Obj_Class_countryDetails = (QueryMasterModule) sp_query.getSelectedItem();
                Long sp_countryIdLong = Obj_Class_countryDetails.getQueryId();
                sp_queryId = sp_countryIdLong.intValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
                    dialog.setMessage("Loading..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                AddQueryDetailsModel addQueryDetailsModel=new AddQueryDetailsModel();
                addQueryDetailsModel.setQueryId(Long.valueOf(sp_queryId));
                addQueryDetailsModel.setQueryMessage(query_msg_et.getText().toString());
                addQueryDetailsModel.setQueryPhotoPath(studPickBase64);
                Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddUserQueryDetailsPost(addQueryDetailsModel).enqueue(new Callback<ModelApiResponse>() {
                    @Override
                    public void onResponse(Call<ModelApiResponse> call, Response<ModelApiResponse> response) {
                        Log.e("tag", "response==" + response.body().getMessage());

                        //  if(response.code()==200){
                        if (response.body().getStatusCode()==200) {
                            // if (response.message().equalsIgnoreCase("Success")) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(getApplicationContext(), "Registration is successfull.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
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
                        dialog.dismiss();
                    }
                });
                }else {
                    Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getAllView() {
        sp_query = (Spinner) findViewById(R.id.sp_query);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        query_msg_et = (EditText) findViewById(R.id.query_msg_et);
        camera = (ImageView) findViewById(R.id.camera);
       // roundedImageView = (RoundedImageView) findViewById(R.id.user_image);
    }

    public void getCountryList(){
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetQueryMasterGet().enqueue(new Callback<List<QueryMasterModel>>(){
            @Override
            public void onResponse(Call<List<QueryMasterModel>> call, Response<List<QueryMasterModel>> response) {
                if(response.isSuccessful())
                {


                    List<QueryMasterModel> getCountrylist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;

                    Log.e("getQueryName", String.valueOf(getCountrylist_obj.get(0).getQueryName()));
                    QueryMasterModule[] countrylist_arrayObj= new QueryMasterModule[getCountrylist_obj.size()];

                    List<QueryMasterModule> countryModelList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        QueryMasterModule inner_countrylst= new QueryMasterModule();
                        inner_countrylst.setQueryName(getCountrylist_obj.get(i).getQueryName());
                        inner_countrylst.setQueryId(getCountrylist_obj.get(i).getQueryId());
                        countrylist_arrayObj[i]=inner_countrylst;
                        countryModelList.add(inner_countrylst);
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, countryModelList);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    sp_query.setAdapter(dataAdapter);
dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<QueryMasterModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //======================Camera Gallery Pop up screen =======================================================//

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    //userChoosenTask ="Take Photo";
                    cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    //userChoosenTask ="Choose from Library";
                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                //onSelectFromGalleryResult(data);

                /*if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                {*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {

                    Log.d("Insidexxxxxx","greater than M");

                    //if (android.os.Build.VERSION.SDK_INT > 24) {

                    /*if(1>2)
                    {*/
                    Uri selectedImage = data.getData();
                    // Log.e("uri", selectedImage.toString());

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);


                    //studPickBase64 = Base64.encodeToString(imageinbytesArray, Base64.DEFAULT);

                  /*  ImageView imgProj = (ImageView) view2.findViewById(projImageId);
                    imgProj.setImageBitmap(bitmap);*/

//In marshmallow version camara image rotation code

                    ExifInterface exif= null;
                    try {
                        exif = new ExifInterface(picturePath.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                   /* Log.d("EXIF value1", exif.getAttribute(ExifInterface.TAG_ORIENTATION));
                    if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
                        bitmap= rotate(bitmap, 90);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
                        bitmap= rotate(bitmap, 270);
                    } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
                        bitmap= rotate(bitmap, 180);
                    }*//* else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")){
                        bitmap= rotate(bitmap, 0);
                    }*/
//-----------------------------------
                  //  img_profilePick.setImageBitmap(bitmap);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();
                    studPickBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    Log.d("studPickBase64sssssss:",studPickBase64);


                } else {
                    Log.d("Insidexxxxxx","elsexxxx");
                    Bundle extras2 = data.getExtras();
                    if (extras2 != null)
                    {
                        Log.d("Insidexxxxxx","extras2 not null");

                        if (data == null)
                        {
                            Toast.makeText(getApplicationContext(),"Failed to read picture data!",Toast.LENGTH_SHORT).show();
                            return;
                        }


                      /*  try {
                            actualImage = Class_FileUtil_forImage.from(this, data.getData());
                            img_profilePick.setImageBitmap(BitmapFactory.decodeFile(actualImage.getAbsolutePath()));

                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(),"Failed to read picture data!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }*/



                    }
                }


            } else if (requestCode == REQUEST_CAMERA) {
                //onCaptureImageResult(data);


                    Bundle extras = data.getExtras();

                    if (extras != null) {
                        Bitmap yourImage = extras.getParcelable("data");
                        // convert bitmap to byte
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        yourImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        // byte[] imageInByte;
                        imageinbytesArray = stream.toByteArray();

                        studPickBase64 = Base64.encodeToString(imageinbytesArray, Base64.DEFAULT);
                        //	Log.e("output before conversion", imageInByte.toString());
                        // Inserting Contacts
                     //   bmp_fromCamera = BitmapFactory.decodeByteArray(imageinbytesArray, 0, imageinbytesArray.length);
                        //report_photo.setImageBitmap(bm);


                    //    img_profilePick.setImageBitmap(bmp_fromCamera);


                    }
            }
        }
    }
/*
    private void showcameradialoge() {
        int REQUEST_PERMISSIONS = 1;

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getApplicationContext(),PERMISSIONS,REQUEST_PERMISSIONS);
            return;
        }
        else {

//            CropImage.activity().start(getApplicationContext(), this);


        }
    }


    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                roundedImageView.setImageURI(resultUri);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), resultUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                encodedString = getEncoded64ImageStringFromBitmap(bitmap);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
*/


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