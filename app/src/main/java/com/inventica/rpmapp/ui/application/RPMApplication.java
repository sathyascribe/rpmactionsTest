package com.inventica.rpmapp.ui.application;

import android.app.Application;
import android.content.Context;

import com.inventica.rpmapp.BuildConfig;
import com.inventica.rpmapp.ui.utils.Constants;


public class RPMApplication extends Application {

    private static Context context;

    public static Context getAppContext() {
        return RPMApplication.context;
    }

    public void onCreate() {
        super.onCreate();
        RPMApplication.context = getApplicationContext();
        System.setProperty("org.openapitools.client.baseUrl", BuildConfig.BASE_URL);
    }
}