package com.example.myapp.utils;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class Language {

    public static void setAppLanguage(String language, Context context) {
        if (language!= null && ( !language.equals(""))) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config,
                    context.getResources().getDisplayMetrics());
        }
    }
}
