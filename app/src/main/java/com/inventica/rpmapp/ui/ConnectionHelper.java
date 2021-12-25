package com.inventica.rpmapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.inventica.rpmapp.R;


public class ConnectionHelper {

    public static void setToken(Context context, String token, String refreshToken) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.jwt_token), token);
        editor.putString(context.getString(R.string.jwt_refresh_token), refreshToken);
        editor.apply();

    }
    public static String getToken(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String string = context.getString(R.string.jwt_token);
        return sharedPref.getString(string, null);
    }

    public static String getRefreshToken(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String string = context.getString(R.string.jwt_refresh_token);
        return sharedPref.getString(string, null);
    }


    public static void setLoginDetails(Context context, String emailId, String name) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.email_id), emailId);
        editor.putString(context.getString(R.string.name), name);
        editor.apply();
    }

    public static String getEmailId(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String email_id = context.getString(R.string.email_id);
        return sharedPref.getString(email_id, null);
    }
    public static String getName(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String profilename = context.getString(R.string.name);
        return sharedPref.getString(profilename, null);
    }

}
