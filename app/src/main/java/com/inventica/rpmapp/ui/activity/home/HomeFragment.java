package com.inventica.rpmapp.ui.activity.home;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.MySharedPreferences;
import com.inventica.rpmapp.ui.activity.Music.MusicMainActivity;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import org.jetbrains.annotations.NotNull;
import org.openapitools.client.model.Int64ServiceResponse;
import org.openapitools.client.model.SetGoalModel;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Context context;
    private CardView set_goals;
    private Location mLastLocation;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private static final String TAG = "HomeFragment";
    private static final int LAUNCH_GEAR = 31;
    public static String[] PERMISSIONS = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = this.getActivity();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView start_iv = root.findViewById(R.id.start_iv);
        set_goals = root.findViewById(R.id.cardView2);
        ImageView imageSelectGear = root.findViewById(R.id.imageSelectGear);
        CardView music_list = root.findViewById(R.id.music_list);
        CardView camera_iv = root.findViewById(R.id.camera_iv);
        loadingProgressBar = new ProgressDialog(context);
        camera_iv.setOnClickListener(v -> {

        });
        start_iv.setOnClickListener(v -> {

            Fragment fragment2 = new HomeStartFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        imageSelectGear.setOnClickListener(v -> {

        });


        set_goals.setOnClickListener(new View.OnClickListener() {

            final String[] paceList1 = {"1", "2",
                    "3", "4",
                    "5", "6"};
            final String[] paceList2 = {".10", ".20",
                    ".30", ".40",
                    ".50", "1", "1.30"};
            final String[] distanceList1 = {".10", ".20", ".50", "1", "2",
                    "3", "4",
                    "5", "6"};
            final String[] distanceList2 = {".10", ".20",
                    ".30", ".40",
                    ".50", "1", "1.30"};

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_set_goals);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Spinner paceSpinner1 = dialog.findViewById(R.id.paseValue);
                Spinner paceSpinner2 = dialog.findViewById(R.id.paseValue2);
                Spinner distanceSpinner1 = dialog.findViewById(R.id.paseValue11);
                Spinner distanceSpinner2 = dialog.findViewById(R.id.paseValue21);

                TextView timePickerText = dialog.findViewById(R.id.time);
                timePickerText.setOnClickListener(v1 -> {

                    Calendar mCurrentTime = Calendar.getInstance();
                    int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mCurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getActivity(), (timePicker, selectedHour, selectedMinute) -> {
                        String timeString = selectedHour + ":" + selectedMinute + ":" + "00";
                        timePickerText.setText(timeString);
                    }, hour, minute, false);//Yes 24 hour time
                    mTimePicker.setTitle("Time");
                    mTimePicker.show();

                });

                CardView setGoal = dialog.findViewById(R.id.setGoal);
                setGoal.setOnClickListener(v12 -> {
                    dialog.dismiss();

                    setMyGoal();

                });

                paceSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) paceSpinner1.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                        ((TextView) paceSpinner1.getSelectedView()).setTypeface(null, Typeface.BOLD);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                ArrayAdapter paceAdapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, paceList1);
                paceAdapter1.setDropDownViewResource(R.layout.sninner_goal_text);
                paceSpinner1.setAdapter(paceAdapter1);

                paceSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) paceSpinner2.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                        ((TextView) paceSpinner2.getSelectedView()).setTypeface(null, Typeface.BOLD);

                        //Toast.makeText(getActivity(),paceList2[position],Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        ((TextView) paceSpinner2.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                    }
                });

                ArrayAdapter paceAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, paceList2);
                paceAdapter2.setDropDownViewResource(R.layout.sninner_goal_text);
                paceSpinner2.setAdapter(paceAdapter2);

                distanceSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) distanceSpinner1.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                        ((TextView) distanceSpinner1.getSelectedView()).setTypeface(null, Typeface.BOLD);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        ((TextView) distanceSpinner1.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                    }
                });

                ArrayAdapter distanceAdapter1 = new ArrayAdapter(getActivity(),
                        android.R.layout.simple_spinner_item, distanceList1);
                distanceAdapter1.setDropDownViewResource(R.layout.sninner_goal_text);
                distanceSpinner1.setAdapter(distanceAdapter1);
                distanceSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) distanceSpinner2.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                        ((TextView) distanceSpinner2.getSelectedView()).setTypeface(null, Typeface.BOLD);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        ((TextView) distanceSpinner2.getSelectedView()).setTextColor(ContextCompat.getColor(context, R.color.spinner_text_color));
                    }
                });

                ArrayAdapter distanceAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, distanceList2);
                distanceAdapter2.setDropDownViewResource(R.layout.sninner_goal_text);
                distanceSpinner2.setAdapter(distanceAdapter2);

                dialog.show();
            }
        });

        music_list.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MusicMainActivity.class);
            startActivity(intent);

        });
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context.getApplicationContext());

        checkLocationPermission();
        return root;
    }

    private ProgressDialog loadingProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setMyGoal() {

        loadingProgressBar.setMessage("Loading ...");
        loadingProgressBar.show();
        SetGoalModel setGoalModel = new SetGoalModel();
        setGoalModel.distance(200.0);
        setGoalModel.pace(2.0);
        Date calendar = Calendar.getInstance().getTime();
        calendar.setMinutes(calendar.getMinutes() + 2);
        setGoalModel.setTime(calendar);
        setGoalModel.setUserGearId(27);

        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesSetGoalPost(setGoalModel).enqueue(new Callback<Int64ServiceResponse>() {
            @Override
            public void onResponse(@NotNull Call<Int64ServiceResponse> call, @NotNull Response<Int64ServiceResponse> response) {
                loadingProgressBar.dismiss();
                Log.d(TAG, "onResponse: " + response);
                if (response.isSuccessful() && response.code() == 200) {

                    Toast.makeText(context, "Goal Set Successfully", Toast.LENGTH_LONG).show();
                    set_goals.setCardBackgroundColor(ContextCompat.getColor(context, R.color.purple_700));
                    int goalId = Math.toIntExact(response.body().getData());
                    Log.d(TAG, "onResponse: Goal Id = " + goalId);
                    MySharedPreferences.setPreferenceBoolean(context, "IsGoalSet", true);
                    MySharedPreferences.setPreferenceInt(context, "CurrentGoalId", goalId);

                } else if (response.isSuccessful() && response.code() == 500) {
                    assert response.body() != null;
                    Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();


                } else {
                    assert response.body() != null;
                    Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<Int64ServiceResponse> call, @NotNull Throwable t) {

            }
        });

    }

    private void checkLocationPermission() {

        initializeMap();
    }

    private void initializeMap() {
        if (mGoogleMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

            assert mapFrag != null;
            mapFrag.getMapAsync(googleMap -> {
                mGoogleMap = googleMap;
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mLocationRequest = LocationRequest.create()
                        .setInterval(1000)
                        .setFastestInterval(1000)
                        .setWaitForAccurateLocation(true)
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setMaxWaitTime(100);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    String s = "";
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);

            });
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            for (int i = 0; i < locationList.size(); i++) {
                Log.d(TAG, "onLocationResult: Latitude " + locationList.get(i).getLatitude()
                        + " Longitude : " + locationList.get(i).getLongitude());
            }

            //The last location in the list is the newest
            Location location = locationList.get(locationList.size() - 1);
            Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());

            mLastLocation = location;
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        }

        @Override
        public void onLocationAvailability(@NonNull LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
        }

        @NonNull
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    };

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }


}
