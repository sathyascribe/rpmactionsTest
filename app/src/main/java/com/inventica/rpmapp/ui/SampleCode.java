package com.inventica.rpmapp.ui;

import android.location.Location;
import android.location.LocationManager;

public class SampleCode {

//    private void whereAmI(){
//
//        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        updateWithNewLocation(location);
//
//        //GPS Listener
//        manager.addGpsStatusListener(gpsListener);
//
//        //Location Listener
//        int minTime = 0;//ms
//        int minDist = 0;//meter
//        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDist, locationListener);
//    }
//
//    GpsStatus.Listener gpsListener = new GpsStatus.Listener() {
//        @Override
//        public void onGpsStatusChanged(int event) {
//            switch (event) {
//                case GpsStatus.GPS_EVENT_STARTED:
//                    Log.d("x=", "GPS_EVENT_STARTED");
//                    Toast.makeText(MapsActivity.this, "GPS_EVENT_STARTED", Toast.LENGTH_SHORT).show();
//                    break;
//                case GpsStatus.GPS_EVENT_STOPPED:
//                    Log.d("x=", "GPS_EVENT_STOPPED");
//                    Toast.makeText(MapsActivity.this, "GPS_EVENT_STOPPED", Toast.LENGTH_SHORT).show();
//                    break;
//                case GpsStatus.GPS_EVENT_FIRST_FIX:
//                    Log.d("x=", "GPS_EVENT_FIRST_FIX");
//                    Toast.makeText(MapsActivity.this, "GPS_EVENT_FIRST_FIX", Toast.LENGTH_SHORT).show();
//                    break;
//                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//                    Log.d("x=", "GPS_EVENT_SATELLITE_STATUS");
//                    break;
//            }
//        }
//    };
//
//    LocationListener locationListener = new LocationListener(){
//        @Override
//        public void onLocationChanged(Location location) {
//            updateWithNewLocation(location);
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            updateWithNewLocation(null);
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            switch (status) {
//                case LocationProvider.OUT_OF_SERVICE:
//                    Log.v("x=", "Status Changed: Out of Service");
//                    Toast.makeText(MapsActivity.this, "Status Changed: Out of Service", Toast.LENGTH_SHORT).show();
//                    break;
//                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    Log.v("x=", "Status Changed: Temporarily Unavailable");
//                    Toast.makeText(MapsActivity.this, "Status Changed: Temporarily Unavailable", Toast.LENGTH_SHORT).show();
//                    break;
//                case LocationProvider.AVAILABLE:
//                    Log.v("x=", "Status Changed: Available");
//                    Toast.makeText(MapsActivity.this, "Status Changed: Available", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//
//    };
//
//    private void showMarkerMe(double lat, double lng){
//        if (markerMe != null) {
//            markerMe.remove();
//        }
//
//        MarkerOptions markerOpt = new MarkerOptions();
//        markerOpt.position(new LatLng(lat, lng));
//        markerOpt.title("I am here!");
//        markerMe = mMap.addMarker(markerOpt);
//
//        //Toast.makeText(this, "lat:" + lat + ",lng:" + lng, Toast.LENGTH_SHORT).show();
//    }
//
//    private void cameraFocusOnMe(double lat, double lng){
//        CameraPosition camPosition = new CameraPosition.Builder()
//                .target(new LatLng(lat, lng))
//                .zoom(16)
//                .build();
//
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
//    }
//
//    private void trackToMe(double lat, double lng){
//        if (traceOfMe == null) {
//            traceOfMe = new ArrayList<LatLng>();
//        }
//        traceOfMe.add(new LatLng(lat, lng));
//
//        calculateDistance(traceOfMe);
//
//        PolylineOptions polylineOpt = new PolylineOptions();
//        for (LatLng latlng : traceOfMe) {
//            polylineOpt.add(latlng);
//        }
//
//        polylineOpt.color(Color.RED);
//
//        Polyline line = mMap.addPolyline(polylineOpt);
//        line.setWidth(10);
//    }
//
//    private void calculateDistance(ArrayList<LatLng> points) {
//
//        for (int i =0; i < points.size() -1; i++) {
//            LatLng pointA =  points.get(i);
//            LatLng pointB = points.get(i + 1);
//            float[] results = new float[3];
//            Location.distanceBetween (pointA.latitude, pointA.longitude, pointB.latitude, pointB.longitude, results);
//            totalD +=  results[0];
//        }
//    }
//
//    private void updateWithNewLocation(Location location) {
//
//        String where = "";
//        if (location != null) {
//
//            double lng = location.getLongitude();
//
//            double lat = location.getLatitude();
//
//            float speed = location.getSpeed();
//
//            long time = location.getTime();
//            String timeString = getTimeString(time);
//
//            speedList.add(""+ speed);
//
//            where = "Lng: " + lng +
//                    "  Lat: " + lat +
//                    "  Speed: " + speed +
//                    "\nTime: " + timeString +
//                    "  Provider: " + "gps" +
//                    "  Distance: " + totalD ;
//
//
//            showMarkerMe(lat, lng);
//            cameraFocusOnMe(lat, lng);
//            trackToMe(lat, lng);
//
//        }else{
//            where = "No location found.";
//        }
//
//
//        txt.setText(where);
//    }
}
