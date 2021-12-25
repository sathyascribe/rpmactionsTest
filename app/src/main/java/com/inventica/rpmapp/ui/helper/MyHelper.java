package com.inventica.rpmapp.ui.helper;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class MyHelper {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getDistanceUnit(Long distanceMeasurementId) {

        String distanceUnit ;
        switch (Math.toIntExact(distanceMeasurementId))
        {
            case 1 : distanceUnit = "Km";
                break;
            case 2 : distanceUnit = "Mi";
                break;
            default: distanceUnit = "M";
        }
        return distanceUnit;

    }

}
