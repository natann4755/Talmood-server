package com.example.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.model.Profile;
import com.google.gson.Gson;

public class InstanceSharedPreferences {

    private static InstanceSharedPreferences mInstanceSharedPreferences;

    private SharedPreferences mSharedPreferences;

    private Gson gson = new Gson();


    public static InstanceSharedPreferences getInstance(Context context) {

        if (mInstanceSharedPreferences == null) {

            mInstanceSharedPreferences = new InstanceSharedPreferences(context);
        }

        return mInstanceSharedPreferences;
    }

    public InstanceSharedPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setProperty(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public void setProperty(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }



    public void setProperty(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public void setProfile(String key, Profile value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }

    public void setToken(String key, String token) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, token);
        editor.apply();
    }

    public String getToken(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }


    public String getProperty(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }


    public int getProperty(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }



    public boolean getProperty(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }


    public Profile getProfile(String key) {
        Profile mProfile = null;
        String json = mSharedPreferences.getString(key, null);
        if (json != null) {
            mProfile = gson.fromJson(json, Profile.class);
        }
        return mProfile;
    }

    public boolean deleteAll() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }



}
