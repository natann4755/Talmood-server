package com.example.myapp.utils;

import android.content.Context;
import android.util.Log;

import com.example.model.shas_masechtot.Shas;
import com.example.myapp.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.myapp.utils.StaticVariables.Assets_Name;

public class InitAllShasFromGson {
    private Context mContext;
    private static InitAllShasFromGson INSTANCE;
    private Shas mShas;

    public static Shas getInstance(Context mContext){
        if (INSTANCE == null){
            INSTANCE = new InitAllShasFromGson(mContext);
        }
        return INSTANCE.mShas;
    }

    private InitAllShasFromGson(Context mContext) {
        initListAllShah(mContext);
    }

    public void initListAllShah(Context mContext) {
        Gson gson = new Gson();
        try {
            String txt;
            txt = convertStreamToString(mContext.getAssets().open(Assets_Name));
            mShas = gson.fromJson(txt, Shas.class);
        } catch (Exception e) {
            Log.e(mContext.getString(R.string.conversion_assets_error), e.toString());
        }
    }


    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
