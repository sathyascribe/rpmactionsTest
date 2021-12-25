package com.inventica.rpmapp.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MySharedPreferences {

    public final static String PREFERENCE_NAME = "MyPreferences";

    public static void setPreference(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String setPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getString(key, "");
    }
    public static void clearSharedPreferences(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public  static Boolean getPreferenceBoolean(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getBoolean(key, false);
    }

    public static void setPreferenceBoolean(Context context, String key, Boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public  static int getPreferenceInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getInt(key, 0);
    }

    public static void setPreferenceInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("check", value);
        editor.apply();
    }
    public static void clearPreference(Context context, String key) {
        SharedPreferences preferences =context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void setPrefList(Context context, String key, String subscriptionId){
        SharedPreferences preferences =context.getSharedPreferences(PREFERENCE_NAME, 0);
        Set<String> set = new HashSet<String>();
        set = preferences.getStringSet(key, null);
        if(set == null)
        {
            set = new HashSet<String>();
        }
        set.add(subscriptionId);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }

    public static ArrayList<String> getPrefList(Context context, String key){
        SharedPreferences preferences =context.getSharedPreferences(PREFERENCE_NAME, 0);
        Set<String> set = new HashSet<String>();
        set = preferences.getStringSet(key, null);
        return new ArrayList<String>(set);
    }

}
