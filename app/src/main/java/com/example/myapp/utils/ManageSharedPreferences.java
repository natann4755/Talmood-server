package com.example.myapp.utils;

import android.content.Context;
import com.example.model.Profile;


public class ManageSharedPreferences {
    public static String KEY_haveProfile = "KEYisConnected";
    public static String KEY_TOKEN = "KEY_TOKEN";

    public static void setProfile (Profile profile, Context context) {
        InstanceSharedPreferences.getInstance(context).setProfile(KEY_haveProfile, profile);
    }

    public static Profile getProfile(Context context) {
        return InstanceSharedPreferences.getInstance(context)
                .getProfile(KEY_haveProfile);
    }

    public static void setToken (String token, Context context) {
        InstanceSharedPreferences.getInstance(context).setToken(KEY_TOKEN,token);
    }

    public static String getToken(Context context) {
        return InstanceSharedPreferences.getInstance(context)
                .getToken(KEY_TOKEN,null);
    }
}
