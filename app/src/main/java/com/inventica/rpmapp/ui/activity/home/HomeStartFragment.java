package com.inventica.rpmapp.ui.activity.home;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.helper.DateTimeString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeStartFragment extends Fragment {


    // array list for storing entries.
    ArrayList barEntriesArrayList;
    private TextView countDown, textDistance, textSpeed, textCalories;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeInMilliSecond = 700000;
    private TextView countDown2;

    private Location mLastLocation;
    private Context context;
    private ImageView pause_iv;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;
    public int counter = 0;
    float distanceInMeters = 0;
    List<LatLng> latLngList = new ArrayList<>();
    private static final String TAG = "HomeFragment";
    private double totalDistance = 0.0;
    private double timeSpend;
    private int zoomValue;
    private String timeSpent;
    private ConstraintLayout constraintLayoutGoal;
    private TextView textDistanceGoal, textPaceGoal, textTimeGoal;
    private ImageView imageDistanceGoal, imagePaceGoal, imageTimeGoal;
    private double mDistanceGoal, mPaceGoal;
    private int mTimeGoal;
    private CardView cardViewCount;
    private TextView textCount;
    List<Location> lastFiveLocations = Arrays.asList(new Location[3]);

    public HomeStartFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        View root = inflater.inflate(R.layout.fragment_goal, container, false);

        mDistanceGoal = 200.0;
        mTimeGoal = 100;
        mPaceGoal = mDistanceGoal / mTimeGoal;
        timeInMilliSecond = mTimeGoal * 1000;

        getAllView(root);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        checkLocationPermission();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this::startStopTimer, 5000);


        pause_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startStopTimer();
                startStopDialog(v);

            }


        });


        return root;

    }

    private void startStopDialog(View v) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_end_resume_timer);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView endStopTimer = dialog.findViewById(R.id.end_timmer);
        ImageView resumeTimer = dialog.findViewById(R.id.resume_timer);

        endStopTimer.setOnClickListener(v1 -> dialog.dismiss());
        resumeTimer.setOnClickListener(v12 -> {
            startStopTimer();
            dialog.dismiss();
        });

        dialog.show();

    }

    private void getAllView(View root) {

        pause_iv = root.findViewById(R.id.pause_iv);
        textDistance = root.findViewById(R.id.textDistance);
        textSpeed = root.findViewById(R.id.textSpeed);
        textCalories = root.findViewById(R.id.textCalories);
        countDown = root.findViewById(R.id.countDown);
        countDown2 = root.findViewById(R.id.countDown2);
        constraintLayoutGoal = root.findViewById(R.id.constraintLayoutGoal);
        constraintLayoutGoal.setVisibility(View.GONE);
        textDistanceGoal = root.findViewById(R.id.textDistanceGoal);
        textPaceGoal = root.findViewById(R.id.textPaceGoal);
        textTimeGoal = root.findViewById(R.id.textTimeGoal);
        imageDistanceGoal = root.findViewById(R.id.imageDistanceGoal);
        imagePaceGoal = root.findViewById(R.id.imagePaseGoal);
        imageTimeGoal = root.findViewById(R.id.imageTimeGoal);

    }

    private void checkLocationPermission() {


        initializeMap();


    }

    private void initializeMap() {
        if (mGoogleMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

            mapFrag.getMapAsync(googleMap -> {
                mGoogleMap = googleMap;
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mLocationRequest = LocationRequest.create()
                        .setInterval(1000)
                        .setFastestInterval(1000)
                        .setWaitForAccurateLocation(true)
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setMaxWaitTime(1000);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions


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

    double caloriesBurn = 0.0;
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();

            //The last location in the list is the newest
            Location location = locationList.get(locationList.size() - 1);
            Log.d(TAG, "Location: " + location.getLatitude() + " " + location.getLongitude());
            Log.d(TAG, "onLocationResult1: " + lastFiveLocations);

            for (int i = 0; i < 3; i++) {
                if (lastFiveLocations.get(i) == null) {
                    Log.d(TAG, "onLocationResult1: location : " + location + " added in list");
                    lastFiveLocations.set(i, location);
                    break;
                }
            }

            if (canAddDistance(location) && timerRunning) {
                totalDistance += lastFiveLocations.get(2).distanceTo(location);
                textDistance.setText((String.format("%.2f", totalDistance) + "m"));
                if (totalDistance == mDistanceGoal) {
                    timerRunning = false;
                    return;
                }

                int gradient = 1;
//                        double v2Max = (0.1 * speed) + (1.8 * speed * gradient ) + 3.5 ;
//                        caloriesBurn +=( ( v2Max * 70 /5000)*5) /1.5;
                Log.d(TAG, "onLocationResult: total distance =" + totalDistance);
                if ((int) totalDistance % 5 == 0) {
                    latLngList.add(new LatLng(location.getLatitude(), location.getLongitude()));
                }

            }

            double speed = (totalDistance / counter) * 1000;
            Log.d(TAG, "onLocationResult: counter" + counter);
            Log.d(TAG, "onLocationResult: Speed : " + speed);
            textSpeed.setText((String.format("%.2f", speed) + "m/s"));
            int gradient = 1;
            double v2Max = (0.1 * speed) + (1.8 * speed * gradient) + 3.5;
            caloriesBurn = totalDistance / 10;

            if (counter % 10 == 0) {
                textCalories.setText((String.format("%.2f", caloriesBurn)));
            }
            if (lastFiveLocations.get(2) != null) {
                Log.d(TAG, "onLocationResult set new list: ");
                setLastFiveLocation(location);
            }

            mLastLocation = location;

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            mGoogleMap.addPolyline(new PolylineOptions()
                    .addAll(latLngList)
                    .width(10)
                    .color(Color.BLUE));
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
            if (timerRunning)
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomValue));
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

    private boolean canAddDistance(Location location) {
        for (int i = 2; i >= 0; i--) {
            if (lastFiveLocations.get(i) != null) {
                Log.d(TAG, "canAddDistance: distance: " + lastFiveLocations.get(i).distanceTo(location));
                if (lastFiveLocations.get(i).distanceTo(location) >= 1) {
                    mLastLocation = lastFiveLocations.get(i);
                    Log.d(TAG, "canAddDistance: yes can add location from lat: " + location.getLatitude()
                            + ", long : " + location.getLongitude() + " to " + " lat : " + lastFiveLocations.get(i).getLatitude()
                            + " , long : " + lastFiveLocations.get(i).getLongitude());
                    return true;
                } else {
                    Log.d(TAG, "canAddDistance: can't add distance");
                }
            } else return false;
        }
        return false;
    }

    private void setLastFiveLocation(Location location) {


        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                lastFiveLocations.set(i, lastFiveLocations.get(i + 1));
            } else lastFiveLocations.set(i, location);
        }


    }

    private boolean isMoving(List<Location> locationList) {

        if (locationList.size() > 3) {
            for (int i = 1; i < 3; i++) {
                if (checkPresentInOneMeterCircle(locationList.get(locationList.size()), locationList.get(locationList.size() - i))) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkPresentInOneMeterCircle(Location location, Location location1) {

        return location.distanceTo(location1) > 1;
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }


    private void startStopTimer() {

        if (timerRunning) {
            constraintLayoutGoal.setVisibility(View.VISIBLE);
            setGoalValues();
            zoomValue = 18;
            if (mLastLocation != null) {
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), zoomValue));
            }
            stopTimer();
        } else {
            constraintLayoutGoal.setVisibility(View.GONE);
            zoomValue = 20;
            if (mLastLocation != null) {
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), zoomValue));
            }
            startTimer();
        }
    }

    private void setGoalValues() {

        imageDistanceGoal.setVisibility(View.VISIBLE);
        imagePaceGoal.setVisibility(View.VISIBLE);
        imageTimeGoal.setVisibility(View.VISIBLE);

        Log.d(TAG, "setGoalValues: set pace : " + mPaceGoal + " current pace : " + (totalDistance / (counter / 1000)));
        int cTime = counter / 1000;
        double cDistanceGoal = mPaceGoal * cTime;
        double expectedDistanceGoal = Math.abs(cDistanceGoal - (totalDistance));
        double expectedPace = Math.abs(mPaceGoal - (totalDistance / (counter / 1000)));
        int expectedTime = Math.abs(mTimeGoal - (int) ((mDistanceGoal - totalDistance) / (totalDistance * 1000 / counter)));

        textPaceGoal.setText(String.format("%.2f", expectedPace) + "m/s");
        textDistanceGoal.setText(String.format("%.2f", expectedDistanceGoal) + "m");
        textTimeGoal.setText(DateTimeString.secToString(expectedTime) + "min");

        if (mPaceGoal > (totalDistance / (counter / 1000))) {


            imageDistanceGoal.setImageResource(R.drawable.lose_red);
            imagePaceGoal.setImageResource(R.drawable.lose_red);
            imageTimeGoal.setImageResource(R.drawable.gain_green);

        } else if (mPaceGoal < (totalDistance / (counter / 1000))) {

            imageDistanceGoal.setImageResource(R.drawable.gain_green);
            imagePaceGoal.setImageResource(R.drawable.gain_green);
            imageTimeGoal.setImageResource(R.drawable.lose_red);

        } else {

            imageDistanceGoal.setVisibility(View.GONE);
            imagePaceGoal.setVisibility(View.GONE);
            imageTimeGoal.setVisibility(View.GONE);

        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeInMilliSecond, 1000) {
            @Override
            public void onTick(long l) {
                timeInMilliSecond = l;
                counter += 1000;
                updateTimer();
            }

            @Override
            public void onFinish() {
                constraintLayoutGoal.setVisibility(View.VISIBLE);
                setGoalValues();
                timerRunning = false;
                countDown.setText("Finished");
                Toast.makeText(getActivity(), "Task Completed", Toast.LENGTH_LONG).show();
            }
        }.start();
        timerRunning = true;
    }

    private void updateTimer() {

        int minTime = (int) timeInMilliSecond / (60000);
        int secTime = (int) timeInMilliSecond % 60000 / 1000;

        int minTime2 = (int) counter / (60000);
        int secTime2 = (int) counter % 60000 / 1000;

        String timeLeft = " " + minTime;
        timeLeft += ":";

        timeSpent = " " + minTime2;
        timeSpent += ":";

        if (secTime < 10)
            timeLeft += "0";
        if (secTime2 < 10)
            timeSpent += "0";
        timeLeft += secTime;
        timeSpent += secTime2;
        countDown.setText(timeLeft);
        countDown2.setText(timeSpent);

    }

    private void stopTimer() {
        countDownTimer.cancel();

        timerRunning = false;

    }


}